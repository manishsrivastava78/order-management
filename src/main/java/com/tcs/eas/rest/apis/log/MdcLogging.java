package com.tcs.eas.rest.apis.log;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.MDC;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.eas.rest.apis.Constants;

/**
 * 
 * @author 44745
 *
 */
public abstract class MdcLogging implements Constants {
	
	/**
	 * 
	 */
	protected void clearMDC() {
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
	
	
	protected void writeProcessLog(String httpMethod,String serviceName,String serviceMethod) {
			Map<String, String> map = new HashMap<>();
	    	map.put(CORRELATION_ID, MDC.get(CORRELATION_ID));
	    	map.put(HTTP_METHOD, httpMethod);
	    	map.put(SERVICE_NAME, serviceName);
	    	map.put(SERVICE_METHOD, serviceMethod);
	    	setMDC(map);
	}
}
