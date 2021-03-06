package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map{
	
	static boolean drawLine = false;
	int frontRow = -1, frontColumn = -1;
	int realRow = -1, realColumn = -1;
	int selectedRow = -1, selectedColumn = -1;
	
	int width, height;
	String path;
	int numOfPoolRow, numOfPoolColumn;
	int[] always;
	int rowOfMap, columnOfMap;
	int widthOfMap, heightOfMap;
	
	int max = 100;
	
	Color white = new Color(1, 1, 1, 1);
	Color none = new Color(0, 0, 0, 0);
	
	TmxMapLoader loader;
	TiledMap map;
	OrthogonalTiledMapRenderer render;
	OrthographicCamera camera;
	Collision collision;
	DyeImg[][] dyeImg;
	Dye[][] dye;
	SpriteBatch batch;
	VirtualLine line;
	
	String alwaysSeen_1 = "background";
	String alwaysSeen_2 = "off";// off-*-*
	String split = "-";
	String choiceSeen = "on";
	
	public Map(int width, int height, String mapPath, 
			int numOfPoolRow, int numOfPoolColumn, 
			int rowOfMap, int columnOfMap, int widthOfMap, int heightOfMap){
		this.width = width;
		this.height = height;
		this.path = mapPath;
		this.numOfPoolRow = numOfPoolRow;
		this.numOfPoolColumn = numOfPoolColumn;
		this.rowOfMap = rowOfMap;
		this.columnOfMap = columnOfMap;
		this.widthOfMap = widthOfMap;
		this.heightOfMap = heightOfMap;
		
		this.initial();
		this.initialDye();
		
		line = new VirtualLine();
	}
	
	public void render(int downX, int downY, int nowX, int nowY, boolean isDown){
		render.render(always);
		renderDye();
		
		int[] p = collision.getPool(downX, downY);
		int[] q = collision.getPool(nowX, nowY);
		
		if((p == null || q == null) && isDown) return;//相关操作太多，1.在拖拽bar，2.要点击空白消除选择
		if(p == null || q == null) return;
		if(p[0] == q[0] && p[1] == q[1]){
			if(isDown){//如果为按住状态，标明可以移动的位置
				selectedRow = p[0];
				selectedColumn = p[1];
				frontRow = -1;
				frontColumn = -1;
				realRow = -1;
				realColumn = -1;
				for(int i = 0; i < numOfPoolRow; i++){
					for(int j = 0; j < numOfPoolColumn; j++){
						if(Math.abs(p[0] - i) <= 1 && Math.abs(p[1] - j) <= 1){
							int[] index = new int[1];
							index[0] = this.findLayerIndex(choiceSeen + split + i + split + j);
							render.render(index);
						}
					}
				}
				Condition.useBar = false;
			}
			else{//否则只是选中
				int[] index = new int[1];
				index[0] = this.findLayerIndex(choiceSeen + split + selectedRow + split + selectedColumn);
				if(index[0] >= 0){
					render.render(index);
					Condition.condition = 1;
					Condition.useBar = false;
				}
			}
		}
		else{
			if(isDown){//如果为按住状态，则正在选择转移的位置，引导线要出现
				if(Math.abs(p[0] - q[0]) <= 1 && Math.abs(p[1] - q[1]) <= 1){//有效移动
					frontRow = p[0];
					frontColumn = p[1];
					realRow = q[0];
					realColumn = q[1];
					selectedRow = -1;
					selectedColumn = -1;
					for(int i = 0; i < numOfPoolRow; i++){//先显示所有可以移动到的位置
						for(int j = 0; j < numOfPoolColumn; j++){
							if(Math.abs(p[0] - i) <= 1 && Math.abs(p[1] - j) <= 1){
								int[] index = new int[1];
								index[0] = this.findLayerIndex(choiceSeen + split + i + split + j);
								render.render(index);
							}
						}
					}
					//再着重被选中移动到的位置
					int[] index = new int[1];
					index[0] = this.findLayerIndex(choiceSeen + split + realRow + split + realColumn);
					if(index[0] >= 0){
						render.render(index);
						render.render(index);
						line.render(downX, downY, nowX, nowY, true);
						Condition.condition = 2;
						Condition.useBar = true;
					}
				}
				else{//无效移动
					this.initialSelected();
					for(int i = 0; i < numOfPoolRow; i++){//先显示所有可以移动到的位置
						for(int j = 0; j < numOfPoolColumn; j++){
							if(Math.abs(p[0] - i) <= 1 && Math.abs(p[1] - j) <= 1){
								int[] index = new int[1];
								index[0] = this.findLayerIndex(choiceSeen + split + i + split + j);
								render.render(index);
							}
						}
					}
					line.render(downX, downY, nowX, nowY, true);
					Condition.useBar = false;
				}
			}
			else{//否则为等待下一步操作状态，显示起始和终点，引导线要出现,保持之前选定的引导状态
				int[] index = new int[2];
				index[0] = this.findLayerIndex(choiceSeen + split + realRow + split + realColumn);
				index[1] = this.findLayerIndex(choiceSeen + split + frontRow + split + frontColumn);
				if(index[0] >= 0 && index[1] >= 0){
					render.render(index);
					line.render();
					Condition.condition = 2;
					Condition.useBar = true;
				}
			}
		}
		//下面注释为原有代码，以上为更新后
	}
	
	public void renderDye(){
		for(int i = 0; i < numOfPoolRow; i++){
			for(int j = 0; j < numOfPoolColumn; j++){
				if(Condition.condition == 3){
					if(DragBar.isDraged && ((i == frontRow && j == frontColumn) || (i == realRow && j == realColumn))) continue;
				}
				Color color = MixColor.mixRGB(dye[i][j].red, dye[i][j].green, dye[i][j].blue);
				setColor(i, j, color);
			}
		}
	}
	
	public void setColor(int rowOfPool, int columnOfPool, Color color){
		if(rowOfPool < 0 || rowOfPool >= numOfPoolRow 
				|| columnOfPool < 0 || columnOfPool >= numOfPoolColumn)
			return;
		batch.setColor(color);
		batch.begin();
		dyeImg[rowOfPool][columnOfPool].draw(batch);
		batch.end();
		batch.setColor(none);
	}
	
	public int getDefaultX(){
		if(map.getLayers().get("pointer") != null){
			MapObject object = map.getLayers().get("pointer").getObjects().get("pointer");
			if(object instanceof CircleMapObject){
				return (int) ((CircleMapObject) object).getCircle().x;
			}
			else if(object instanceof EllipseMapObject){
				return (int) ((EllipseMapObject) object).getEllipse().x;
			}
		}
		return 1;
	}
	
	public int getDefaultY(){
		if(map.getLayers().get("pointer") != null){
			MapObject object = map.getLayers().get("pointer").getObjects().get("pointer");
			if(object instanceof CircleMapObject){
				return (int) ((CircleMapObject) object).getCircle().y;
			}
			else if(object instanceof EllipseMapObject){
				return (int) ((EllipseMapObject) object).getEllipse().y;
			}
		}
		return 1;
	}
	
	private void initial(){
		loader = new TmxMapLoader();
		map = loader.load(path);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, widthOfMap, heightOfMap);
		render = new OrthogonalTiledMapRenderer(map);
		render.setView(camera);
		
		
		collision = new Collision(map, rowOfMap, columnOfMap, width, height);
		
		int[] temp = new int[map.getLayers().getCount()];
		int index = 0;
		for(int i = 0; i < temp.length; i++){
			String name = map.getLayers().get(i).getName();
			if(name.equals(alwaysSeen_1)){
				temp[index] = i;
				index++;
			}
			else{
				String[] off = name.split(split);
				if(off[0].equals(alwaysSeen_2)){
					temp[index] = i;
					index++;
				}
			}
		}
		always = new int[index + 1];
		for(int i = 0; i <= index; i++){
			always[i] = temp[i];
		}
	}
	
	private void initialDye(){
		dyeImg = new DyeImg[numOfPoolRow][numOfPoolColumn];
		for(int i = 0; i < numOfPoolRow; i++){
			for(int j = 0; j < numOfPoolColumn; j++){
				dyeImg[i][j] = new DyeImg();
				dyeImg[i][j].setSize(width / columnOfMap, height / rowOfMap);
				dyeImg[i][j].setPosition(width / columnOfMap * (j + 1), height / rowOfMap * (numOfPoolRow - i));
			}
		}
		batch = new SpriteBatch();
		
		dye = new Dye[numOfPoolRow][numOfPoolColumn];
		for(int i = 0; i < numOfPoolRow; i++){
			for(int j = 0; j < numOfPoolColumn; j++){
				dye[i][j] = new Dye(0, 0, 0);
				dye[i][j].whose = 0;
				dye[i][j].maxDye = 100;
			}
		}
	}
	
	private int findLayerIndex(String layerName){
		int index = 0;
		for(MapLayer layer : map.getLayers()){
			if(!layer.getName().equals(layerName)){
				index++;
			}
			else{
				return index;
			}
		}
		return -1;
	}
	
	public void initialSelected(){
		frontRow = -1;
		frontColumn = -1;
		realRow = -1;
		realColumn = -1;
		selectedRow = -1;
		selectedColumn = -1;
	}

}


