package com.tunan.linkedklist;

public class Josepfu {

	public static void main(String[] args) {
		//测试添加显示小孩
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.add(5);
		circleSingleLinkedList.showBoy();
		
		//测试小孩出圈
		circleSingleLinkedList.countBoy(1, 2, 5); 

	}

}

//创建一个单向环形链表类
class CircleSingleLinkedList {
	//创建一个first节点
	private Boy first = new Boy(-1);
	//添加新的boy,构成一个环形链表
	public void add(int nums){
		//首先对nums进行一个校验
		if(nums < 1) {
			System.out.println("nums 输入不正确");
		}
		Boy curBoy = null;
		//
		for (int i = 1; i < nums+1; i++) {
			Boy boy = new Boy(i);
			
			if(i == 1){//创建第一个小孩
				first = boy;
				first.setNext(first);
				curBoy = first;
			} else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;
			}
		}
	}
	
	//遍历环形链表输出
	public void showBoy() {
		if (first == null) {
			System.out.println("链表为空");
			return;
		}
		Boy curBoy = first;
		while(true) {
			
			System.out.printf("当前小孩编号是 %d \n", curBoy.getNo());
			if (curBoy.getNext() == first) {//说明遍历完毕
				break;
			}
			
			curBoy = curBoy.getNext();//后移curBoy
		}
	}
	
	//根据用户输入，计算小孩出圈顺序
	/**
	 * 
	 * @param startNo 表示第几个小孩开始数数
	 * @param countNum 表示数几下
	 * @param nums 表示共有几个小孩，进行校验
	 */
	public void countBoy(int startNo, int countNum, int nums){
		if(first == null || startNo < 1 || startNo > nums) {
			System.out.println("输入参数有误");
		}
		//创建一个辅助指针,应当指向最后一个节点位置
		Boy helper = first;
		while(true) {
			if(helper.getNext() == first){//说明已经指向了最后一个节点
				break;
			}
			helper = helper.getNext();	
		}
		
		//小孩报数前，先让first和helper移动startNo-1次
		for(int j = 0; j < startNo - 1; j++){
			first = first.getNext();
			helper = helper.getNext();
		}
		//开始报数，first和helper同时移动countNum-1 次，然后出圈
		//循环操作，直到圈中只有一个节点
		while(true){
			if(helper == first){
				break;
			}
			//开始 移动countNum-1 次，然后出圈
			for (int k = 0; k < countNum - 1; k++){
				first = first.getNext();
				helper = helper.getNext();
			}
			System.out.printf("编号 %d 小孩出圈 \n", first.getNo());
			//修改圈
			first = first.getNext();
			helper.setNext(first);
		}
		System.out.printf("最后留在圈中的小孩编号 %d \n", first.getNo());
	}
}


//创建一个Boy类，表示一个节点
class Boy {
	
	private int no;//编号
	private Boy next;//指向下一个节点
	
	public Boy(int no) {
		
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}
	
	
}