package com.springbootplus.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
@Component
@ConditionalOnProperty(prefix = "applicationPID",name = "open" ,havingValue = "true")
public class ApplicationPIDConfig {
	@Value("${spring.pid.file}")
    private String pid_file_path;

	public String getPidFilePath() {
		return pid_file_path;
	}

	public void setPidFilePath(String pid_file_path) {
		this.pid_file_path = pid_file_path;
	}
	
}
