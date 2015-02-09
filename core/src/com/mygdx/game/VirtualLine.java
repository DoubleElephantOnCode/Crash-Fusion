package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class VirtualLine {
	private static final String imgPath = "line/line";
	private static final String last = ".png";
	private static final String straightImgPath = "line/straight";
	private static final int g = 7;
	private static final int s = 3;
	
	private TextureRegion[] regionA;
	private TextureRegion[] regionB;
	private TextureRegion[] regionS;
	private TextureRegion currentFrame;
	private Animation amtA;
	private Animation amtB;
	private Animation amtS;
	private Sprite sprite;
	private SpriteBatch batch;
	
	int fX = 0, fY = 0, rX = 0, rY = 0;
	
	float width = 0, height = 0;
	
	float degree = 0;
	
	private static float singleStempTime = 0.04f;
	private static float stateTime = 0;
	
	public VirtualLine(){
		
		batch = new SpriteBatch();
		
		Texture[] textures = new Texture[g];
		regionA = new TextureRegion[g];
		regionB = new TextureRegion[g];
		for(int i = 0; i < g;){
			textures[i] = new Texture(Gdx.files.internal(imgPath + (++i) + last));
		}
		int imgW = textures[0].getWidth();
		int imgH = textures[0].getHeight();
		for(int i = 0; i < g; i++){
			regionA[i] = new TextureRegion(textures[i], 0, 0, imgW, imgH);
			regionB[i] = new TextureRegion(textures[g-i-1], 0, 0, imgW, imgH);
		}
		amtA = new Animation(singleStempTime, regionA);
		amtB = new Animation(singleStempTime, regionB);
		
		Texture[] texturesS = new Texture[s];
		regionS = new TextureRegion[s];
		for(int i = 0; i < s; i++){
			texturesS[i] = new Texture(Gdx.files.internal(straightImgPath + (i + 1) + last));
			regionS[i] = new TextureRegion(texturesS[i], 0, 0, imgW, imgH);
		}
		amtS = new Animation(singleStempTime * 2, regionS);
		
		sprite = new Sprite();
	}
	
	public void render(int frontX, int frontY, int realX, int realY, boolean drawable){
		stateTime += Gdx.graphics.getDeltaTime();
		if(!drawable) return;
		fX = frontX;
		fY = frontY;
		rX = realX;
		rY = realY;
		if(fX == rX && fY == rY) return;
		this.setImgBounds();
		sprite.setOrigin(0, 0);
		sprite.setColor(1, 1, 1, 1);
		degree = (float) Math.abs((Math.asin((rY - fY) / width) / Math.PI * 180));
		if(rY > fY && rX > fX){//第一象限
			currentFrame = amtA.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		else if(rY > fY && rX < fX){//第二象限
			currentFrame = amtB.getKeyFrame(stateTime, true);
			sprite.setPosition(rX, rY);
			degree = -degree;
		}
		else if(rY < fY && rX < fX){//第三象限
			currentFrame = amtB.getKeyFrame(stateTime, true);
			sprite.setPosition(rX, rY);
		}
		else if(rY < fY && rX > fX){//第四象限
			degree = -degree;
			currentFrame = amtA.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		else if(rY < fY){//270
			degree = -degree;
			currentFrame = amtS.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		else if(rY > fY){//90
			currentFrame = amtS.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		else if(rX < fX){//180
			currentFrame = amtB.getKeyFrame(stateTime, true);
			sprite.setPosition(rX, rY);
		}
		else{//0
			currentFrame = amtA.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		
		sprite.setRegion(currentFrame);
		sprite.setSize(width, height);
		sprite.rotate(degree);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		sprite.rotate(-degree);
	}
	
	public void render(){
		stateTime += Gdx.graphics.getDeltaTime();
		if(fX == rX && fY == rY) return;
		this.setImgBounds();
		sprite.setOrigin(0, 0);
		sprite.setColor(1, 1, 1, 1);
		degree = (float) Math.abs((Math.asin((rY - fY) / width) / Math.PI * 180));
		if(rY > fY && rX > fX){//第一象限
			currentFrame = amtA.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		else if(rY > fY && rX < fX){//第二象限
			currentFrame = amtB.getKeyFrame(stateTime, true);
			sprite.setPosition(rX, rY);
			degree = -degree;
		}
		else if(rY < fY && rX < fX){//第三象限
			currentFrame = amtB.getKeyFrame(stateTime, true);
			sprite.setPosition(rX, rY);
		}
		else if(rY < fY && rX > fX){//第四象限
			degree = -degree;
			currentFrame = amtA.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		else if(rY < fY){//270
			degree = -degree;
			currentFrame = amtS.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		else if(rY > fY){//90
			currentFrame = amtS.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		else if(rX < fX){//180
			currentFrame = amtB.getKeyFrame(stateTime, true);
			sprite.setPosition(rX, rY);
		}
		else{//0
			currentFrame = amtA.getKeyFrame(stateTime, true);
			sprite.setPosition(fX, fY);
		}
		
		sprite.setRegion(currentFrame);
		sprite.setSize(width, height);
		sprite.rotate(degree);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		sprite.rotate(-degree);
	}
	
	private void setImgBounds(){
		width = (float) Math.sqrt((rX - fX) * (rX - fX) + (rY - fY) * (rY - fY));
		height = (Math.abs(rX - fX)) / 5 + 15;
		if(height > 50) height = 50;
	}
}
