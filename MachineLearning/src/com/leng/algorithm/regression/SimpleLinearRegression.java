package com.leng.algorithm.regression;

import com.leng.algorithm.Tools;

public class SimpleLinearRegression {
	/*
	 * b1 = ∑(xi - average(x))*(yi - average(y)) / ∑(xi - average(x))2
	 * b0 = average(y) - b1 * average(x)
	 * ^y = b0 + b1*x
	 */
	private double b0;
	
	private double b1;
	
	public void fit(int[] data,int[] target){
		if (data == null || target == null) {
			throw new RuntimeException("");
		}
		if (data.length != target.length) {
			throw new RuntimeException("");
		}
		double sum = 0;
		double s2 = 0;
		double averageData = Tools.average(data);
		double averageTarget = Tools.average(target);
		for (int i = 0; i < data.length; i++) {
			sum += (data[i] - averageData) * (target[i] - averageTarget);
			s2 += Math.pow((data[i] - averageData), 2);
		}
		System.out.println("sum=" + sum +", s2=" + s2);
		b1 = sum / s2;
		b0 = averageTarget - b1 * averageData;
	}
	
	public double compute(int data){
		return b0 + b1 * data;
	}

}
