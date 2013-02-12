package bbc.forge.music.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;


import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

    public static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	private static DateFormat iso8601Format = new SimpleDateFormat(ISO_8601_DATE_FORMAT, Locale.UK);
	  
	public Object processArrayValue(Object value, JsonConfig config) {
		return process(value, config);
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(value, config);
	}
	
	public Object process(Object value, JsonConfig config) {
		if (value == null) return null;
			
		return iso8601Format.format(value);
		
	}

}
