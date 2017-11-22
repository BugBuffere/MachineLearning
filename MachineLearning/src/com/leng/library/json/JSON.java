package com.leng.library.json;

public class JSON {

	public static JSONException typeMismatch(Object object, String string) {
		
		return new JSONException(object + " can't cast to " + string);
	}

	public static Object checkDouble(double value) {
		return new Double(value);
	}

	public static Boolean toBoolean(Object object) {
		// TODO Auto-generated method stub
		return object == null ? null : object instanceof Boolean ? (Boolean)object : null;
	}

	public static JSONException typeMismatch(String name, Object object,
			String string) {
		// TODO Auto-generated method stub
		return new JSONException("key=" + name + " value=" + object + " can't cast to " + string);
	}

	public static Double toDouble(Object object) {
		// TODO Auto-generated method stub
		return object == null ? null : object instanceof Double ? (Double)object : null;
	}

	public static Integer toInteger(Object object) {
		// TODO Auto-generated method stub
		return object == null ? null : object instanceof Integer ? (Integer)object : null;
	}

	public static Long toLong(Object object) {
		// TODO Auto-generated method stub
		return object == null ? null : object instanceof Long ? (Long)object : null;
	}

	public static String toString(Object object) {
		// TODO Auto-generated method stub
		return object == null ? null : object instanceof String ? (String)object : null;
	}

	public static JSONException typeMismatch(int index, Object object, String string) {
		// TODO Auto-generated method stub
		return new JSONException("index=" + index + " value=" + object + " can't cast to" + string);
	}

}
