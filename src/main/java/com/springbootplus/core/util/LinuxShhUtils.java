package com.springbootplus.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class LinuxShhUtils {
	private static final Logger logger = LoggerFactory.getLogger(LinuxShhUtils.class);
    final static String userName = "root";// 用户名
    final static String password = "wshh@2020$Awr+CentOsBaseCoreBD";// 密码
    final static String host = "47.92.118.168";// 服务器地址
    final static int port = 22;// 端口号
    final static int timeout = 60000000;
    
    /**
     * <dependency>
     * <groupId>com.jcraft</groupId>
     * <artifactId>jsch</artifactId>
     * <version>0.1.55</version>
     * </dependency>
     */
    public static void main(String[] args)  {
        String cmd = "uname -a && date && uptime && who && vmstat 1 60 ";
        //知道端口获取进程PID
        cmd = "lsof -i:8888";
        //知道应用名称获取进程PID
        cmd = "ps -ef | grep typlatform-sjh | grep -v grep";
        
        cmd = "top -Hp 16471 -n 1";
        
        cmd = "ps -eo pid,%cpu,time | grep 13802";
        
        cmd = "ps -eo pid,%cpu,time | sort -k 2 -r -n";
        try {
            String rs =   LinuxShhUtils.exeCommand(host,port,userName,password,cmd);
            System.out.println(rs);

        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//        	LinuxShhUtils.getLog(host,port,userName,password,cmd);
//        } catch (JSchException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    
    public static int exeCommand(String[] cmd){
    	Process process = null;
    	 StringBuffer errInfo = new StringBuffer();
         StringBuffer outInfo = new StringBuffer();
         BufferedReader inisr;
         BufferedReader errisr;
    	try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException var3) {
        	logger.error(var3.getMessage());
            errInfo.append(var3.getMessage());
            return 1;
        }

        inisr = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
        errisr = new BufferedReader(new InputStreamReader(process.getErrorStream()), 1024);

        try {
            process.waitFor();
        } catch (InterruptedException var2) {
        	logger.error(var2.getMessage());
            errInfo.append(var2.getMessage());
            return 1;
        }

        String line2;
        try {
            line2 = "";

            while(inisr != null && (line2 = inisr.readLine()) != null) {
                outInfo.append(line2).append(" \r\n");
            }

            if (outInfo.length() > 1) {
            	logger.info(outInfo.toString());
            }
        } catch (IOException var5) {
        	logger.error(var5.getMessage());
            errInfo.append(var5.getMessage());
            return 1;
        }

        try {
            line2 = "";

            while(true) {
                if (errisr == null || (line2 = errisr.readLine()) == null) {
                    if (errInfo.length() > 1) {
                    	logger.error(errInfo.toString());
                    }
                    break;
                }

                errInfo.append(line2).append(" \r\n");
            }
        } catch (IOException var4) {
        	logger.error(var4.getMessage());
            errInfo.append(var4.getMessage());
            return 1;
        }

        return process.exitValue();
    }
    /**
    *@Author:lw
    *@Description:  实时获取命令日志
     * @param :host,port user,password cmmmand
    *@Date:14:00 2018/8/6
    */
    public static void getLog(String host, int port, String user, String password, String command)throws JSchException, IOException{

        JSch jsch = new JSch(); // 创建JSch对象
        //String cmd = "vmstat 1 1";// 要运行的命
        String cmd = "uname -a && date && uptime && vmstat 1 60 ";  //>/home/linux_shell/m/vmstat.txt
        Session session = jsch.getSession(user, host, port); // 根据用户名，主机ip，端口获取一个Session对象
        session.setPassword(password); // 设置密码
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        channelExec.setCommand(command);
        channelExec.setInputStream(null);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        InputStream in = channelExec.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        //写入相应的文件
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:\\2.txt"),"UTF-8"));
        String buf = null;

        StringBuffer sb = new StringBuffer();

        System.out.println("您的IP是：" + host);
        logger.info("您的IP是：" + host);
        System.out.println("以下是：系统资源信息：");

        while ((buf = reader.readLine()) != null) {

            sb.append(buf);
            //写入相关文件
            out.write(buf);
            out.newLine();
            System.out.println(buf);// 打印控制台输出
        }
        //清楚缓存
        out.flush();
        //关闭流
        reader.close();
        out.close();
        channelExec.disconnect();
        if (null != session) {
            session.disconnect();
        }

    }

    /**
    *@Author:lw
    *@Description:  去除全部结果后，才显示处理，不是实时获取数据
    *@Date:13:53 2018/8/6
    */
    public static String exeCommand(String host, int port, String user, String password, String command) throws JSchException, IOException {

        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();
        session.setTimeout(timeout); // 设置timeout时间
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        String out = IOUtils.toString(in, "UTF-8");
        channelExec.disconnect();
        session.disconnect();
        return out;
    }
}
