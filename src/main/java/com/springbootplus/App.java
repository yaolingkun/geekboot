package com.springbootplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.springbootplus"})
@EnableScheduling
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(App.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }

}
