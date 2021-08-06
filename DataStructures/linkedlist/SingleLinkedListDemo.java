package com.tunan.linkedklist;

import java.util.Stack;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		//测试
		//先创建几个节点
		HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		
		//顺序加入
		SingleLinkedList singleLinkedList = new SingleLinkedList();
//		singleLinkedList.add(hero1);
//		singleLinkedList.add(hero2);
//		singleLinkedList.add(hero3);
//		singleLinkedList.add(hero4);
		
		//按编号加入
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero3);
		
		singleLinkedList.list();
		System.out.println();
		System.out.println(hero1.next);
		System.out.println();
		
		//测试修改节点的代码
		System.out.println("测试修改");
		HeroNode newhero = new HeroNode(2, "小卢", "小麒麟");
		singleLinkedList.update(newhero);
		
		//显示
		singleLinkedList.list();
		
		//删除测试
		System.out.println("测试删除");
		singleLinkedList.del(1);
		System.out.println();
		singleLinkedList.list();
		
		//链表中有效节点的个数
		System.out.println("测试有效节点个数");
		System.out.println("当前链表有效长度为" + getLength(singleLinkedList.getHead()));
		
		//获取倒数第k个节点
		System.out.println("测试获取倒数第k个节点");
		HeroNode res = findNode(singleLinkedList.getHead(), 2);
		System.out.println("所查询节点是" + res);
		
		//测试单链表反转
		System.out.println("测试反转，改变了链表结构");
		reverseList(singleLinkedList.getHead());
		singleLinkedList.list();
		
		//测试逆序打印
		System.out.println("测试逆序打印，没有改变链表结构");
		reversePrint(singleLinkedList.getHead());
	}
	
	
	//从尾到头打印单链表
	//使用栈的数据结构来进行逆序打印
	public static void reversePrint(HeroNode head) {
		if(head.next == null) {
			 return;
		}
		
		//创建一个栈，将各个节点压入栈
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode cur = head.next;
		
		//将链表的所有节点压入栈
		while(cur != null){
			stack.push(cur);
			cur = cur.next;
		}
		//将栈中的节点进行打印，pop出栈
		while(stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}
	
	
	//反转单链表（腾讯面试题）
	//1、先定义一个节点，reverseHead = new HeroNode();
	//2、从头到尾遍历链表，每遍历一个节点，就取出它，放在新的链表最前端
	//3、原来链表的head.next = reverseHead.next
	public static void reverseList(HeroNode head) {
		//无节点或者只有一个节点
		if(head.next == null || head.next.next == null) {
			 return;
		}
		
		//定义辅助指针，遍历原先的链表
		HeroNode cur = head.next;
		HeroNode next = null;//指向当前节点（cur）的下一个节点
		HeroNode reverseHead = new HeroNode(0, "", "");
		
		//遍历原先的链表
		while(cur != null) {
			next = cur.next;//暂时保存当前节点的下一个节点
			cur.next = reverseHead.next;//将cur下一个节点指向新链表的最前端
			reverseHead.next = cur;
			cur = next;
		}
		//将head.next 指向 reverseHead.next，实现反转
		head.next = reverseHead.next;
	}
	
	//查找单链表中倒数第k个节点【新浪面试题】
	//思路：
	//1、编写方法，接受单链表head，同时接受k
	//2、遍历链表得到长度length
	//3、再次从第一个遍历 到length-k个就可以得到该节点
	//4、找到了就返回节点，找不到就返回null
	
	public static HeroNode findNode(HeroNode head, int k){
		//如果链表为空，返回null
		if (head.next == null){
			return null;
		}
		int length = getLength(head);
		
		//先做一个k的校验
		if(k<=0 || k> length){
			return null;
		}
		HeroNode temp = head.next;
		for (int i = 0; i < length-k; i++){
			temp = temp.next;
		}
		return temp;
	}
	
	
	//去掉头节点，统计链表有效长度
	public static int getLength(HeroNode head) {
		if (head.next == null){
			return 0;
		}
		int length = 0;
		//定义一个辅助变量
		HeroNode cur = head.next;
		while(cur != null) {
			length++;
			cur = cur.next;
		}
		return length;
	}

}


//定义一个SingleLinkedList，管理水浒英雄
class SingleLinkedList {
	//先初始化一个头节点，头节点不要动，不存放具体数据
	private HeroNode head = new HeroNode(0,"", "");
	
	
	//返回头节点
	public HeroNode getHead() {
		return head;
	}

	//添加节点到单向链表方法
	//当不考虑编号顺序时。找到当前链表最后节点，将这个节点next指向新的节点
	public void add(HeroNode heroNode) {
		
		//因为head节点不能动，因此需要一个辅助变量temp
		HeroNode temp = head;
		//遍历链表，找到最后
		while(true) {
			//找到链表最后
			if(temp.next == null){
				break;
			}
			//如果没有找到最后，就将temp后移
			temp = temp.next;
		}
		//当退出while循环时，temp就指向了链表最后
		temp.next = heroNode;
	}
	
	//第二种添加方法，根据排名进行添加
	public void addByOrder(HeroNode heroNode) {
		//因为head不能动，因此需要辅助变量来遍历
		//因为是单链表，因此我们的temp 是位于添加位置的前一个节点，否则插入失败
		HeroNode temp = head;
		boolean flag = false;//标识英雄编号是否存在，默认false
		while (true) {
			if(temp.next == null){//说明temp已经在链表最后
				break;
			}
			if(temp.next.no > heroNode.no) {//位置找到，就在temp后面插入
				break;
			} else if(temp.next.no == heroNode.no){//位置已被占用，不能添加
				flag = true;//编号存在
				break;
			}
			temp = temp.next;//后移，遍历当前列表
		}
		//判断flag的值
		if(flag){
			System.out.printf("准备插入的英雄编号%d已被占用,不能加入\n",heroNode.no);
		} else {
			//插入到链表，temp的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}
	
	//修改节点信息，根据新节点的编号进行修改
	public void update(HeroNode newHeroNode) {
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//根据no编号，找到需要修改的节点
		HeroNode temp = head.next;
		boolean flag = false;//表示是否找到该节点
		while(true) {
			if(temp == null) {
				break;//链表遍历完毕
			}
			if(temp.no == newHeroNode.no) {//找到目标节点
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//根据flag判断是否找到目标节点
		if(flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else {//没有找到
			System.out.printf("没有找到 编号%d 的节点，不能修改", newHeroNode.no);
		}
	}
	
	
	//删除节点，思路
	//1、head不能动，需要temp辅助节点，找到待删除节点的前一个节点
	//2、说明我们在比较时，是temp.next.no和待删除节点的no比较
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false;//标志是否找到待删除的节点的前一个节点
		while(true) {
			if(temp.next == null) {//已经到链表最后了
				break;
			}
			if(temp.next.no == no) {
				//找到了待删除节点的前一个temp
				flag = true;
				break;
			}
			temp = temp.next;//temp后移，遍历
		}
		if(flag) {
			temp.next =  temp.next.next;
		} else {
			System.out.printf("没有找到要删除的%d 节点\n", no);
		}
	}
	
	//显示链表，遍历
	public void list() {
		//判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为head不能动，因此需要辅助变量来遍历
		HeroNode temp = head.next;
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

//定义一个HeroNode，每一个HeroNode对象就是一个节点
class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;//指向下一个节点
	
	//构造器
	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	
	//为了显示方便，重写toString方法
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}
