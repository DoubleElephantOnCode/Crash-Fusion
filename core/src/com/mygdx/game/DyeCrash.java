package com.mygdx.game;

import java.util.ArrayList;

public class DyeCrash {
	
	public static Dye crash(Dye d1, Dye d2){
		d1.syncFromrToR();
		d2.syncFromrToR();
		return firstcrash(d1,d2);
	}
	
	/**
	 * ��һ�ֵ�������
	 * ÿ��ȡ����Ⱦ��������ɫ���е��� ֱ��һ����ʧ
	 * ���ڵ����� �� ���������ɫͬ����� �� �ᰴrgb��˳������ȡ��������̫��ƽ�� �Ժ���Կ�����ͬʱ���ȡ
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
	
	/**
	 * �ڶ��ֵ�������
	 * �Ȱ���ͬ���ֵ��� Ȼ��ÿ��ȡ����Ⱦ��������ɫ���е��� ֱ��һ����ʧ
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Dye secondcrash(Dye d1, Dye d2){
		if(d1.bluecolor.num > d2.bluecolor.num){
			d1.bluecolor.num -= d2.bluecolor.num;
			d2.bluecolor.num = 0;
		}else{
			d2.bluecolor.num -= d1.bluecolor.num;
			d1.bluecolor.num = 0;
		}
		if(d1.redcolor.num > d2.redcolor.num){
			d1.redcolor.num -= d2.redcolor.num;
			d2.redcolor.num = 0;
		}else{
			d2.redcolor.num -= d1.redcolor.num;
			d1.redcolor.num = 0;
		}
		
		if(d1.greencolor.num > d2.greencolor.num){
			d1.greencolor.num -= d2.greencolor.num;
			d2.greencolor.num = 0;
		}else{
			d2.greencolor.num -= d1.greencolor.num;
			d1.greencolor.num = 0;
		}
		
		while(!(d1.isZero()||d2.isZero())){
			Colour c1 = d1.findMax();
			Colour c2 = d2.findMax();
			c1.crash2(c2);
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
