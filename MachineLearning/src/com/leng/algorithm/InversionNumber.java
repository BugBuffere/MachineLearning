package com.leng.algorithm;

/**
 * 逆序数
 * @author carry
 *
 */
public class InversionNumber {
	
	public static void main(String[] args) {
		System.out.println(inversionNumber(new int[]{1,2,3}));
		System.out.println(inversionNumber("123", null));
		System.out.println(inversionNumber("1,2,3", ","));
	}
	/**
	 * 求逆序数
	 * @param inversion
	 * @return
	 */
	public static int inversionNumber(int[] inversion){
		if (inversion == null) {
			return -1;
		}
		int result = 0;
		for (int i = 0; i < inversion.length; i++) {
			for (int j = 0; j < i; j++) {
				if (inversion[j] > inversion[i]) {
					result++;
				}
			}
		}
		return result;
	}
	/**
	 * 求逆序数
	 * @param inversion
	 * @return
	 */
	public static int inversionNumber(String inversion,String split){
		if (inversion == null) {
			return -1;
		}
		int[] result;
		if (split == null || "".equals(split) ) {
			result = new int[inversion.length()];
			for (int i = 0; i < result.length; i++) {
				result[i] = Integer.parseInt(inversion.charAt(i)+"");
			}
		}else{
			String[] strAry = inversion.split(split);
			result = new int[strAry.length];
			for (int i = 0; i < strAry.length; i++) {
				result[i] = Integer.parseInt(strAry[i]);
			}
		}
		return inversionNumber(result);
	}

}
