package com.leng.test;

import java.util.List;

import com.leng.algorithm.knn.KNN;
import com.leng.utils.FileReaderUtils;
import com.leng.utils.Resource;

public class TestKnn {
	
	public static void main(String[] args) {
		test();
	}
	
	public static void test(){
		List<String> dataList = FileReaderUtils.readFile(Resource.asInstance().getFileResource("knntest.txt"));
		System.out.println(dataList);
		int[][] juzhen = new int[dataList.size()][3];
		for (int i = 0; i < juzhen.length; i++) {
			String[] datas = dataList.get(i).split(",");
			if (datas.length == 4) {
				juzhen[i][0] = Integer.parseInt(datas[1]);
				juzhen[i][1] = Integer.parseInt(datas[2]);
				juzhen[i][2] = "Romance".equals(datas[3]) ? 0 : 1;
			}
		}
		int type = KNN.knn(juzhen, new int[]{18,90,-1}, 1);
		System.out.println("type::" + type);
		float ran = KNN.test(0.2f, juzhen, 3);
		System.out.println("rate::" + ran);
	}

}
