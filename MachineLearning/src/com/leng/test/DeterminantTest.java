package com.leng.test;

import com.leng.algorithm.linearAlgebra.Determinant;

public class DeterminantTest {
	
	public static void main(String[] args) {
		int[][] matrix = new int[4][4];
		matrix[0][0] = 3;
		matrix[0][1] = 1;
		matrix[0][2] = 1;
		matrix[0][3] = 1;
		matrix[1][0] = 1;
		matrix[1][1] = 3;
		matrix[1][2] = 1;
		matrix[1][3] = 1;
		matrix[2][0] = 1;
		matrix[2][1] = 1;
		matrix[2][2] = 3;
		matrix[2][3] = 1;
		matrix[3][0] = 1;
		matrix[3][1] = 1;
		matrix[3][2] = 1;
		matrix[3][3] = 3;
		
		System.out.println(Determinant.determinant(matrix));
	}

}
