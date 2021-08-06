package com.tunan.stack;

import java.util.Scanner;

public class ArrayStackDemo {

	public static void main(String[] args) {

		//测试
		ArrayStack arrayStack = new ArrayStack(4);
		String key = "";
		boolean loop = true;
		Scanner scanner = new Scanner(System.in);
		
		while(loop) {
			System.out.println("show: 显示栈");
			System.out.println("exit: 退出栈");
			System.out.println("push: 入栈");
			System.out.println("pop: 出栈");
			System.out.println("请输入你的选择");
			key = scanner.next();
			switch (key) {
			case "show":
				arrayStack.showStack();
				break;
			case "push":
				System.out.println("请输入入栈参数");
				int value = scanner.nextInt();
				arrayStack.push(value);
				break;
			case "pop":
				try {
					int res = arrayStack.pop();
					System.out.printf("出栈数据是%d \n", res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "exit":
				scanner.close();
				loop = false;
				System.out.println("退出成功");
				break;

			default:
				break;
			}
		}
		

	}

}

//创建一个 ArrayStack 类，数组模拟栈
class ArrayStack {
	private int maxSize; //栈的最大容量
	private int[] stack;//栈的数据存放于该数组
	private int top = -1;//表示栈顶，初始化为-1
	
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[maxSize];
	}
	//判断栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}
	//判断栈空
	public boolean isEmpty() {
		return top == -1;
	}
	//入栈操作
	public void push(int val) {
		if(isFull()) {
			System.out.println("栈满，无法进行入栈操作");
			return;
		}
		top++;
		stack[top]=val;
	}
	//出栈操作
	public int pop() {
		if(isEmpty()) {
			//抛出异常
			throw new RuntimeException("栈空，无法进行出栈操作");
		}
		int res = stack[top];
		top--;
		return res;
	}
	//遍历栈
	public void showStack(){
		if(isEmpty()) {
			System.out.println("栈空");
			return;
		}
		for (int j=top; j>-1;j--){
			System.out.printf("stack[%d] = %d \n", j, stack[j]);
		}
	}
	
	
}