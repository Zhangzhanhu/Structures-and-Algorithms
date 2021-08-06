package com.tunan.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

	public static void main(String[] args) {
		//创建一个队列
		ArrayQueue arrayQueue = new ArrayQueue(3);
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

//使用数组模拟队列，编写一个ArrayQueue类
class ArrayQueue {
	private int maxSize;//队列最大
	private int front;//队列头
	private int rear;//队列尾
	private int[] arr;//用于存放数据的数组，模拟队列
	
	//创建队列的构造器
	public ArrayQueue(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
		front = -1;//指向队列头部前一个位置
		rear = -1;//指向队列尾的具体位置
	}
	
	//判断队列是否满 
	public boolean isFull() {
		return rear == maxSize - 1;
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
		rear++;//让rear后移
		arr[rear] = n;
	}
	
	//获取队列数据，数据出队列
	public int getQueue() {
		//判断是否空
		if (isEmpty()) {
			throw new RuntimeException("队列空，不能取出数据");
			//这里不再需要return，因为throw已经将程序终止，后面的代码将不会执行
		}
		front++;//让front后移
		return arr[front];
	}
	
	//显示队列所有数据
	public void showQueue() {
		//遍历
//		for (int i = front; i < rear; i++) {
//			System.out.println(arr[i]);
//		}
		if (isEmpty()) {
			System.out.println("数组空");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}
	}
	
	//查看头数据，而不把它取出
	public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空，没有数据");
		}
		return arr[front+1];
	}
}