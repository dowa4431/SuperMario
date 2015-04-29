package wenjin.dongyao.tianfang.object;

import java.awt.image.BufferedImage;

public class Object {
	private int x;
	private int y;
	private BufferedImage image;
	private boolean cangotop;
	private boolean cangoright;
	private boolean cangoleft;
	
	public void move(){
		
	}
	
	public void down(){
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
