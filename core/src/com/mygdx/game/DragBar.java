package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DragBar extends Actor{
	TextureRegion buttonRegion;
	TextureRegion barRegion;
	Sprite spritebutton;
	Sprite spritebar;
	SpriteBatch batch;
	
	int width = 0, height = 0;
	float x = 0, y = 0;
	float bx = 0, by = 0;
	float value = 0;
	boolean isDraged = false;
	public DragBar(String color){
		Texture buttonImg = new Texture(Gdx.files.internal("dragBar/dragButton_"+color+".png"));
		Texture barImg = new Texture(Gdx.files.internal("dragBar/bar.png"));
		buttonRegion = new TextureRegion(buttonImg);
		barRegion = new TextureRegion(barImg);
		spritebutton = new Sprite(buttonImg);
		spritebar = new Sprite(barImg);
		batch = new SpriteBatch();
	}
	
	public void render(float x, float y, boolean useable){
		if(useable && (x - this.x) >= height / 2 && (x - this.x) <= width && (y - this.y) >= 0 && (y - this.y) <= height){
			bx = x - height / 2;
			if(bx < this.x) bx = this.x;
			value = (bx - this.x) / width;
			spritebutton.setPosition(bx, by);
			isDraged = true;
			Play.move(value);
		}
		batch.begin();
		spritebar.draw(batch);
		batch.end();
		batch.begin();
		spritebutton.draw(batch);
		batch.end();
	}
	
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
		spritebutton.setSize(height, height);
		spritebar.setSize(width - height / 2, height);
		setPosition(x, y);
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
		bx = x;
		by = y;
		spritebutton.setPosition(x, y);
		spritebar.setPosition(x + height / 2, y);
	}
	
	public void resetCondition(){
		isDraged = false;
	}
	
	public void reset(){
		bx = x;
		value = 0;
		isDraged = false;
		batch.begin();
		spritebar.draw(batch);
		batch.end();
		batch.begin();
		spritebutton.draw(batch);
		batch.end();
	}
	
}
