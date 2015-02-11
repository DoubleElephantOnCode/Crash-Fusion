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
	float max_value = 1;
	float min_value = 0;
	static boolean isDraged = false;
	public DragBar(String color){
		Texture buttonImg = new Texture(Gdx.files.internal("dragBar/dragButton_"+color+".png"));
		Texture barImg = new Texture(Gdx.files.internal("dragBar/bar.png"));
		buttonRegion = new TextureRegion(buttonImg);
		barRegion = new TextureRegion(barImg);
		spritebutton = new Sprite(buttonImg);
		spritebar = new Sprite(barImg);
		batch = new SpriteBatch();
	}
	
	public void render(float x, float y, boolean useable, boolean isDown){
		if(isDown && useable && (x - this.x) >= -height / 2 && (x - this.x) <= width + height / 2 && (y - this.y) >= -height / 2 && (y - this.y) <= height * 3 / 2){
			bx = x;
			if(bx < this.x) bx = this.x;
			if(bx > this.x + width) bx = this.x + width;
			value = (bx - this.x) / width;
			if(value > max_value){
				value = max_value;
				bx = this.x + value * width;
			}
			else if(value < min_value){
				value = min_value;
				bx = this.x + value * width;
			}
			spritebutton.setPosition(bx, by);
			isDraged = true;
			Condition.condition = 3;
		}
		batch.begin();
		spritebar.draw(batch);
		batch.end();
		batch.begin();
		spritebutton.draw(batch);
		batch.end();
	}
	
	public void setValue(float v){
		value = v;
		if(value > 1) value = 1;
		if(value < 0) value = 0;
		bx = x + width * v;
		spritebutton.setPosition(bx, by);
		batch.begin();
		spritebar.draw(batch);
		batch.end();
		batch.begin();
		spritebutton.draw(batch);
		batch.end();
	}
	
	public void setMaxValue(float max){
		if(max > 1) max = 1;
		if(max < 0) max = 0;
		max_value = max;
	}
	
	public void setMinValue(float min){
		if(min > 1) min = 1;
		if(min < 0) min = 0;
		min_value = min;
	}
	
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
		spritebutton.setSize(height, height);
		spritebar.setSize(width, height);
		setPosition(x, y);
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
		bx = x;
		by = y;
		spritebutton.setPosition(x, y);
		spritebar.setPosition(x, y);
	}
	
	public void resetCondition(){
		isDraged = false;
	}
	
	public void reset(){
		bx = x;
		value = 0;
		max_value = 1;
		min_value = 0;
		isDraged = false;
		spritebutton.setPosition(bx, by);
		batch.begin();
		spritebar.draw(batch);
		batch.end();
		batch.begin();
		spritebutton.draw(batch);
		batch.end();
	}
	
}
