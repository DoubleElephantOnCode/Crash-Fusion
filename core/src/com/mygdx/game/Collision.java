package com.mygdx.game;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class Collision {
	TiledMap map;
	int row, column;
	int width, height;
	float cellW, cellH;
	String alwaysSeen_2 = "off";// off-*-*
	String split = "-";
	
	public Collision(TiledMap map, int row, int column,
			int width, int height){
		this.map = map;
		this.row = row;
		this.column = column;
		this.width = width;
		this.height = height;
		
		cellW = width / column;
		cellH = height / row;
	}
	
	public int[] getPool(int x, int y){
		int r = (int) (x / cellW);
		int c = (int) (y / cellH);
		for(MapLayer layer : map.getLayers()){
			if(layer instanceof TiledMapTileLayer){
				TiledMapTileLayer l = (TiledMapTileLayer) layer;
				Cell cell = l.getCell(r, c);
				if(cell != null){
					String[] name = l.getName().split(split);
					if(name[0].equals(alwaysSeen_2)){
						try{
							int[] p = new int[4];
							p[0] = Integer.parseInt(name[1]);
							p[1] = Integer.parseInt(name[2]);
							return p;
						}catch (Exception e){
							return null;
						}
					}
				}
			}
		}
		return null;
	}
}
