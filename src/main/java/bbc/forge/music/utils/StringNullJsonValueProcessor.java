package bbc.forge.music.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class StringNullJsonValueProcessor  implements JsonValueProcessor {
	
	public Object processArrayValue(Object value, JsonConfig config) {
		return process(value, config);
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(value, config);
	}
	
	public Object process(Object value, JsonConfig config) {
		//TODO
		//This needs to return the primitive null instead of string "null"
		if (value == null || value=="null" || "null".equals(value) || "".equals(value) || value=="") return null;
		
		return value;
		
	}


}
