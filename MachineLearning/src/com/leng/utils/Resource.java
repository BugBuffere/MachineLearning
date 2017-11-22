package com.leng.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class Resource {
	
	private String gen;
	
	private static Resource res;
	
	private Resource() {
		super();
		gen = System.getProperty("user.dir") + "\\res";
	}
	
	public static Resource asInstance() {
		if (res == null) {
			synchronized (Resource.class) {
				if (res == null) {
					res = new Resource();
				}
			}
		}
		return res;
	}
	
	public String getRoot(){
		return gen;
	}
	
	public String getFilePath(String fileName){
		return gen + "\\" + fileName;
	}
	
	public InputStream openFile(String fileName) throws FileNotFoundException{
		InputStream is = new FileInputStream(new File(getFilePath(fileName)));
		return is;
	}
	
	public OutputStream outputFile(String fileName) throws FileNotFoundException{
		OutputStream os = new FileOutputStream(new File(getFilePath(fileName)));
		return os;
	}
	
	public ImageInputStream openImage(String fileName) throws FileNotFoundException, IOException{
		ImageInputStream iis = new FileImageInputStream(new File(getImageResource(fileName)));
		return iis;
	}
	
	public ImageOutputStream getImageOuput(String fileName) throws FileNotFoundException, IOException{
		ImageOutputStream ios = new FileImageOutputStream(new File(getImageResource(fileName)));
		return ios;
	}

	public String getImageResource(String fileName){
		return gen +"\\image\\" + fileName;
	}
	
	public String getFileResource(String fileName){
		return gen + "\\file\\" + fileName;
	}

}
