package com.springbootplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@ServletComponentScan 	//Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
@SpringBootApplication(scanBasePackages = {"com.springbootplus"})
//@EnableScheduling 		//开启调度 
@EnableSwagger2			//开启swagger
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(App.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }

}
