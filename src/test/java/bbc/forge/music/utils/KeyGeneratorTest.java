package bbc.forge.music.utils;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.validation.constraints.AssertTrue;

import bbc.forge.music.utils.KeyGenerator;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class KeyGeneratorTest {


	@Test
	public void testGenerateKey(){
	    Logger logger = Logger.getLogger("my.test.logger");
	    StreamHandler sh = new StreamHandler(System.out, new SimpleFormatter());
	    logger.addHandler(sh);
		
		KeyGenerator keygen=new KeyGenerator();
				
		String key=keygen.getUrlKey(99l);
		
		logger.info("Generated Key - " + key);
		
		assertNotNull("key generated",key);
		assertTrue("key length=5", key.length()==5);
		
	    sh.flush();

	}



	
}
