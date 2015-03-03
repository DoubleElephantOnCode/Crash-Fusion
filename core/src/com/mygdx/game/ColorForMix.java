package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

/**
 * 
 * @author HalaWKS
 *
 */
public class ColorForMix {

	Color color;
	
	float r;
	float g;
	float b;
	
	public ColorForMix(float red, float green, float blue) {
		int R = (int) (red * 20);
		int G = (int) (green * 20);
		int B = (int) (blue * 20);
		color = MixColor.mix(R, G, B);
		
		r = red;
		g = green;
		b = blue;
	}

	public Color getColor() {
		return color;
	}

	public float getR() {
		return r;
	}

	public float getG() {
		return g;
	}

	public float getB() {
		return b;
	}
	
	
}
