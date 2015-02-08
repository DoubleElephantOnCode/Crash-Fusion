package colorMix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 
 * @author HalaWKS
 * 颜料图片
 * 
 */
public class DyePic extends Actor{

	/**
	 * 红色数值
	 */
	private float red;
	
	/**
	 * 绿色数值
	 */
	private float green;
	
	/**
	 * 蓝色数值
	 */
	private float blue;
	
	private Sprite sprite;
	
	
	public DyePic(Color c, String picPath, float width, float height, float x, float y) {
		this.red = c.getRed();
		this.green = c.getGreen();
		this.blue = c.getBlue();
		
		Texture pic = new Texture(Gdx.files.internal(picPath));
		sprite = new Sprite(pic);
		this.setSize(width, height);
		this.setPosition(x, y);
	}

	/**
	 * 设置图片大小
	 */
	private void setPicSize(float w, float h){
		sprite.setSize(w, h);
	}
	
	/**
	 * 设置图片位置
	 */
	private void setPicPosition(float x, float y){
		sprite.setPosition(x, y);
	}
	
	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}
	

	public float getH() {
		return sprite.getHeight();
	}

	public void setH(float h) {
		sprite.setSize(sprite.getWidth(), h);
	}

	public float getW() {
		return sprite.getWidth();
	}

	public void setW(float w) {
		sprite.setSize(w, sprite.getHeight());
	}

	public float getX() {
		return sprite.getX();
	}

	public void setX(float x) {
		sprite.setX(x);
	}

	public float getY() {
		return sprite.getY();
	}

	public void setY(float y) {
		sprite.setY(y);
	}
	
	
	
}
