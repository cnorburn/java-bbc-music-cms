package bbc.forge.music.monitoring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DatabaseMonitoringHandlerInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	DatabaseMonitoring dbMon;
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) {
		dbMon.setDatabaseAvailable();
	}

}
