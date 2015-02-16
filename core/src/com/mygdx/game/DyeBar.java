package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DyeBar {
	int x, y;
	int width, height;
	SpriteBatch batch;
	
	static final String path = "dyeBar/bar_";
	static final String last = ".png";
	Texture r, g, b;
	Animation red, green, blue;
	
	int rW, gW, bW;
	TextureRegion cR, cG, cB;
	
	static final int numOfPic = 10;
	static float stepTime = 0.03f;
	
	float stateTime = 0;
	
	
	public DyeBar(){
		
		TextureRegion[] red, green, blue;
		r = new Texture(Gdx.files.internal(path + "r" + last));
		g = new Texture(Gdx.files.internal(path + "g" + last));
		b = new Texture(Gdx.files.internal(path + "b" + last));
		
		red = new TextureRegion[numOfPic];
		green = new TextureRegion[numOfPic];
		blue = new TextureRegion[numOfPic];
		
		for(int i = 0; i < numOfPic; i++){
			red[i] = new TextureRegion(r, 0, 0, r.getWidth() * (i + 1) / numOfPic, r.getHeight());
			green[i] = new TextureRegion(g, 0, 0, r.getWidth() * (i + 1) / numOfPic, r.getHeight());
			blue[i] = new TextureRegion(b, 0, 0, r.getWidth() * (i + 1) / numOfPic, r.getHeight());
		}
		
		this.red = new Animation(stepTime, red);
		this.green = new Animation(stepTime, green);
		this.blue = new Animation(stepTime, blue);
		
		batch = new SpriteBatch();
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int width, int height){
		this.width = width;
		this.height = height / 3;
	}
	
	public void render(Dye dye, boolean isChange){
		if(dye.whose == 0)return;
		if(isChange){
			stateTime = 0;
			int total = dye.red + dye.green + dye.blue;
			if(total == 0) return;
			rW = dye.red * width / total;
			gW = dye.green * width / total;
			bW = dye.blue * width / total;
		}
		stateTime += Gdx.graphics.getDeltaTime();
		cR = red.getKeyFrame(stateTime, false);
		cG = green.getKeyFrame(stateTime, false);
		cB = blue.getKeyFrame(stateTime, false);
		batch.begin();
		batch.draw(cB, x, y, bW * cB.getRegionWidth() / b.getWidth(), height);
		batch.draw(cG, x, y+height, gW * cG.getRegionWidth() / g.getWidth(), height);
		batch.draw(cR, x, y+2*height, rW * cR.getRegionWidth() / r.getWidth(), height);
		batch.end();
	}
	
}
