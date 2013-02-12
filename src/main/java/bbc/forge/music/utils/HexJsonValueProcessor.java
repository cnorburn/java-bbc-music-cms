package bbc.forge.music.utils;

import bbc.forge.music.utils.HexUtils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class HexJsonValueProcessor implements JsonValueProcessor  {

	private static HexUtils hex=new HexUtils();
	
	public Object processArrayValue(Object value, JsonConfig config) {
		return process(value, config);
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(value, config);
	}
	
	public Object process(Object value, JsonConfig config) {
		if (value == null) return null;
		
		return hex.getHex((Long) value);		
		
	}

}
