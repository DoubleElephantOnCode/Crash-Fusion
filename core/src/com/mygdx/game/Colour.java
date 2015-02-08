package com.mygdx.game;

public class Colour {
	enum Type{
		red,green,blue
	}
	
	Type type;
	int num;
	
	public Colour(Type type,int num){
		this.type = type;
		this.num = num;
	}
	
	/**
	 * 两种颜色抵消的方法    红克绿  绿克蓝 蓝克红  暂定相克颜色抵消比例为1：2
	 * 加入秒杀机制  如果伤害比超过10 则造成秒杀，多的一方不会损失
	 * @param another
	 */
	public void crash(Colour another){
		
	}
}
