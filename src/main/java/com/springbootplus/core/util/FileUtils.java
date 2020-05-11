package com.springbootplus.core.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	/**
	 * 获取pid
	 * @param fileName
	 * @return
	 */
	public static String getPid(String fileName){
		FileReader fileReader;
		String pid = null;
		try {
			fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
			pid = bufferedReader.readLine();

	        bufferedReader.close();
	        fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return pid;
		
	}
}
