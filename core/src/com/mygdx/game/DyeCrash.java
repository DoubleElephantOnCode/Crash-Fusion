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
}
