package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

/**
 * 
 * @author WLJ
 *@version 0.1
 */
public class MyGdxGame extends ApplicationAdapter {
	InputMultiplexer multiplexer;
	TouchPointer pointer;
	Map map;
	DragBar bar;
	Play play;
	
//	int width = 800, height = 480;
	String mapPath = "map/map.tmx";
	int numOfPoolRow = 4, numOfPoolColumn = 5;
	int rowOfMap = 6, columnOfMap = 7;
	int widthOfMap = 1022, heightOfMap = 690;
	
	@Override
	public void create () {
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		map = new Map(width, height, mapPath, numOfPoolRow, numOfPoolColumn, rowOfMap, columnOfMap, widthOfMap, heightOfMap);
		
		multiplexer = new InputMultiplexer();
		pointer = new TouchPointer(map.getDefaultX(), map.getDefaultY());
		multiplexer.addProcessor(pointer);
		Gdx.input.setInputProcessor(multiplexer);
		
		bar = new DragBar("green");
		bar.setPosition(50, 50);
		bar.setSize(600, 50);
		
		play = new Play(map, bar);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		map.render(pointer.getDownX(), pointer.getDownY(), pointer.getNowX(), pointer.getNowY(), pointer.DOWN);
		
		bar.render(pointer.getNowX(), pointer.getNowY(), Condition.useBar, pointer.DOWN);
		
		play.move(bar.value, pointer.DOWN);
	}
}
