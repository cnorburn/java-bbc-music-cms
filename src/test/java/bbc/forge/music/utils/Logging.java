package bbc.forge.music.utils;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class Logging {

	private Logger logger = Logger.getLogger("my.test.logger");

	public void logTest(String msg){

		
		StreamHandler sh = new StreamHandler(System.out, new SimpleFormatter());
	    logger.addHandler(sh);
	
		logger.info("------------- Running Tests - " + msg + " --------------------");
		
		sh.flush();
		  
		
	}

}
