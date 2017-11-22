package com.leng.algorithm.linearAlgebra;


import java.util.Set;

import com.leng.algorithm.InversionNumber;
import com.leng.algorithm.Tools;

/**
 * 行列式
 * @author carry
 *
 */
public class Determinant {
	
	/**
	 * ∑ = (-1)t*a1p1*a2p2...anpn 
	 * @param matrix
	 * @return
	 */
	public static long determinant(int[][] matrix){
		if (matrix == null) {
			throw new RuntimeException("Parameter cannot be null");
		}
		if (matrix.length != matrix[0].length) {
			throw new RuntimeException("matrix row is not equal to column!!");
		}
		long result = 0;
		Set<String> combination = Tools.combination(matrix.length, matrix[0].length);
		System.out.println(combination);
		for (String str : combination) {
			String[] splitAry = str.split("#");
			long product = 1;
			for (int i = 0; i < splitAry.length; i++) {
				product *= matrix[i][Integer.parseInt(splitAry[i])];
			}
			result += Math.pow(-1, InversionNumber.inversionNumber(str, "#")) * product;
		}
		return result;
	}
	
	/*private static final int SIN = 0;
	
	private static final int COS = 1;
	
	public static long determinant1(int[][] matrix){
		if (matrix.length != matrix[0].length) {
			throw new RuntimeException("matrix row != cols!!");
		}
		long result = 0;
		long sin = 0;
		long cos = 0;
		for (int i = 0; i < matrix.length; i++) {
			cos += calculation(matrix, i, 0, COS, 0);
			System.out.println("cos=" + cos);
			sin += calculation(matrix, i, 0, SIN, 0);
			System.out.println("sin=" + sin);
		}
		result = sin - cos;
		return result;
	}
	
	public static long calculation(int[][] matrix,int x,int y,int direction,int end){
		if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || end >= matrix[0].length) {
			return 1;
		}
		long result = matrix[x][y];
		System.out.println("x=" + x + ", y=" + y + ", result=" + result);
		end++;
		switch (direction) {
		case SIN:
			x += 1;
			y += 1;
			if (y >= matrix[0].length) {
				y = matrix[0].length - y;
			}

			if (x >= matrix.length) {
				x = matrix.length - x;
			}
			if (Math.abs(oldX - x) ==1) {
				return 1;
			}
			result *= calculation(matrix, x, y, direction, end);
			break;
		case COS:
			x -= 1;
			y += 1;
			if (x < 0) {
				x = matrix.length + x;
			}	
			result *= calculation(matrix, x, y, direction, end);
			break;
		}
		return result;
	}*/

}
