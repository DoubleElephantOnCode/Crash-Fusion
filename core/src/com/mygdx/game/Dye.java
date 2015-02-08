package com.mygdx.game;

public class Dye {
	int red;
	int green;
	int blue;
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
}
