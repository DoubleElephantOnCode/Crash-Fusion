package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

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
	DyeBar dyebar;
	DyeBar dyebar_real;
	
	int formerSR = -1, formerSC = -1;
	int formerRR = -1, formerRC = -1;
	
//	int width = 800, height = 480;
	String mapPath = "map/map.tmx";
	int numOfPoolRow = 4, numOfPoolColumn = 5;
	int rowOfMap = 6, columnOfMap = 7;
	int widthOfMap = 1022, heightOfMap = 690;
	
	//TODO WKS
	Stage stage;
	ColorMixInterface colorMix;
	Color red = new Color(1, 0, 0, 1);
	Color green = new Color(0, 1, 0, 1);
	Color blue = new Color(0, 0, 1, 1);
	
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
		
		dyebar = new DyeBar();
		dyebar.setPosition(0, 0);
		dyebar.setSize(500, 45);
		
		dyebar_real = new DyeBar();
		dyebar_real.setPosition(510, 0);
		dyebar_real.setSize(500, 45);
		
		play = new Play(map, bar);
		
		//TODO WKS
		stage = new Stage();
		colorMix = new ColorMixInterface();

		stage.addActor(colorMix.redBucket);
		stage.addActor(colorMix.greenBucket);
		stage.addActor(colorMix.blueBucket);
		stage.addActor(colorMix.confirmBtn);
		stage.addActor(colorMix.deleteBtn);
		stage.addActor(colorMix.nextBtn);

		multiplexer = new InputMultiplexer();
		pointer = new TouchPointer(1, 1);
		multiplexer.addProcessor(pointer);
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void render () {
		
		colorMixStage();
		if(colorMix.isFinished){
			gameStage();
		}
		
	}
	
	public void colorMixStage(){
		//TODO WKS
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ColorMixInterface.red.render(pointer.getNowX(), pointer.getNowY(), pointer.DOWN);
		ColorMixInterface.green.render(pointer.getNowX(), pointer.getNowY(), pointer.DOWN);
		ColorMixInterface.blue.render(pointer.getNowX(), pointer.getNowY(), pointer.DOWN);
		ColorMixInterface.rgbDisplay(pointer.getNowX(), pointer.getNowY(), pointer.DOWN);
		
		stage.act();
		stage.draw();
		
		if (ColorMixInterface.red.isDraged || ColorMixInterface.green.isDraged
				|| ColorMixInterface.blue.isDraged) {
			float r = ColorMixInterface.red.value;
			float g = ColorMixInterface.green.value;
			float b = ColorMixInterface.blue.value;

			int R = (int) (r * 20);
			int G = (int) (g * 20);
			int B = (int) (b * 20);
			Color temp = MixColor.mix(R, G, B);
			ColorMixInterface.sprite.setColor(temp);
		}

		ColorMixInterface.batch.begin();
		ColorMixInterface.sprite.draw(ColorMixInterface.batch);
		for (int i = 0; i < ColorMixInterface.colorArea.length; i++) {
			ColorMixInterface.colorArea[i].sprite.draw(ColorMixInterface.batch);
		}
		ColorMixInterface.batch.end();
	}
	
	public void gameStage(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		map.render(pointer.getDownX(), pointer.getDownY(), pointer.getNowX(), pointer.getNowY(), pointer.DOWN);
		
		bar.render(pointer.getNowX(), pointer.getNowY(), Condition.useBar, pointer.DOWN);
		
		play.move(bar.value, pointer.DOWN);
		
		if(map.selectedRow != -1 && map.selectedColumn != -1){
			if(formerSR != map.selectedRow || formerSC != map.selectedColumn){
				formerSR = map.selectedRow;
				formerSC = map.selectedColumn;
				dyebar.render(map.dye[map.selectedRow][map.selectedColumn], true);
			}
			else{
				dyebar.render(map.dye[map.selectedRow][map.selectedColumn], false);
			}
		}
		else{
//			formerSR = -1;
//			formerSC = -1;
			
			if(map.frontRow != -1 && map.frontColumn != -1){
				if(formerSR != map.frontRow || formerSC != map.frontColumn){
					formerSR = map.frontRow;
					formerSC = map.frontColumn;
					dyebar.render(map.dye[map.frontRow][map.frontColumn], true);
				}
				else{
					dyebar.render(map.dye[map.frontRow][map.frontColumn], false);
				}
			}
			else{
				formerSR = -1;
				formerSC = -1;
			}
			
		}
		
		
		
		if(map.realRow != -1 && map.realColumn != -1){
			if(formerRR != map.realRow || formerRC != map.realColumn){
				formerRR = map.realRow;
				formerRC = map.realColumn;
				dyebar_real.render(map.dye[map.realRow][map.realColumn], true);
			}
			else{
				dyebar_real.render(map.dye[map.realRow][map.realColumn], false);
			}
		}
		else{
			formerRR = -1;
			formerRC = -1;
		}
	}

}
