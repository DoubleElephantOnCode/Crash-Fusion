package com.mygdx.game;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Color;

public class MixColor {
	
	static Color red = new Color(0.95f, 0, 0, 1);
	static Color green = new Color(0, 1.0f, 0, 1);
	static Color blue = new Color(0, 0, 0.9f, 1);
	static Color yellow = new Color(1, 1, 0, 1);
	static Color white = new Color(1, 1, 1, 1);
	static Color none = new Color(0, 0, 0, 0);
	
	static float hsb_red[] = {0, 1, 1.0f};
	static float hsb_green[] = {120, 1, 1.0f};
	static float hsb_blue[] = {240, 1, 1.0f};
	
	public static float[] rgb2hsb(float rgbR, float rgbG, float rgbB) {  
	    assert 0 <= rgbR && rgbR <= 1;  
	    assert 0 <= rgbG && rgbG <= 1;  
	    assert 0 <= rgbB && rgbB <= 1;
	    rgbR *= 255.0f;
	    rgbG *= 255.0f;
	    rgbB *= 255.0f;
	    float[] rgb = new float[] { rgbR, rgbG, rgbB };  
	    Arrays.sort(rgb);  
	    float max = rgb[2];
	    float min = rgb[0];  
	  
	    float hsbB = max / 255.0f;  
	    float hsbS = max == 0 ? 0 : (max - min) / (float) max;  
	  
	    float hsbH = 0;  
	    if (max == rgbR && rgbG >= rgbB) {  
	        hsbH = (rgbG - rgbB) * 60f / (max - min) + 0;  
	    } else if (max == rgbR && rgbG < rgbB) {  
	        hsbH = (rgbG - rgbB) * 60f / (max - min) + 360;  
	    } else if (max == rgbG) {  
	        hsbH = (rgbB - rgbR) * 60f / (max - min) + 120;  
	    } else if (max == rgbB) {  
	        hsbH = (rgbR - rgbG) * 60f / (max - min) + 240;  
	    }  
	  
	    return new float[] { hsbH, hsbS, hsbB };  
	}
	  
	public static float[] hsb2rgb(float h, float s, float v) {  
	    assert Float.compare(h, 0.0f) >= 0 && Float.compare(h, 360.0f) <= 0;  
	    assert Float.compare(s, 0.0f) >= 0 && Float.compare(s, 1.0f) <= 0;  
	    assert Float.compare(v, 0.0f) >= 0 && Float.compare(v, 1.0f) <= 0;  
	  
	    float r = 0, g = 0, b = 0;  
	    int i = (int) ((h / 60) % 6);  
	    float f = (h / 60) - i;  
	    float p = v * (1 - s);  
	    float q = v * (1 - f * s);  
	    float t = v * (1 - (1 - f) * s); 
	    switch (i) {
	    case 0:  
	        r = v;  
	        g = t;  
	        b = p;  
	        break;  
	    case 1:  
	        r = q;  
	        g = v;  
	        b = p;  
	        break;  
	    case 2:  
	        r = p;  
	        g = v;  
	        b = t;  
	        break;  
	    case 3:  
	        r = p;  
	        g = q;  
	        b = v;  
	        break;  
	    case 4:  
	        r = t;  
	        g = p;  
	        b = v;  
	        break;  
	    case 5:  
	        r = v;  
	        g = p;  
	        b = q;  
	        break;  
	    default:  
	        break;  
	    }
	    return new float[] { r, g, b};
	}
	
	public static float[] hsb2rgb(float[] hsb) {
	    float[] rgb= new float[3];        
	    //先令饱和度和亮度为100%，调节色相h
	    for(int offset=240,i=0;i<3;i++,offset-=120) {
	        //算出色相h的值和三个区域中心点(即0°，120°和240°)相差多少，然后根据坐标图按分段函数算出rgb。但因为色环展开后，红色区域的中心点是0°同时也是360°，不好算，索性将三个区域的中心点都向右平移到240°再计算比较方便
	        float x=Math.abs((hsb[0]+offset)%360-240);
	        //如果相差小于60°则为255
	        if(x<=60) rgb[i]=255;
	        //如果相差在60°和120°之间，
	        else if(60<x && x<120) rgb[i]=((1-(x-60)/60)*255);
	        //如果相差大于120°则为0
	        else rgb[i]=0;
	    }
	    //在调节饱和度s
	    for(int i=0;i<3;i++)
	        rgb[i]+=(255-rgb[i])*(1-hsb[1]);
	    //最后调节亮度b
	    for(int i=0;i<3;i++)
	        rgb[i]*=hsb[2];
	    for(int i=0;i<3;i++)
	    	rgb[i]/=255;
	    return rgb;
	}
	
