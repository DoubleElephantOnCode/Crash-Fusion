package colorMix;

/**
 * 
 * @author HalaWKS
 *
 */
public class Color {

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
	
	public Color(float r, float g, float b) {
		this.red = r;
		this.green = g;
		this.blue = b;
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
	
	
	
}
