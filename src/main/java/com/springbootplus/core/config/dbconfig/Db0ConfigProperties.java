package com.springbootplus.core.config.dbconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "db0")
public class Db0ConfigProperties {
	private String[] mapperLocations;
    private String url;
    private String driver;
    private String username;
    private String password;
	public String[] getMapperLocations() {
		return mapperLocations;
	}
	public void setMapperLocations(String[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
