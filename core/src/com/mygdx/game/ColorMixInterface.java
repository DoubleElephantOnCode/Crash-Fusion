package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * 
 * @author HalaWKS
 * 混色界面
 */
public class ColorMixInterface extends Stage {

	// 图片的RGB值
	float r;
	float g;
	float b;

	// 间距
	int PADDING = 200;
	int PADDING_Y = 100;

	//判断添加了多少种颜色
	int colorNum;
	//判断是否完成颜色配置
	boolean isFinished = false;
	
	Image redBucket;
	Image greenBucket;
	Image blueBucket;
	ImageButton confirmBtn;
	ImageButton deleteBtn;
	ImageButton nextBtn;
//	ImageButton colorSquare[];
	
	/**
	 * 颜色们
	 */
	static Color colors[];
	
	static Texture texPic;
	static Texture colorArea[];
	
	static SpriteBatch batch;
	static Sprite sprite;
	static Sprite spriteForColorSqr[];
	
	
	static DragBar red;
	static DragBar green;
	static DragBar blue;

	public ColorMixInterface() {
		super(new StretchViewport(1920, 1080));
		
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
		red.setPosition(300, 400);
		red.setSize(1000, 100);
		
		green = new DragBar("green");
		green.setPosition(300, 300);
		green.setSize(1000, 100);
		
		blue = new DragBar("blue");
		blue.setPosition(300, 200);
		blue.setSize(1000, 100);
	}

	public void createPic() {
		texPic = new Texture(Gdx.files.internal("colorMix/white.png"));
		sprite = new Sprite(texPic);
		sprite.setColor(1, 1, 1, 1);
		sprite.setPosition(600, 600);
	}

	public void createBuckets() {
		Texture tex = new Texture(Gdx.files.internal("colorMix/buckets_s.png"));
		TextureRegion[][] texRegs = TextureRegion.split(tex, 60, 85);

		redBucket = new Image(texRegs[0][1]);
		greenBucket = new Image(texRegs[0][2]);
		blueBucket = new Image(texRegs[0][0]);

		blueBucket.setPosition(PADDING, PADDING);
		greenBucket.setPosition(blueBucket.getX(), blueBucket.getY() + PADDING_Y);
		redBucket.setPosition(greenBucket.getX(), greenBucket.getY() + PADDING_Y);
	}

	/**
	 * 创建按钮
	 */
	public void createButton(){
		TextureRegionDrawable btnConfirm = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("colorMix/confirm_s.png"))));
		TextureRegionDrawable btnConfirmPush = new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("colorMix/confirm_s_push.png"))));
		TextureRegionDrawable btnDelete = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("colorMix/delete_s.jpg"))));
		TextureRegionDrawable btnDeletePush = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("colorMix/delete_s_push.jpg"))));
		TextureRegionDrawable btnRight = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("colorMix/arrow_right.png"))));
		TextureRegionDrawable btnRightPush = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("colorMix/arrow_right_push.png"))));
		
		confirmBtn = new ImageButton(btnConfirm, btnConfirmPush);
		deleteBtn = new ImageButton(btnDelete, btnDeletePush);
		nextBtn = new ImageButton(btnRight, btnRightPush);
		
		//设定按钮坐标
		confirmBtn.setPosition(700, PADDING / 2);
		deleteBtn.setPosition(confirmBtn.getX() + confirmBtn.getWidth() + PADDING, confirmBtn.getY());
		nextBtn.setPosition(deleteBtn.getX() + deleteBtn.getWidth() + PADDING, deleteBtn.getY());
		
		confirmBtn.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//TODO 添加一种颜色
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
				//TODO 删除一种颜色
				if((colorNum <= colorArea.length) && (colorNum > 0)){
					spriteForColorSqr[colorNum - 1].setColor(new Color(1, 1, 1, 1));
					colorNum--;
					System.out.println("delete a color");
				}
				
				return true;
			}
		});
		
		nextBtn.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//TODO 完成颜色配置
				isFinished = true;
				System.out.println(isFinished);
				return true;
			}
		});
		
	}

	/**
	 * 添加的颜色显示区域
	 */
	public void mixedColorArea(){
		colorArea = new Texture[3];
		spriteForColorSqr = new Sprite[3];
		for (int i = 0; i < colorArea.length; i++) {
			colorArea[i] = new Texture(Gdx.files.internal("colorMix/colorSquare.png"));
			spriteForColorSqr[i] = new Sprite(colorArea[i]);
			spriteForColorSqr[i].setColor(1, 1, 1, 1);
			spriteForColorSqr[i].setPosition(1400, 700 - i * PADDING_Y);
		}
		
	}
	
	/**
	 * 点击颜色显示区域，显示具体rgb量
	 * @param x 点击的x坐标
	 * @param y 点击的y坐标
	 * @param isTouched 是否按下
	 */
	public static void rgbDisplay(float x, float y, boolean isTouched){
		if(isTouched){
			for (Sprite s : spriteForColorSqr) {
				if((x >= s.getX()) && (x <= s.getX() + s.getWidth()) &&
						(y >= s.getY()) && (y <= s.getY() + s.getHeight())){
					Color c = s.getColor();
					red.spritebutton.setPosition(c.r * red.getWidth() + red.getX(), red.getY());
					green.spritebutton.setPosition(c.g * green.getWidth() + green.getX(), green.getY());
					blue.spritebutton.setPosition(c.b * green.getWidth() + blue.getX(), blue.getY());
				}
			}
		}
	}
	
	/**
	 * 获取设定的颜色
	 * @return
	 */
	public static Color[] getColors(){
		colors = new Color[colorArea.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = spriteForColorSqr[i].getColor();
		}
		return colors;
	}
}
