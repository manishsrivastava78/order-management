package com.tcs.eas.rest.apis.log;

import static net.logstash.logback.argument.StructuredArguments.kv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.eas.rest.apis.model.Test;
import com.tcs.eas.rest.apis.utility.Utility;

import lombok.extern.java.Log;
import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;

@Component
@Log
public class LoggingServiceImpl extends MdcLogging implements LoggingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingService.class);

	@Override
	public void logRequest(HttpServletRequest request, Object body) {
		Map<String, String> map = new HashMap<>();
		UUID uuid = UUID.randomUUID();
		String correlationID = uuid.toString();
		map.put(CORRELATION_ID, correlationID);
		map.put(HTTP_METHOD, request.getMethod());
		map.put(REQUEST_URI, request.getRequestURI());
		map.put(HTTP_PROTOCOL, request.getProtocol());
		map.put(HEADER_CONTENT_TYPE, request.getContentType());
		map.put(CLIENT_IP, Utility.getClientIp(request));
		Map<String, String> httpHeadersMap = Utility.getRequestHeaderValues(request);
		setMDC(map);
		LOGGER.info("Request details", StructuredArguments.entries(httpHeadersMap));
		clearMDC();
		MDC.put(CORRELATION_ID, correlationID);
	}

	@Override
	public void logResponse(HttpServletRequest request, HttpServletResponse httpServletResponse, Object body) {
		Map<String, String> map = new HashMap<>();
		map.put(HTTP_METHOD, request.getMethod());
		map.put(REQUEST_URI, request.getRequestURI());
		map.put(HTTP_PROTOCOL, request.getProtocol());
		map.put(HTTP_STATUS, httpServletResponse.getStatus() + "");
		setMDC(map);
		LOGGER.info("Response details", httpServletResponse.getStatus());
		// LOGGER.info("Response details: ", kv("jsonPayload", from(body)));
		// Map<String, Object> map1 = from(body);
		clearMDC();
	}

	/**
	 * 
	 */
	public void clearMDC() {
		MDC.clear();
	}

	/**
	 * 
	 * @param map
	 */
	protected void setMDC(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			MDC.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	protected Map<String, Object> from(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {
		});
	}

	public void writeProcessLog(String httpMethod, String serviceName, String serviceMethod, Object object) {
		Map<String, String> map = new HashMap<>();
		map.put(CORRELATION_ID, MDC.get(CORRELATION_ID));
		map.put(HTTP_METHOD, httpMethod);
		map.put(SERVICE_NAME, serviceName);
		map.put(SERVICE_METHOD, serviceMethod);
		setMDC(map);
		if (object != null && !(object instanceof List)) {
			LOGGER.info("Processing details: " + httpMethod + " call for " + object.getClass().getName(),
					kv("jsonPayload", from(object)));
		} else if (object != null && object instanceof List) {
			LOGGER.info("Processing details: " + httpMethod + " call for " + object.getClass().getName(),
					kv("jsonPayload", writeListToJsonArray(object)));
		}

		else {
			LOGGER.info("Processing details: " + httpMethod + " call for " + object.getClass().getName());
		}
	}

	public static void main(String[] args) throws Exception {
		LoggingServiceImpl ls = new LoggingServiceImpl();
		/*
		 * List<Test> list = new ArrayList<>(); Test t = new Test("dsad", "dasd");
		 * list.add(t); t = new Test("ddsadsad", "dasd"); list.add(t);
		 * 
		 * ObjectMapper mapper = new ObjectMapper(); String newJsonData =
		 * mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list); //return
		 * newJsonData; System.out.println(newJsonData);
		 * 
		 * //Map<String, Object> map = ls.from(list);
		 * 
		 * StructuredArgument sa = kv("jsonPayload", newJsonData);
		 */
		// LOGGER.info("info:jsonPayload"+newJsonData);
		// System.out.println();

		/*
		 * List<Test> list = new ArrayList<>(); Test t = new Test("dsad", "dasd");
		 * list.add(t); t = new Test("ddsadsad", "dasd"); list.add(t);
		 * 
		 * ObjectMapper objectMapper = new ObjectMapper(); try { String json =
		 * objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
		 * System.out.println(json); } catch(Exception e) { e.printStackTrace(); }
		 */

		final List<Test> list = new ArrayList<Test>(2);
		list.add(new Test("ddsadsad", "dasd"));
		list.add(new Test("ddsadsdsdad", "dasd"));

		System.out.println(ls.writeListToJsonArray(list));
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @throws IOException
	 */
	public String writeListToJsonArray(Object list) {
		try {
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, list);
			final byte[] data = out.toByteArray();
			return new String(data);
		} catch (Exception e) {
			return "{\"error\":\"payload generation issue\"}";
		}
	}

}
