package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * 
 * @author HalaWKS
 * ��ɫ����
 */
public class ColorMixInterface {
	

	// ͼƬ��RGBֵ
	float r;
	float g;
	float b;

	// ���
	int PADDING = 20;
	int PADDING_Y = 85;

	//�ж�����˶�������ɫ
	int colorNum;
	
	Image redBucket;
	Image greenBucket;
	Image blueBucket;
	ImageButton confirmBtn;
	ImageButton deleteBtn;
//	ImageButton colorSquare[];
	
	static Texture texPic;
	static Texture colorArea[];
	
	static SpriteBatch batch;
	static Sprite sprite;
	static Sprite spriteForColorSqr[];
	
	
	static DragBar red;
	static DragBar green;
	static DragBar blue;

	public ColorMixInterface() {
		r = 1f;
		g = 1f;
		b = 1f;
		colorNum = 0;
		batch = new SpriteBatch();
		this.createBuckets();
		this.createPic();
		this.createButton();
		this.mixedColorArea();
		
		red = new DragBar("red");
		red.setPosition(100, 220);
		red.setSize(200, 20);
		
		green = new DragBar("green");
		green.setPosition(100, 140);
		green.setSize(200, 20);
		
		blue = new DragBar("blue");
		blue.setPosition(100, 60);
		blue.setSize(200, 20);
	}

	public void createPic() {
		texPic = new Texture(Gdx.files.internal("white.png"));
		sprite = new Sprite(texPic);
		sprite.setColor(1, 1, 1, 1);
		sprite.setPosition(200, 300);
	}

	public void createBuckets() {
		Texture tex = new Texture(Gdx.files.internal("buckets_s.png"));
		TextureRegion[][] texRegs = TextureRegion.split(tex, 60, 85);

		redBucket = new Image(texRegs[0][1]);
		greenBucket = new Image(texRegs[0][2]);
		blueBucket = new Image(texRegs[0][0]);

		blueBucket.setPosition(PADDING, PADDING);
		greenBucket.setPosition(blueBucket.getX(), blueBucket.getY() + PADDING_Y);
		redBucket.setPosition(greenBucket.getX(), greenBucket.getY() + PADDING_Y);
	}

	public void createButton(){
		TextureRegionDrawable btnConfirm = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("confirm_s.png"))));
		TextureRegionDrawable btnConfirmPush = new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("confirm_s_push.png"))));
		TextureRegionDrawable btnDelete = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("delete_s.jpg"))));
		TextureRegionDrawable btnDeletePush = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("delete_s_push.jpg"))));
		
		confirmBtn = new ImageButton(btnConfirm, btnConfirmPush);
		deleteBtn = new ImageButton(btnDelete, btnDeletePush);
		
		//�趨��ť����
		confirmBtn.setPosition(350, PADDING);
		deleteBtn.setPosition(confirmBtn.getX() + confirmBtn.getWidth() + PADDING, PADDING);
		
		confirmBtn.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//TODO ���һ����ɫ
				if(colorNum < colorArea.length){
					spriteForColorSqr[colorNum].setColor(sprite.getColor());
					colorNum++;
					System.out.println("add a color");
				}
				return true;
			}
		});
		
		deleteBtn.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//TODO ɾ��һ����ɫ
				if((colorNum <= colorArea.length) && (colorNum > 0)){
					spriteForColorSqr[colorNum - 1].setColor(new Color(1, 1, 1, 1));
					colorNum--;
					System.out.println("delete a color");
				}
				
				return true;
			}
		});
		
		
	}

	/**
	 * ��ӵ���ɫ��ʾ����
	 */
	public void mixedColorArea(){
		colorArea = new Texture[3];
		spriteForColorSqr = new Sprite[3];
		for (int i = 0; i < colorArea.length; i++) {
			colorArea[i] = new Texture(Gdx.files.internal("colorSquare.png"));
			spriteForColorSqr[i] = new Sprite(colorArea[i]);
			spriteForColorSqr[i].setColor(1, 1, 1, 1);
			spriteForColorSqr[i].setPosition(500, 300 - i * PADDING_Y);
		}
		
	}
	
	/**
	 * �����ɫ��ʾ������ʾ����rgb��
	 * @param x �����x����
	 * @param y �����y����
	 * @param isTouched �Ƿ���
	 */
	public static void rgbDisplay(float x, float y, boolean isTouched){
		if(isTouched){
			for (Sprite s : spriteForColorSqr) {
				if((x >= s.getX()) && (x <= s.getX() + s.getWidth()) &&
						(y >= s.getY()) && (y <= s.getY() + s.getHeight())){
					Color c = s.getColor();
					red.spritebutton.setPosition(c.r * 200 + red.getX(), red.getY());
					green.spritebutton.setPosition(c.g * 200 + green.getWidth(), green.getY());
					blue.spritebutton.setPosition(c.b * 200 + blue.getWidth(), blue.getY());
				}
			}
		}
	}
	
}
