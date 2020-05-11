package com.springbootplus.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//根据spring.profiles.active的值获取自定义配置信息
public class PropertiesUtils {
	private static Map<String, String> proMap = new HashMap<>();
	private static String activeProfile = SpringContextUtil.getActiveProfile();
	private static String prefix = "application";// 前缀
	private static String suffix = null;// 后缀
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	private PropertiesUtils() {
	}
	/**
	 * 获取自定义properties的值
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getExtPropertiesValue(String key) throws IOException{
		getPropMap();
		return proMap.get(key);
	}
	private static Map<String, String> getPropMap() throws IOException {
		if (0 == proMap.size()) {
			synchronized (PropertiesUtils.class) {
				if (0 == proMap.size()) {
					String basePaht = CommonUtils.getSysPath() + File.separator;
					getSuffixByActiveProfile();// 初始化后缀
					// key 文件名，value 文件路径
					Map<String, String> filePath = new HashMap<String, String>();
					Map<String, Boolean> fileFlag = new HashMap<String, Boolean>();
					// 先获取文件路径并加装文件
					//配置文件优先级顺序
					//加装配置文件需要从低到高，重复属性将被覆盖
					// 最低：在classpath下直接放配置文件。
					getConfigvarPropertiesPath(File.separator, filePath, fileFlag, false);
					// 低：在classpath下建一个config文件夹，然后把配置文件放进去。
					getConfigvarPropertiesPath(File.separator + "config" + File.separator, filePath, fileFlag, false);
					// 中：直接把配置文件放到jar包的同级目录。
					getConfigvarPropertiesPath(basePaht, filePath, fileFlag, true);
					// 高：在jar包的同一目录下建一个config文件夹，然后把配置文件放到这个文件夹下。
					getConfigvarPropertiesPath(basePaht + "config" + File.separator, filePath, fileFlag, true);
					
					
					
					
				}
			}

		}
		return proMap;
	}
	private static void getSuffixByActiveProfile() {
		if (null == activeProfile) {
			suffix = ".properties";
		} else {
			suffix = "-" + SpringContextUtil.getActiveProfile() + ".properties";
		}
	}
	private static void getConfigvarPropertiesPath(String path, Map<String, String> filePath,
			Map<String, Boolean> fileFlag, boolean flag) throws IOException {
		File file = null;
		if(flag){//外部配置文件
			file = new File(path);
		}else{//内部配置文件
			Resource resource = new ClassPathResource(path);
			try {
				file =resource.getFile();
			} catch (Exception e) {
				logger.info(e.getMessage());
				return;
			}
			
		}
		
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				String fileName = tempList[i].getName();
				// 非application开头 并且以 -test.properties结尾
				if (!fileName.startsWith(prefix) && fileName.endsWith(suffix)) {
					readProperties(new FileInputStream(tempList[i]));
				}
			}

		}

	}
	private static void readProperties(InputStream ins) {
			Properties prop = new Properties();
			try {
				
				BufferedReader bf = new BufferedReader(new InputStreamReader(ins));
				prop.load(bf);
				String key;
				Enumeration<?> pro = prop.propertyNames();
				while (pro.hasMoreElements()) {
					key = (String) pro.nextElement();
					String value = prop.getProperty(key);
					proMap.put(key, value);
				}
			} catch (Exception var10) {
				var10.printStackTrace();
			}
	}

	

	

	

}
