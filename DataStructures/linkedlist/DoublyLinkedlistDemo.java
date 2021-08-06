package com.tunan.linkedklist;

public class DoublyLinkedlistDemo {

	public static void main(String[] args) {
		// 先创建几个节点
		Node node1 = new Node(1,"老大");
		Node node2 = new Node(2,"老二");
		Node node3 = new Node(3,"老三");
		Node node4 = new Node(4,"老四");
		
		DoublyLinkedlist doublyLinkedlist = new DoublyLinkedlist();
		
		//测试顺序添加
		System.out.println("测试按顺序添加");
		doublyLinkedlist.add(node1);
		doublyLinkedlist.add(node2);
		doublyLinkedlist.add(node3);
		doublyLinkedlist.add(node4);
		doublyLinkedlist.list();
		
		//测试顺序添加
//		System.out.println("测试按编号排序添加");
//		doublyLinkedlist.addByOrder(node1);
//		doublyLinkedlist.addByOrder(node4);
//		doublyLinkedlist.addByOrder(node2);
//		doublyLinkedlist.addByOrder(node3);
//		doublyLinkedlist.list();
		
		//测试修改
		System.out.println("测试修改操作");
		Node newnode = new Node(4,"老幺");
		doublyLinkedlist.update(newnode);
		doublyLinkedlist.list();
		
		//测试修改
		System.out.println("测试删除操作");
		doublyLinkedlist.del(2);
		doublyLinkedlist.list();
	}

}

class DoublyLinkedlist {
	//先初始化一个头节点，头节点不要动，不存放具体数据
	private Node head = new Node(0,"");
	
	//返回头节点
	public Node getHead() {
		return head;
	}
	
	//按顺序添加节点到双向链表
	public void add(Node node) {
		//遍历链表，找到最后的节点，将所添加的节点加到最后
		//使用临时的 指针temp
		Node temp = head;
		while(true) {
			if (temp.next == null){
				break;
			}
			temp = temp.next;
		}
		//当退出while循环时，temp就指向了链表最后，将要添加的node加上即可
		temp.next = node;
		node.pre = temp;
	}
	
	//按节点排序添加
	public void addByOrder(Node node) {
		//因为是双链表，因此temp可以在插入的位置， （区别于单链表）
		Node temp = head;
		boolean flag = false;//标识插入节点位置是否存在，默认false
		
		while(true) {
			if(temp.next == null) {//已经在链表最后
				break;
			}
			if(temp.next.id > node.id) {//位置找到，就在temp后面插入
				break;
			}else if(temp.next.id == node.id){//位置已被占用，不能添加
				flag = true;//编号存在
				break;
			}
			temp = temp.next;
		}
		
		if(flag){
			System.out.printf("准备插入的英雄编号%d已被占用,不能加入\n",node.id);
		} else {
			//插入到链表，temp的后面
			//这里的顺序很有讲究，看一下
			
			node.pre = temp;
			node.next = temp.next;
			temp.next = node;
			temp.next.pre = node; 
		}
	}
	
	//修改节点信息，根据新节点的编号进行修改(和单链表基本一样的操作)
	public void update(Node node) {
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//根据no编号，找到需要修改的节点
		Node temp = head.next;
		boolean flag = false;//表示是否找到该节点
		while(true) {
			if(temp == null) {
				break;//链表遍历完毕
			}
			if(temp.id == node.id) {//找到目标节点
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//根据flag判断是否找到目标节点
		if(flag) {
			temp.name = node.name;
		} else {//没有找到
			System.out.printf("没有找到 编号%d 的节点，不能修改", node.id);
		}
	}
	
	//删除节点，思路
	//1、head不能动，需要temp辅助节点，找到待删除节点，然后进行自我删除
	//2、区别于单链表
	public void del(int id) {
		if(head.next == null) {
			System.out.println("链表为空，不进行删除");
			return;
		}
		Node temp = head.next;//辅助指针，直接指向第一个节点
		boolean flag = false;//标志是否找到待删除的节点的前一个节点
		while(true) {
			if(temp == null) {//已经到链表最后了
				break;
			}
			if(temp.id == id) {
				//找到了待删除节点的temp
				flag = true;
				break;
			}
			temp = temp.next;//temp后移，遍历
		}
		if(flag) {
			temp.pre.next =  temp.next;
			//如果是删除最后一个节点，则不需要执行 下面这句话，否则空指针异常
			if(temp.next != null){
				temp.next.pre = temp.pre;
			}			
		} else {
			System.out.printf("没有找到要删除的%d 节点\n", id);
		}
	}
	
	
	
	//显示链表
	//显示链表，遍历
	public void list() {
		//判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为head不能动，因此需要辅助变量来遍历
		Node temp = head.next;
		while(true) {
			//判断是否到链表最后
			if(temp == null) {
				break;
			}
			//输出节点信息
			System.out.println(temp);
			//将next后移
			temp= temp.next;
		}
	}
}

class Node {
	public int id;
	public String name;
	public Node next;//指向下一个节点
	public Node pre;//指向前一个节点
	
	//构造器
	public Node(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//为了显示方便，重写toString方法
	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + "]";
	}
	
}
