package com.tunan.sparsearray;

public class SparseArray {

	public static void main(String[] args) {
		// 创建一个原始稀疏数组11*11
		//0：表示没有棋子  1：表示黑子  2：表示蓝子
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 1;
		chessArr1[5][3] = 2;
		chessArr1[2][4] = 2;
		//输出原始的稀疏数组
		System.out.println("原始的稀疏数组");
		for(int[] row : chessArr1) {
			for(int data : row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		
		//将稀疏数组转三元数组
		//1、先遍历稀疏数组 得到非0数据的个数
		int sum = 0;
		for (int i = 0; i < chessArr1.length; i++) {
			for (int j = 0; j < chessArr1.length; j++) {
				if (chessArr1[i][j] != 0) {
					sum++;
				}
			}
		}
		//System.out.println("sum= " + sum);
		
		//2、创建三元数组
		int sparseArr[][] = new int[sum+1][3];
		//给三元数组赋值
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;
		
		//遍历稀疏数组，将非零值存放到sparseArr中
		int count = 0;//count用来记录是第几个非0数据
		for (int i = 0; i < chessArr1.length; i++) {
			for (int j = 0; j < chessArr1.length; j++) {
				if (chessArr1[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArr1[i][j];
				}
			}
		}
		
		//输出三元数组的形式
		System.out.println();
		System.out.println("三元数组为");
		for (int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}
		System.out.println();

		//将三元数组返回成稀疏数组
		//1、先读取三元数组第一行，根据第一行数据初始化原始稀疏数组
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		
		//2、读取三元数据后几行的数据（从第二行开始），并赋给原始的稀疏数组即可
		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		
		System.out.println("转换回的稀疏数组");
		for(int[] row : chessArr2) {
			for(int data : row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
	}

}
