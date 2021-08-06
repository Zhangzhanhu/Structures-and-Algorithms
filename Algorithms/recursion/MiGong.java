package com.tunan.recursion;

public class MiGong {

	public static void main(String[] args) {
		//使用二维数组模拟一个地图
		int map[][] = new int[8][7];
		//使用1表示墙
		for(int i = 0; i < 7; i++){
			map[0][i] = 1;
			map[7][i] = 1;
		}
		for(int i = 0; i < 8; i++){
			map[i][0] = 1;
			map[i][6] = 1;
		}
		map[3][1] = 1;
		map[3][2] = 1;
		//输出地图
		System.out.println("地图的情况");
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 7; j++){
				System.out.print(map[i][j]+" ");	
			}
			System.out.println();
		}
		
		
		//使用递归方法找路
		setWay(map, 1, 1);
		System.out.println("找到的路");
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 7; j++){
				System.out.print(map[i][j]+" ");	
			}
			System.out.println();
		}
	}
	
	//使用递归回溯给小球找路
	/**
	 * @param map	表示传入的地图
	 * @param i		表示起始位置横坐标
	 * @param j		表示起始位置纵坐标
	 * @return		找到路true，否则false
	 * 【1，1】开始；【6，5】结束
	 * map[][]=0表示没走过、map[][]=1表示墙、map[][]=2表示能走、map[][]=3表示走过但是走不通
	 * 策略：下->右->上->左
	 */
	public static boolean setWay(int[][] map,int i, int j){
		if(map[6][5] == 2){//表示已经走通
			return true;
		} else{
			if(map[i][j] == 0){//该点没走过，按照策略进行
				map[i][j] = 2;//假设该点走得通
				if(setWay(map, i+1, j)){//向下走
					return true;
				}else if(setWay(map, i, j+1)){//向右走
					return true;
				}else if(setWay(map, i-1, j)){//向上走
					return true;
				}else if(setWay(map, i, j-1)){//向左走
					return true;
				}else{
					map[i][j] = 3;//思路一条
					return false;
				}
			}else{//map[]][] 可能是1、2、3。都不能再走
				return false;
			}
		}
	}

}
