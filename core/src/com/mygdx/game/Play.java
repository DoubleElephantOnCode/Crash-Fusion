package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;


public class Play {
	Map map;
	static Color red = new Color(0.95f, 0, 0, 1);
	static Color green = new Color(0, 1.0f, 0, 1);
	static Color blue = new Color(0, 0, 0.9f, 1);
	static Color yellow = new Color(1, 1, 0, 1);
	static Color white = new Color(1, 1, 1, 1);
	static Color none = new Color(0, 0, 0, 0);
	
	Dye[][] dye;
	
	DragBar bar;
	
	int maxRow, maxColumn;
	
	int tempRed, tempGreen, tempBlue;
	public Play(Map map, DragBar bar){
		
		maxRow = map.numOfPoolRow;
		maxColumn = map.numOfPoolColumn;
		this.map = map;
		this.bar = bar;
		dye = map.dye;
		
		map.setColor(0, 0, red);
		map.setColor(0, 1, green);
		map.setColor(0, 2, blue);
		map.setColor(maxRow - 1, maxColumn - 1, red);
		map.setColor(maxRow - 1, maxColumn - 2, green);
		map.setColor(maxRow - 1, maxColumn - 3, blue);
		
		dye[0][0].red = 100; dye[0][0].whose = 1;
		dye[0][1].green = 100; dye[0][1].whose = 1;
		dye[0][2].blue = 100; dye[0][2].whose = 1;
		dye[maxRow-1][maxColumn-1].red = 100; dye[maxRow-1][maxColumn-1].whose = 2;
		dye[maxRow-1][maxColumn-2].green = 100; dye[maxRow-1][maxColumn-2].whose = 2;
		dye[maxRow-1][maxColumn-3].blue = 100; dye[maxRow-1][maxColumn-3].whose = 2;
	}
	
	public void move(float value, boolean isDown){
		if(value < 0.05) value = 0;
		if(value > 0.95) value = 1;
		if(map.frontColumn < 0 || map.frontRow < 0 || map.realColumn < 0 || map.realRow < 0) return;
		if(isDown){
			
			tempRed = (int) (dye[map.frontRow][map.frontColumn].red * value);
			tempGreen = (int) (dye[map.frontRow][map.frontColumn].green * value);
			tempBlue = (int) (dye[map.frontRow][map.frontColumn].blue * value);
			
			Dye temp = new Dye(tempRed, tempGreen, tempBlue);
			Dye realDye = DyeCrash.crash(temp, dye[map.realRow][map.realColumn]);
			Color c = MixColor.mixRGB(realDye.red, realDye.green, realDye.blue);
			map.setColor(map.realRow, map.realColumn, c);
			Color d = MixColor.mixRGB(dye[map.frontRow][map.frontColumn].red - tempRed, dye[map.frontRow][map.frontColumn].green - tempGreen, dye[map.frontRow][map.frontColumn].blue - tempBlue);
			map.setColor(map.frontRow, map.frontColumn, d);
		}
		else{
			dye[map.frontRow][map.frontColumn].red -= tempRed;
			dye[map.frontRow][map.frontColumn].green -= tempGreen;
			dye[map.frontRow][map.frontColumn].blue -= tempBlue;
			
			Dye temp = new Dye(tempRed, tempGreen, tempBlue);
			dye[map.realRow][map.realColumn] = DyeCrash.crash(temp, dye[map.realRow][map.realColumn]);
			bar.reset();
		}
	}
	
	public void mixFriend(float value, boolean isDown){
		if(value < 0.05) value = 0;
		if(value > 0.95) value = 1;
		if(map.frontColumn < 0 || map.frontRow < 0 || map.realColumn < 0 || map.realRow < 0) return;
		if(isDown){
			
			tempRed = (int) (dye[map.frontRow][map.frontColumn].red * value);
			tempGreen = (int) (dye[map.frontRow][map.frontColumn].green * value);
			tempBlue = (int) (dye[map.frontRow][map.frontColumn].blue * value);
			
			Color c = MixColor.mixRGB(tempRed + dye[map.realRow][map.realColumn].red, tempGreen + dye[map.realRow][map.realColumn].green, tempBlue + dye[map.realRow][map.realColumn].blue);
			map.setColor(map.realRow, map.realColumn, c);
			Color d = MixColor.mixRGB(dye[map.frontRow][map.frontColumn].red - tempRed, dye[map.frontRow][map.frontColumn].green - tempGreen, dye[map.frontRow][map.frontColumn].blue - tempBlue);
			map.setColor(map.frontRow, map.frontColumn, d);
		}
		else{
			tempRed = (int) (dye[map.frontRow][map.frontColumn].red * value);
			tempGreen = (int) (dye[map.frontRow][map.frontColumn].green * value);
			tempBlue = (int) (dye[map.frontRow][map.frontColumn].blue * value);
			
			dye[map.frontRow][map.frontColumn].red -= tempRed;
			dye[map.frontRow][map.frontColumn].green -= tempGreen;
			dye[map.frontRow][map.frontColumn].blue -= tempBlue;
			
			dye[map.realRow][map.realColumn].red += tempRed;
			dye[map.realRow][map.realColumn].green += tempGreen;
			dye[map.realRow][map.realColumn].blue += tempBlue;
			bar.reset();
		}
	}
}
