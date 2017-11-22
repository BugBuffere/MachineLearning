package com.leng.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtils {
	
	public static List<String> readFile(String filePath){	
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
			List<String> list = new ArrayList<String>();
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Tools.close(br);
			br = null;
		}
		return null;
	}
	
}
