package com.anne.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import com.anne.config.properties.DataSourceProperties;

@Configuration
public class AppConfig {
	private Map<String, DataSourceProperties> datasource = new HashMap<String, DataSourceProperties>();

	public Map<String, DataSourceProperties> getDatasource() {
		return datasource;
	}
}