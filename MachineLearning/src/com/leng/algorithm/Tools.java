package com.leng.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Tools {
	/**
	 * 求n的阶乘
	 * @param n
	 * @return
	 */
	public static int factorial(int n){
		if (n == 0) {
			return 1;
		}
		return n * factorial(n - 1);
	}
	
	/**
	 * 组合
	 */
	public static Set<String> combination(int n,int length){
		Set<String> combination = new TreeSet<String>();
		List<Integer> close = new ArrayList<Integer>();
		Random random = new Random();
		int count = factorial(n);
		int addCount = 0;
		StringBuilder sb = new StringBuilder();
		while (combination.size() < count) {
			int index = random.nextInt(n);
			if (close.contains(index)) {
				continue;
			}
			sb.append(index);
			addCount ++;
			close.add(index);
			if (addCount == length) {
				combination.add(sb.toString());
				sb.delete(0, sb.length());
				close.clear();
				addCount = 0;
			}else{
				sb.append("#");
			}
		}
		return combination;
	}
	/**
	 * 求平均数
	 * @param values
	 * @return
	 */
	public static double average(int... values){
		checkParams(values);
		int sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += values[i];
		}
		return (float)sum / values.length;
	}
	/**
	 * 中位数 
	 * @param values
	 * @return
	 */
	public static int median(int... values){
		checkParams(values);
		Arrays.sort(values);
		if (values.length % 2 == 0) 
			return (values[values.length / 2] + values[values.length / 2 + 1])/2;
		else
			return values[values.length / 2 + 1];
	}
	/**
	 * 方差(用于衡量数据离散程度)	s2 = ∑i=1,n(xi-average)2/n-1
	 * @param values
	 * @return
	 */
	public static double variance(int... values){
		checkParams(values);
		double average = average(values);
		double variance = 0;
		for (int i = 0; i < values.length; i++) {
			variance += Math.pow((values[i] - average), 2);
		}
		return variance / (values.length - 1);
	}
	/**
	 * 标准差=方差的开平方	s = Math.sqrt(s2);
	 * @param values
	 * @return
	 */
	public static double standardDeviation(int... values){
		checkParams(values);
		return Math.sqrt(variance(values));
	}
	
	private static void checkParams(Object... object){
		if (object == null) {
			throw new RuntimeException("Parameter cannot be null!!");
		}else if (object.length == 0) {
			throw new RuntimeException("Parameter cannot be empty!!");
		}
	}

}
