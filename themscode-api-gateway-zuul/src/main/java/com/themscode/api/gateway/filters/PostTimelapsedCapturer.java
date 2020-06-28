package com.themscode.api.gateway.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTimelapsedCapturer extends ZuulFilter {

private static Logger log = org.slf4j.LoggerFactory.getLogger(PreTimelapsedCapturer.class);

	
	public boolean shouldFilter() {
		return true;
	}

	
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("POST filter", request.getMethod(), request.getRequestURL().toString()));
		
		Long initialTime = (Long) request.getAttribute("initialTime");
		Long finalTime = System.currentTimeMillis();
		Long totalTimeElapsed = finalTime - initialTime;
		
		log.info(String.format("Elapsed time in seconds: %s", totalTimeElapsed.doubleValue() / 1000.00));
		log.info(String.format("Elapsed time in milliseconds: %s", totalTimeElapsed));
		return null;
	}

	
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	
	public int filterOrder() {
		return 2;
	}
}
