package com.themscode.api.gateway.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTimelapsedCapturer extends ZuulFilter  {
	
	private static Logger log = LoggerFactory.getLogger(PreTimelapsedCapturer.class);

	
	public boolean shouldFilter() {
		return true;
	}

	
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request routed at %s", request.getMethod(), request.getRequestURL().toString()));
		
		Long initialTime = System.currentTimeMillis();
		request.setAttribute("initialTime", initialTime);
		return null;
	}

	
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	
	public int filterOrder() {
		return 1;
	}

}
