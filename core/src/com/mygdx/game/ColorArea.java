package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * 
 * @author HalaWKS
 * 存储调好的颜色
 */
public class ColorArea {

	Texture texture;
	ColorForMix colorForMix;
	Sprite sprite;
	
	public ColorArea(Texture t) {
		this.texture = t;
		sprite = new Sprite(t);
	}

	public Texture getTexture() {
		return texture;
	}

	public void setT(Texture t) {
		this.texture = t;
	}

	public ColorForMix getColorForMix() {
		return colorForMix;
	}

	public void setColorForMix(ColorForMix colorForMix) {
		this.colorForMix = colorForMix;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setS(Sprite s) {
		this.sprite = s;
	}
	
	
	
}
