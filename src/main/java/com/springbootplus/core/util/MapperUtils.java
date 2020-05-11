package com.springbootplus.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class MapperUtils {

	public static Resource[] getResources(String locationPattern) {
		List<String> fileNames = getAllFileName(locationPattern);
		Resource[] resources = new Resource[fileNames.size()];
		for(int i=0;i<fileNames.size();i++){
			resources[i] = new FileSystemResource(fileNames.get(i));
		}
		
		return resources;
	}

	private static List<String> getAllFileName(String locationPattern,List<String> pathList){
	
		pathList = pathList ==null? new ArrayList<String>():pathList;
		String[] locationPatterns  = locationPattern.trim().split(",");
		for(String lp : locationPatterns){
			File file = new File(CommonUtils.getSysPath() +lp.trim());
			String path = file.getPath()+File.separator;
	        String [] names = file.list();
	        if(names != null){
	    		for(int i=0;i<names.length;i++){
	    			pathList.add(path+names[i]);
	    		}
	    			
	    	}
	        File [] files = file.listFiles();//获取一个文件夹下的所有文件
	        for(File a:files){
	    		if(a.isDirectory()){//如果文件夹下有子文件夹，获取子文件夹下的所有文件全路径。
	    			getAllFileName(a.getAbsolutePath()+File.separator,pathList);
	    		}
	    	} 
		
		}
		return pathList;
	}

	/**
	 * 获取目录下的文件路径
	 * 
	 * @param locationPattern：/config/mappers/mappers0,/config/mappers/mappers1
	 * @return
	 */
	private static List<String> getAllFileName(String locationPattern) {

		return getAllFileName(locationPattern, null);

	}

	

	

}
