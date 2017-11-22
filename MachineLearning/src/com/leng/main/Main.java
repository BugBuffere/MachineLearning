package com.leng.main;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		System.out.println(lucky(6, 4));
	}
	/**
	 * 123
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public static int lucky(int n,int m){
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		ArrayList<Integer> luckyList = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			luckyList.add(i);
		}
		int i = 0;
		while (luckyList.size() > 1) {
			i = (i + m) % luckyList.size();
			int remove = luckyList.remove(i-1);
			System.out.println("踢出::" + remove);
		}
		/*for (int j = 0; j < luckyList.size(); j++) {
			i++;
			if (i%m == 0) {
				int remove = luckyList.remove(j);
				i = 0;
				System.out.println("踢出::" + remove);
			}
			if (luckyList.size() ==1) {
				break;
			}
			if (j == luckyList.size() -1) {
				j = -1;
			}
		}*/
		return luckyList.get(0);
	}

}
