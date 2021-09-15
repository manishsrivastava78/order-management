package com.tcs.eas.rest.apis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tcs.eas.rest.apis.log.LoggingService;
import com.tcs.eas.rest.apis.log.MdcLogging;

@Component
public class ServiceInterceptor extends MdcLogging implements HandlerInterceptor {
	
	@Autowired
	LoggingService loggingService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		loggingService.logRequest(request, handler);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,

			@Nullable ModelAndView modelAndView) throws Exception {
		//System.out.println("---method executed---");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body, Exception ex)
			throws Exception {

		//System.out.println("---Request Completed---" + response.getStatus() + " " + request.getRequestURI());
		// response.getWriter();
		//loggingService.logResponse(httpServletRequest, httpServletResponse, body);
	}

	
	
	
}
