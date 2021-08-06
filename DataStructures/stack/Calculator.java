package com.tunan.stack;

public class Calculator {

	public static void main(String[] args) {
		//给出一个表达式
		String expression = "7*2*2-5+1-5+3-4/2";//如何处理多位数的问题
		//创建两个栈，数栈和符号栈
		calStack numStack = new calStack(10);
		calStack operStack = new calStack(10);
		//定义相关变量
		int index = 0;//扫描用
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' ';//将每次扫描得到char保存到ch中
		String keepNum = "";//用于拼接多位数
		//开始进行while扫描
		while(true) {
			//依次得到expression中的每一位,使用字符串的方法
			ch = expression.substring(index, index+1).charAt(0);
			//判断这一位是数还是符号
			if(operStack.isOper(ch)) {//如果是运算符
				if(!operStack.isEmpty()){
					//不为空需要判断优先级
					//优先级小于等于栈顶优先级，则需pop出相应的操作
					if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						//把运算结果入数栈
						numStack.push(res);
						//然后把当前符号入符号栈
						operStack.push(ch);
						
					} else {//否则直接入栈
						operStack.push(ch);
					}
					
				}else {//为空直接入栈
					operStack.push(ch);
				}
				
			} else {//如果是数，直接入数栈
				//numStack.push(ch - 48); //'1' -> 1   ***对照acsll码，字符和数字差了48
				//考虑多位数的情况，需要对index后面继续扫描，如果是数，则需要拼接
				keepNum += ch;
				if(index == expression.length() - 1){
					numStack.push(Integer.parseInt(keepNum));
				} else{
					if(operStack.isOper(expression.substring(index+1, index+2).charAt(0))) {
						//字符串转int，“123”->123
						numStack.push(Integer.parseInt(keepNum));
						//必须把keepNum清空！！！
						keepNum = "";
					}
				}
				
			}
			index++;
			if(index >= expression.length()) {
				break;
			}
		}
		
		//当扫描完毕，就从数栈和符号栈pop出相应的进行运算即可
		while(true) {
			//如果符号栈为空，则计算结束---》数栈中的仅存一位就是结果
			if(operStack.isEmpty()){
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		System.out.printf("表达式 %s = %d\n", expression, numStack.pop());
	}
}


//创建一个栈，增加计算器所需功能
class calStack {
	private int maxSize; //栈的最大容量
	private int[] stack;//栈的数据存放于该数组
	private int top = -1;//表示栈顶，初始化为-1
	
	public calStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[maxSize];
	}
	//查看栈顶值但不执行出栈操作
	public int peek() {
		if(isEmpty()) {
			//抛出异常
			throw new RuntimeException("栈空，无法进行出栈操作");
		}
		int res = stack[top];
		return res;
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
	//返回操作符的优先级，用数字表示
	//数字越大，优先级越高
	public int priority(int oper) {
		if(oper == '*' || oper == '/'){
			return 1;
		} else if(oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1;
		}
	}
	//判断是否是运算符
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}
	//判断是否是数值
	//不是运算符就是数值
	
	//计算方法
	public int cal(int num1, int num2, int oper){
		int res = 0;//用于存放结果
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;//注意顺序
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;
			break;

		default:
			break;
		}
		return res;
	}
	
}