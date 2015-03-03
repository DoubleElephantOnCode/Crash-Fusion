package com.mygdx.game;

public class Blue extends Colour{
	public Blue(int num){
		super(Type.blue ,num);
	}
	
	public void crash(Colour another){
		if(another.type == Type.blue){
			if(num/another.num >= 10){
				another.num = 0;
				return;
			}else if(another.num/num >= 10){
				num = 0;
				return;
			}
			if(num >= another.num){
				num -= another.num;
				another.num = 0;
			}else{
				another.num -= num;
				num = 0;
			}
		}else if(another.type == Type.red){
			if(num/another.num >= 5){
				another.num = 0;
				return;
			}else if(another.num/num >= 20){
				num = 0;
				return;
			}
			if(2*num >= another.num){
				num -= another.num/2;
				another.num = 0;
			}else{
				another.num -= 2*num;
				num = 0;
			}
		}else if(another.type == Type.green){
			if(num/another.num >= 20){
				another.num = 0;
				return;
			}else if(another.num/num >= 5){
				num = 0;
				return;
			}
			if(num >= 2*another.num){
				num -= 2*another.num;
				another.num = 0;
			}else{
				another.num -= num/2;
				num = 0;
			}
		}
		
	}
	
	public void crash2(Colour another){
		if(another.type == Type.blue){
			
			if(num >= another.num){
				num -= another.num;
				another.num = 0;
			}else{
				another.num -= num;
				num = 0;
			}
		}else if(another.type == Type.red){
			
			if(2*num >= another.num){
				num -= another.num/2;
				another.num = 0;
			}else{
				another.num -= 2*num;
				num = 0;
			}
		}else if(another.type == Type.green){
			
			if(num >= 2*another.num){
				num -= 2*another.num;
				another.num = 0;
			}else{
				another.num -= num/2;
				num = 0;
			}
		}
		
	}
}
