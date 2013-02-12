package bbc.forge.music.monitoring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class DatabaseExceptionResolver implements HandlerExceptionResolver {

	@Autowired
	DatabaseMonitoring dbMon;
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
		
		if(e.getClass().equals(DataAccessResourceFailureException.class))
			dbMon.setDatabaseUnavailable();
		
		return null;
	}

}
