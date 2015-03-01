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
 * ��ɫ����
 */
public class ColorMixInterface extends Stage {

	// ͼƬ��RGBֵ
	float r;
	float g;
	float b;

	// ���
	int PADDING = 200;
	int PADDING_Y = 100;

	//�ж�����˶�������ɫ
	int colorNum;
	//�ж��Ƿ������ɫ����
	boolean isFinished = false;
	
	Image redBucket;
	Image greenBucket;
	Image blueBucket;
	ImageButton confirmBtn;
	ImageButton deleteBtn;
	ImageButton nextBtn;
//	ImageButton colorSquare[];
	
	/**
	 * ��ɫ��
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
	 * ������ť
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
		
		//�趨��ť����
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
		
		nextBtn.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//TODO �����ɫ����
				isFinished = true;
				System.out.println(isFinished);
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
			colorArea[i] = new Texture(Gdx.files.internal("colorMix/colorSquare.png"));
			spriteForColorSqr[i] = new Sprite(colorArea[i]);
			spriteForColorSqr[i].setColor(1, 1, 1, 1);
			spriteForColorSqr[i].setPosition(1400, 700 - i * PADDING_Y);
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
					red.spritebutton.setPosition(c.r * red.getWidth() + red.getX(), red.getY());
					green.spritebutton.setPosition(c.g * green.getWidth() + green.getX(), green.getY());
					blue.spritebutton.setPosition(c.b * green.getWidth() + blue.getX(), blue.getY());
				}
			}
		}
	}
	
	/**
	 * ��ȡ�趨����ɫ
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