//if(!isDown){
//if(downX == nowX && downY == nowY) return;
//int[] p = collision.getPool(downX, downY);
//int[] q = collision.getPool(nowX, nowY);
//if(p == null || q == null){
//	drawLine = false;
//	return;
//}
//if(p[0] == q[0] && p[1] == q[1]){
//	int[] index = new int[1];
//	index[0] = this.findLayerIndex(choiceSeen + split + p[0] + split + p[1]);
//	render.render(index);
//	drawLine = false;
//}
//else if(Math.abs(p[0] - q[0]) <= 1 && Math.abs(p[1] - q[1]) <= 1){
//	int[] index = new int[1];
//	index[0] = this.findLayerIndex(choiceSeen + split + p[0] + split + p[1]);
//	render.render(index);
//	index[0] = this.findLayerIndex(choiceSeen + split + q[0] + split + q[1]);
//	render.render(index);
//	drawLine = true;
//}
//else{
//	int[] index = new int[1];
//	index[0] = this.findLayerIndex(choiceSeen + split + p[0] + split + p[1]);
//	render.render(index);
//	drawLine = false;
//}
//}
//else{
//int[] p = collision.getPool(downX, downY);
//int[] q = collision.getPool(nowX, nowY);
//if(p == null || q == null){
//	drawLine = false;
//	return;
//}
//frontRow = p[0];
//frontColumn = p[1];
//realRow = q[0];
//realColumn = q[1];
//for(int i = 0; i < numOfPoolRow; i++){
//	for(int j = 0; j < numOfPoolColumn; j++){
//		if(Math.abs(p[0] - i) <= 1 && Math.abs(p[1] - j) <= 1){
//			int[] index = new int[1];
//			index[0] = this.findLayerIndex(choiceSeen + split + i + split + j);
//			render.render(index);
//		}
//	}
//}
//if(Math.abs(p[0] - q[0]) <= 1 && Math.abs(p[1] - q[1]) <= 1){
//	int[] index = new int[1];
//	index[0] = this.findLayerIndex(choiceSeen + split + q[0] + split + q[1]);
//	render.render(index);
//	render.render(index);
//}
//drawLine = true;
//}
