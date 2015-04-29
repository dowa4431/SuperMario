package sup.mario.game;

import java.awt.image.BufferedImage;

public class Obstruction {
	public int x;								//x-coordinate
	public int y;								//y-coordinate
	public BufferedImage showImage = null;		//Image
	public int type;							//type of obstruction
	
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
		showImage = MediaLibrary.Obst.get(type);
	}
}
