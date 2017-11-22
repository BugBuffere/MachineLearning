package com.leng.utils;

import java.io.Closeable;

public class Tools {
	
	public static void close(Closeable close){
		if (close != null) {
			try {
				close.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
