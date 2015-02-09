package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DyeImg {
	int width, height;
	int x, y;
	Texture texture;
	TextureRegion region;
	
	public DyeImg(){
		texture = new Texture(Gdx.files.internal("map/dye.png"));
		region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
	}
	
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Batch batch){
		batch.draw(region, x, y, width, height);
	}
	
	public void draw(Batch batch, int x, int y){
		x -= (x % width);
		y -= (y % height);
		batch.draw(region, x, y, width, height);
	}
}
