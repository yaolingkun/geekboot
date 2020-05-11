package com.springbootplus.core.config.dbconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "db",name = "open",havingValue = "true")
public class DBConfig {

}
