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
	 * ������ɫ�����ķ���    �����  �̿��� ���˺�  �ݶ������ɫ��������Ϊ1��2
	 * ������ɱ����  ����˺��ȳ���10 �������ɱ�����һ��������ʧ
	 * @param another
	 */
	public void crash(Colour another){
		
	}
	
	/**
	 *  ������ɫ�����ķ���    �����  �̿��� ���˺�  �ݶ������ɫ��������Ϊ1��2
	 * @param another
	 */
	public void crash2(Colour another){
		
	}
}
