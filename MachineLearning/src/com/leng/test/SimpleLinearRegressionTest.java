package com.leng.test;

import com.leng.algorithm.regression.SimpleLinearRegression;

/**
 * 简单线性回归
 * @author carry
 *
 */
public class SimpleLinearRegressionTest {
	
	public static void main(String[] args) {
		SimpleLinearRegression simple = new SimpleLinearRegression();
		simple.fit(new int[]{1,3,2,1,3}, new int[]{14,24,18,17,27});
		System.out.println(simple.compute(3));
	}

}