	public static Color mix(Color c1, float p1, Color c2, float p2){
		float[] hsb1 = rgb2hsb(c1.r, c1.g, c1.b);
		float[] hsb2 = rgb2hsb(c2.r, c2.g, c2.b);
		float[] hsb = new float[3];
		if(Math.abs(hsb1[0] - hsb2[0]) <= 180){
			hsb[0] = (hsb1[0] * p1 + hsb2[0] * p2)/(p1 + p2);
		}
		else if(Math.abs(hsb1[0] - hsb2[0] + 360) < 180){
			hsb1[0] += 360;
			hsb[0] = (hsb1[0] * p1 + hsb2[0] * p2)/(p1 + p2);
			if(hsb[0] > 360){
				hsb[0] -= 360;
			}
		}
		else if(Math.abs(hsb1[0] - hsb2[0] - 360) < 180){
			hsb2[0] += 360;
			hsb[0] = (hsb1[0] * p1 + hsb2[0] * p2)/(p1 + p2);
			if(hsb[0] > 360){
				hsb[0] -= 360;
			}
		}
		hsb[1] = (hsb1[1] * p1 + hsb2[1] * p2)/(p1 + p2);
		
		hsb[2] = (hsb1[2] * p1 + hsb2[2] * p2)/(p1 + p2);
//		System.out.println(hsb[0] + ", " + hsb[1] + ", " + hsb[2] + " = hsb");
		float[] rgb = hsb2rgb(hsb[0], hsb[1], hsb[2]);
//		System.out.println(rgb[0] + ", " + rgb[1] + ", " + rgb[2] + " = rgb");
		return new Color(rgb[0], rgb[1], rgb[2], 1);
	}
	
	public static Color mix(float r,float g, float b){
		if(r + g + b == 0) return white;
		
		if(g + b == 0) return red;
			
		float h_rg;
		float h_rb;
		float[] hsb = {0.0f, 1.0f, 1.0f};
		float rg = g / (g + b) * r;
		float rb = b / (g + b) * r;
		if(g == 0) g = 0.0001f;
		if(b == 0) b = 0.0001f;
		h_rg = 120.0f * g / (g + rg);
		h_rb = (360.0f * rb + 240.0f * b) / (rb + b);
		hsb[0] = (h_rg * g + h_rb * b) / (g + b);
		if(hsb[0] >= 360) hsb[0] -= 360;
		
		float d = Math.abs(g - b);
		r -= d;
		if(r > 0)
			hsb[0] = hsb[0] * (g + b) / (r + g + b);
		
		float[] rgb = hsb2rgb(hsb[0], hsb[1], hsb[2]);
		return new Color(rgb[0], rgb[1], rgb[2], 1);
	}
	
	public static Color mixRGB(float r,float g, float b){
		if(r + g + b == 0) return none;
		if(r + g == 0) return blue;
		Color temp = calculateAlphaValue(red, green, g / (r + g));
		temp = calculateAlphaValue(temp, blue, b / (r + g + b));
		float[] hsb = rgb2hsb(temp.r, temp.g, temp.b);
		hsb[1] = (float) Math.sqrt(hsb[1]);
		hsb[2] = (float) Math.sqrt(hsb[2]);
		float rgb[] = hsb2rgb(hsb);
		return new Color(rgb[0], rgb[1], rgb[2], 1);
	}
	
	protected static Color calculateAlphaValue(Color c1, Color c2, float alpha)
	{
	  if (alpha < 0)
	  alpha = 0;
	  else if (alpha > 1)
	  alpha = 1;
	 
	  float R = (c1.r * (1 - alpha) + c2.r * alpha);
	  float G = (c1.g * (1 - alpha) + c2.g * alpha);
	  float B = (c1.b * (1 - alpha) + c2.b * alpha);
	  
	  R = (float) Math.sqrt(R);
	  G = (float) Math.sqrt(G);
	  B = (float) Math.sqrt(B);
	 
	  return new Color(R, G, B, 1);
	}
	
}
