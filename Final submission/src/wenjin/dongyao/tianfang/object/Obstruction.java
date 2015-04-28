package wenjin.dongyao.tianfang.object;

import java.awt.image.BufferedImage;

import wenjin.dongyao.tianfang.media.MediaLibrary;

public class Obstruction {
	private int x;								//x-coordinate
	private int y;								//y-coordinate
	private BufferedImage showImage = null;		//Image
	private int type;							//type of obstruction
	
	//Constructor
	public Obstruction(int x, int y, int type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		setImage(type);
	}
	
	//initialize the Image
	public void setImage(int type)
	{
		showImage = MediaLibrary.getObst().get(type);
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

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
