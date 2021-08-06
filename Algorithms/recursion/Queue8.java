package com.tunan.recursion;

public class Queue8 {

	int max = 8;//定义一共有多少个皇后
	int[] array = new int[max];//用于保存皇后摆放位置，比如array={0, 4, 7, 5, 2, 6, 1, 3}
	static int count = 0;//解法
	static int judgeCount = 0;//判断次数
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue8 queue8 = new Queue8();
		queue8.check(0);
		System.out.println("执行完毕");
		System.out.printf("一共有%d种解法",count);
		System.out.println();
		System.out.printf("一共判断%d次",judgeCount);

	}
	
	//回溯方法
	private void check(int n) {
		if(n == max){ // 8个皇后已经放好
			print();
			return;
		}
		for (int i = 0; i < max; i++){
			array[n] = i;//将皇后放到第i列
			if(judge(n)){//不冲突的话就开始递归下一个
				check(n+1);
			}
			//将n向i+1列移动，继续寻找后续可能解	
		}
	}
	
	//检查皇后是否存在冲突
	/**
	 * 
	 * @param n	表示第n个皇后
	 * @return
	 */
	private boolean judge(int n){
		judgeCount++;
		for(int i = 0; i < n; i++){
			//1、array[i] == array[n] 表示第n个皇后与前面的皇后在同一列
			//2、Math.abs(n-i) == Math.abs(array[n] - array[i])表示在同一斜线
			if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
				return false;
			}
		}
		return true;
	}
	
	//输出方法
	private void print(){
		count++;
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

}
