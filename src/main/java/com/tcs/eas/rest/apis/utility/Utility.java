package com.tcs.eas.rest.apis.utility;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.eas.rest.apis.Constants;

/**
 * 
 * @author 44745
 *
 */
public class Utility  implements Constants{
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, Object> from(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {
		});
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getRequestHeaderValues(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String remoteAddr = "";
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}
	
	/**
	 * 
	 * @param headers
	 * @return
	 */
	public static HttpHeaders getCustomResponseHeaders(Map<String, String> headers) {
		// setting required response headers
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(TRANSACTION_ID,
				headers.get(TRANSACTION_ID) == null ? NO_TRANSATION_ID : headers.get(TRANSACTION_ID));
		String correlationId = headers.get(CORRELATION_ID);
		if (correlationId != null) {
			responseHeaders.set(CORRELATION_ID, correlationId);
		}

		return responseHeaders;
	}
	
}
