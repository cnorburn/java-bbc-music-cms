package bbc.forge.music.utils;

import net.sf.json.JsonConfig;

import org.springframework.web.servlet.view.json.writer.jsonlib.JsonlibJsonWriterConfiguratorTemplate;

public class PropertyExclusionSojoJsonWriterConfiguratorTemplate extends JsonlibJsonWriterConfiguratorTemplate {
	
	private JsonConfig config;
	
	public PropertyExclusionSojoJsonWriterConfiguratorTemplate() {
		config = new JsonConfig();
	}

	@Override
	public JsonConfig getJsonConfig() {
		return config;
	}

}
