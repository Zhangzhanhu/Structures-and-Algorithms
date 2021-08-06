package com.tunan.queue;

import java.util.Scanner;

public class CircleArrayQueue {

	public static void main(String[] args) {
		// 测试
		//创建一个队列
		System.out.println("测试环形队列");
		CircleArray arrayQueue = new CircleArray(4);//说明：设置为4，该队列有效数据最大为3，有一位预留
		char key = ' ';//接收用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			System.out.println("s(show)：显示队列");
			System.out.println("e(exit)：退出程序");
			System.out.println("a(add)：添加数据");
			System.out.println("g(get)：取出数据");
			System.out.println("h(head)：查看头部");
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				arrayQueue.showQueue();
				break;
			case 'a':
				System.out.println("请输入一个数");
				int value = scanner.nextInt();
				arrayQueue.addQueue(value);
				break;
			case 'g':
				try {
					int res = arrayQueue.getQueue();
					System.out.println("取出的数为" + res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int res = arrayQueue.headQueue();
					System.out.println("队列头的数为" + res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'e':
				scanner.close();
				loop = false;
				System.out.println("程序退出");
				break;

			default:
				break;
			}
		}

	}

}

class CircleArray {
	private int maxSize;//队列最大容量
	//front指向队列第一个元素，初始值是0
	private int front;//队列头
	//rear指向队列最后一个元素的后一个位置，希望预留一个位置作为约定
	private int rear;//队列尾
	private int[] arr;//用于存放数据的数组，模拟队列
	
	//创建队列的构造器
	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}
	
	//判断队列是否满 
	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return rear == front;
	}
	
	//添加数据到队列
	public void addQueue(int n) {
		//判断是否满
		if(isFull()) {
			System.out.println("队列已满，不能加入数据");
			return;
		}
		//直接将数据加入
		arr[rear] = n;
		rear = (rear + 1) % maxSize;//将rear后移，必须考虑取模
	}
	
	//获取队列数据，数据出队列
	public int getQueue() {
		//判断是否空
		if (isEmpty()) {
			throw new RuntimeException("队列空，不能取出数据");
			//这里不再需要return，因为throw已经将程序终止，后面的代码将不会执行
		}
		//需要分析，front是指向队列的第一个元素
		//1、先把front对于的值保存到临时遍历
		//2、将front后移，考虑取模
		//3、将临时变量返回
		int value= arr[front];
		front = (front + 1) % maxSize;
		return value;
	}
	
	//显示队列所有数据
	public void showQueue() {
		if (isEmpty()) {
			System.out.println("数组空");
			return;
		}
		for (int i = front; i <front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}
	
	//求当前队列有效数的个数
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}
	
	//查看头数据，而不把它取出
	public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空，没有数据");
		}
		return arr[front];
	}
}