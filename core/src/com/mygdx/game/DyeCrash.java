package com.mygdx.game;

import java.util.ArrayList;

public class DyeCrash {
	
	public static Dye crash(Dye d1, Dye d2){
		d1.syncFromrToR();
		d2.syncFromrToR();
		return firstcrash(d1,d2);
	}
	
	/**
	 * 第一种抵消方法
	 * 每次取两个染料最多的颜色进行抵消 直到一方消失
	 * 存在的问题 ： 如果两种颜色同样最多 ， 会按rgb的顺序优先取，这样不太公平， 以后可以考虑相同时随机取
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Dye firstcrash(Dye d1, Dye d2){
		while(!(d1.isZero()||d2.isZero())){
			Colour c1 = d1.findMax();
			Colour c2 = d2.findMax();
			c1.crash(c2);
		}
		d1.sync();
		d2.sync();
		if(d1.isZero()){
			return new Dye(d2);
		}else{
			return new Dye(d1);
		}
	}
}
