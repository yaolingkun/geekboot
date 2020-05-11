package com.springbootplus.core.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springbootplus.core.config.ApplicationPIDConfig;
import com.springbootplus.core.util.FileUtils;
import com.springbootplus.core.util.LinuxShhUtils;

@Component
public class ProcessScheduler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	@Autowired
	private ApplicationPIDConfig applicationPIDConfig;
	
    @Scheduled(cron="0 0/1 * * * ?") //每分钟执行一次  
    public void statusCheck() {      
        logger.info("每分钟执行一次。开始……");  
        //statusTask.healthCheck();  
//        String pid_path = applicationPIDConfig.getPidFilePath();
//        String pid = FileUtils.getPid(pid_path);
//        String[] cmd = new String[] { "/bin/bash", "-c", " ps -eo pid,%cpu,time | grep  "+pid };
//        LinuxShhUtils.exeCommand( cmd);
        logger.info("每分钟执行一次。结束。");  
    }    
  
    @Scheduled(fixedRate=20000)  
    public void testTasks() {      
        logger.info("每20秒执行一次。开始……");  
        //statusTask.healthCheck();  
//        String pid_path = applicationPIDConfig.getPidFilePath();
//        String pid = FileUtils.getPid(pid_path);
//        String[] cmd = new String[] {  " ps -eo pid,%cpu,time | grep  "+pid };
//        LinuxShhUtils.exeCommand( cmd);
        logger.info("每20秒执行一次。结束。");  
    }
}
