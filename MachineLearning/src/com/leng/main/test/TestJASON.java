package com.leng.main.test;

import com.leng.library.json.JSONObject;


public class TestJASON {
	
	public static void main(String[] args) {
		JSONObject job = new JSONObject();
		job.put("a", 1);
		job.put("b", 5L);
		job.put("c", 3.5);
		job.put("d", 4.5f);
		job.put("e", "value");
		System.out.println(job.toString());
		System.out.println(job.getString("e"));
		System.out.println(job.getInt("a"));
		System.out.println(job.getDouble("c"));
		System.out.println(job.getLong("b"));
	}

}
