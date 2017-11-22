package com.leng.algorithm.knn;

import java.util.HashMap;

public class KNN {
	/**
	 * 计算特征值的距离
	 * @param length
	 * @param values
	 * @return
	 */
	public static double computeEuclideanDistance(int length,double... values){
		if (values == null) {
			return -1;
		}
		if (values.length / length != 2) {
			return -1;
		}
		double sqrt = 0;
		for (int i = 0; i < values.length / 2; i++) {
			sqrt += Math.pow(values[i] - values[i + length], 2);
		}
		return Math.sqrt(sqrt);
	}
	/**
	 * k-近邻算法，矩阵的最后一位为类型
	 * @param board
	 * @param unknown
	 * @param k
	 * @return
	 */
	public static double knn(double[][] board,double[] unknown,int k){
		if (board == null || unknown == null || board[0].length != unknown.length || k % 2 ==0) {
			if (k % 2 ==0) {
				System.err.println("k is oushu");
			}
			return -1;
		}
		return execute(board, unknown, k);
	}
	/**
	 * k-近邻算法，矩阵的最后一位为类型
	 * @param board
	 * @param unknown
	 * @param k
	 * @return
	 */
	public static int knn(int[][] board,int[] unknown,int k){
		if (board == null || unknown == null || board[0].length != unknown.length || k % 2 ==0) {
			if (k % 2 ==0) {
				System.err.println("k is oushu");
			}
			return -1;
		}
		double[][] copyBoard = new double[board.length][board[0].length];
		for (int i = 0; i < copyBoard.length; i++) {
			for (int j = 0; j < copyBoard[0].length; j++) {
				copyBoard[i][j] = board[i][j];
			}
		}
		double[] copyUnknown = new double[unknown.length];
		for (int i = 0; i < copyUnknown.length; i++) {
			copyUnknown[i] = unknown[i];
		}
		return (int) execute(copyBoard, copyUnknown, k);
	}
	/**
	 * 测试算法的准确率
	 * @param split 测试数据的比例0~1
	 * @param datas 样本总数
	 * @param k 
	 * @return
	 */
	public static float test(float split,double[][] datas,int k){
		if (datas == null || split == 0 || split >= 1) {
			return 0;
		}
		return executeTest(split, datas, k);
	}
	
	public static float test(float split,int[][] datas,int k){
		if (datas == null || split == 0 || split >= 1) {
			return 0;
		}
		double[][] copy = new double[datas.length][datas[0].length];
		for (int i = 0; i < datas.length; i++) {
			for (int j = 0; j < datas[0].length; j++) {
				copy[i][j] = datas[i][j];
			}
		}
		return executeTest(split, copy, k);
	}
	
	private static float executeTest(float split,double[][] datas,int k){
		Type yangben = new Type();
		Type test = new Type();
		for (int i = 0; i < datas.length; i++) {
			double[] raw = datas[i];
			if (Math.random() < split) {
				test.add(raw);
			}else{
				yangben.add(raw);
			}
		}
		double[][] yangbenData = yangben.get();
		double[][] testData = test.get();
		if (testData.length==0) {
			System.out.println("test data is 0!!");
			return executeTest(split, datas, k);
		}
		int result = 0;
		for (int i = 0; i < testData.length; i++) {
			double type = knn(yangbenData, testData[i], k);
			if (type == testData[i][testData[i].length -1]) {
				result++;
			}
		}
		return (float)result / testData.length;
	}
	
	private static class Type{
		
		private double[][] board = new double[5][];
		
		private int index = 0;
		
		public void add(double[] data){
			if (index <= board.length -1) {
				board[index] = data;
				index++;
			}else{
				double[][] copy = board;
				board = new double[copy.length + 5][];
				for (int i = 0; i < copy.length; i++) {
					board[i] = copy[i];
				}
				board[index] = data;
				index++;
			}
		}
		public double[][] get(){
			double[][] copy = new double[index][];
			for (int i = 0; i < copy.length; i++) {
				copy[i] = board[i];
			}
			return copy;
		}
	}
	
	private static double execute(double[][] board,double[] unknown,int k) {
		HashMap<Double, Integer> stack = new HashMap<Double, Integer>();
		double[] raw = new double[(unknown.length - 1) * 2];
		//找出距离最近的点
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length - 1; j++) {
				raw[j] = board[i][j];
				raw[j + unknown.length -1] = unknown[j];
			}
			showRaw(raw);
			double juli = computeEuclideanDistance(unknown.length - 1, raw);
			System.out.println("juli::" + juli);
			if (stack.size() < k) {
				stack.put(juli, i);
			}else{
				double max = -1;
				for (double d : stack.keySet()) {
					if (max < d) {
						max = d;
					}
				}
				if (max > juli) {
					stack.remove(max);
					stack.put(juli, i);
				}
			}
			System.out.println(stack);
		}
		//统计每种类型出现的次数
		HashMap<Double, Integer> count = new HashMap<Double, Integer>();
		for (double d : stack.keySet()) {
			double tezheng = board[stack.get(d)][unknown.length -1];
			//count.put(tezheng, count.get(tezheng) + 1);
			if (count.containsKey(tezheng)) {
				count.put(tezheng, count.get(tezheng) + 1);
			}else{
				count.put(tezheng, 1);
			}
		}
		System.out.println(count);
		//找出出现最大次数的类型
		double result = -1;
		int max = -1;
		for (double i : count.keySet()) {
			if (max < count.get(i)) {
				max = count.get(i);
				result = i;
			}
		}
		return result;
	}
	
	private static void showRaw(double[] raw){
		StringBuilder sb = new StringBuilder();
		sb.append("raw=");
		for (int i = 0; i < raw.length; i++) {
			sb.append(raw[i]);
			if (i < raw.length -1) {
				sb.append(",");
			}
		}
		System.out.println(sb.toString());
	}

}
