package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;


public class Play {
	static Map map;
	static Color red = new Color(0.95f, 0, 0, 1);
	static Color green = new Color(0, 1.0f, 0, 1);
	static Color blue = new Color(0, 0, 0.9f, 1);
	static Color yellow = new Color(1, 1, 0, 1);
	static Color white = new Color(1, 1, 1, 1);
	
	static Dye[][] dye;
	
	int maxRow, maxColumn;
	public Play(Map map){
		
		maxRow = map.numOfPoolRow;
		maxColumn = map.numOfPoolColumn;
		this.map = map;
		dye = map.dye;
		
		map.setColor(0, 0, red);
		map.setColor(0, 1, green);
		map.setColor(0, 2, blue);
		map.setColor(maxRow - 1, maxColumn - 1, red);
		map.setColor(maxRow - 1, maxColumn - 2, green);
		map.setColor(maxRow - 1, maxColumn - 3, blue);
		
		dye[0][0].red = 100;
		dye[0][1].green = 100;
		dye[0][2].blue = 100;
		dye[maxRow-1][maxColumn-1].red = 100;
		dye[maxRow-1][maxColumn-2].green = 100;
		dye[maxRow-1][maxColumn-3].blue = 100;
	}
	
	public static void move(float value){
		int red = (int) (dye[map.frontRow][map.frontColumn].red * value);
		int green = (int) (dye[map.frontRow][map.frontColumn].green * value);
		int blue = (int) (dye[map.frontRow][map.frontColumn].blue * value);
		
		dye[map.frontRow][map.frontColumn].red -= red;
		dye[map.frontRow][map.frontColumn].green -= green;
		dye[map.frontRow][map.frontColumn].blue -= blue;
		
		Dye temp = new Dye(red, green, blue);
		dye[map.realRow][map.realColumn] = DyeCrash.crash(temp, dye[map.realRow][map.realColumn]);
//		Color c = MixColor.mixRGB(dye[map.realRow][map.realColumn].red, dye[map.realRow][map.realColumn].green, dye[map.realRow][map.realColumn].blue);
//		map.setColor(map.realRow, map.realColumn, c);
//		Color d = MixColor.mixRGB(dye[map.frontRow][map.frontColumn].red, dye[map.frontRow][map.frontColumn].green, dye[map.frontRow][map.frontColumn].blue);
//		map.setColor(map.frontRow, map.frontColumn, d);
	}
}
