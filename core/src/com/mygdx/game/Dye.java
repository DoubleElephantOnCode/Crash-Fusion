package com.mygdx.game;

public class Dye {
	int red;
	int green;
	int blue;
	public int maxDye;//可以容下的最大  red +　green + blue 的量
	
	public int whose = 0;//0无归属，1，2分别为双方归属
	
	Red redcolor;
	Green greencolor;
	Blue bluecolor;
	public Dye(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
		redcolor = new Red(red);
		greencolor = new Green(green);
		bluecolor = new Blue(blue);
	}
	
	public Dye(Dye d){
		this.red =d.red;
		this.green = d.green;
		this.blue = d.blue;
		redcolor = new Red(red);
		greencolor = new Green(green);
		bluecolor = new Blue(blue);
		this.maxDye = d.maxDye;
		this.whose = d.whose;
	}
	
	
	
	public boolean isZero(){
		return (redcolor.num == 0)&&(greencolor.num == 0)&&(bluecolor.num == 0);
	}
	public Colour findMax(){
		if(redcolor.num >= greencolor.num &&redcolor.num >= bluecolor.num){
			return redcolor;
		}else if(greencolor.num >= redcolor.num && greencolor.num >= bluecolor.num){
			return greencolor;
		}else{
			return bluecolor;
		}
	}
	
	/*
	 * 将redcolor greencolr bluecolor 里的数据和red green blue等 同步
	 */
	public void sync(){
		red = redcolor.num;
		green = greencolor.num;
		blue = bluecolor.num;
	}
	
	/*
	 * 将red green blue 和 redcolor greencolr bluecolor 里的数据e等 同步
	 */
	public void syncFromrToR(){
		redcolor.num = red;
		greencolor.num = green;
		bluecolor.num = blue;
	}
}
