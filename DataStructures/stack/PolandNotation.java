package com.tunan.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {
		
		//完成将中缀表达式转后缀表达式的操作
		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println(infixExpressionList);
		
		List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
		System.out.println(parseSuffixExpressionList);
		
		System.out.printf("expression %s = %d \n", expression, calculate(parseSuffixExpressionList));
		/*
		//先定义一个逆波兰表达式
		//(3+4)*5-6 ==> 3 4 + 5 * 6 -
		//为了方便，逆波兰表达式 使用空格隔开数字符号
		//String suffixExpression = "3 4 + 5 * 6 - ";
		String suffixExpression = "4 5 * 8 - 60 + 8 2 / + ";
		//先将"3 4 + 5 * 6 - "放到一个ArrayList中
		//然后将ArrayList传递给一个方法，配合栈完成计算
		List<String> rpnList = getListString(suffixExpression);
		System.out.println(rpnList);
		
		int res = calculate(rpnList);
		System.out.println("计算结果是: " + res);
		*/

	}
	
	//中缀list转后缀list
	public static List<String> parseSuffixExpressionList(List<String> ls){
		//定义两个栈
		Stack<String> s1 = new Stack<String>();//符号栈
		//储存中间结果的栈  不必是栈结构，可以用list，因为没有出栈操作
		//Stack<String> s2 = new Stack<String>();
		List<String> s2 = new ArrayList<String>();
		
		//遍历ls
		for (String item: ls){
			//如果是数，直接加入到s2
			if(item.matches("\\d+")){
				s2.add(item);
			} else if(item.equals("(")){
				s1.push(item);
			} else if(item.equals(")")){
				//依次弹出s1的运算符加入到s2，直到遇到左括号为止，将这一对括号丢弃
				while(!s1.peek().equals("(")){
					s2.add(s1.pop());
				}
				s1.pop();//将左括号也弹出哦！！从而消除括号
			} else{
				//当item的优先级小于等于s1栈顶符号时，将s1栈顶运算符弹出加入到s2，再次比较
				while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
					s2.add(s1.pop());
				}
				//还需要将item压入栈
				s1.push(item);
			}
		}
		//将s1中 的依次弹出加入到s2
		while(s1.size() != 0){
			s2.add(s1.pop());
		}
		
		return s2;//s2 是list 因此按顺序输出即可，不需要像栈一样再进行倒序操作
	}
	
	//将中缀转成list
	public static List<String> toInfixExpressionList(String s) {
		List<String> ls = new ArrayList<String>();
		int i = 0;//指针，用于遍历s
		String str;//对多位数的拼接
		char c;//遍历到的字符放入c中
		do {
			//如果c不是一个非数字
			if((c = s.charAt(i)) < 48 ||(c = s.charAt(i)) > 57){
				ls.add("" + c);
				i++;
			}else {//是数字就需要考虑多位数的拼接
				str = "";//str置成空串
				while(i<s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
					str += c;
					i++;
				}
				ls.add(str);
			}
		} while(i < s.length());
		return ls;
	}
	
	//将逆波兰表达式，依次放到ArrayList中
	public static List<String> getListString(String suffixExpression) {
		//将suffixExpression分割
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for(String ele: split){
			list.add(ele);
		}
		return list;
	}
	
	//完成逆波兰表达式的计算
	/*遇到 + 运算符，弹出4和3（4是栈顶元素，3是次栈顶元素），计算出3+4得7，将7入栈
	将5入栈
	下面遇到*运算符，因此弹出5和7，计算7*5得35，将35入栈
	将6入栈
	最后遇到 - 运算符，计算35-6，得最终结果*/
	public static int calculate(List<String> ls){
		
		//只需创建一个栈即可
		Stack<String> stack = new Stack<String>();
		//遍历ls
		for(String item: ls){
			//使用正则表达来取出数
			if(item.matches("\\d+")) {//表示匹配多位数
				//入栈
				stack.push(item);
			} else {
				//pop出两个数进行运算，再将结果入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				switch (item) {
				case "+":
					 res = num1 + num2;
					break;
				case "-":
					res = num1 - num2;
					break;
				case "*":
					res = num1 * num2;
					break;
				case "/":
					res = num1 / num2;
					break;

				default:
					break;
				}
				stack.push(res + "");//int转string
			}
		}
		//最后留在stack中的数是结果
		return Integer.parseInt(stack.pop());
	}

}

//编写一个返回运算符对应优先级的类
class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 3;
	
	public static int getValue(String operation){
		int res = 0;
		switch (operation) {
		case "+":
			res = ADD;
			break;
		case "-":
			res = SUB;
			break;
		case "*":
			res = MUL;
			break;
		case "/":
			res = DIV;
			break;

		default:
			System.out.println("运算符出错");
			break;
		}
		return res;
	}
}
 