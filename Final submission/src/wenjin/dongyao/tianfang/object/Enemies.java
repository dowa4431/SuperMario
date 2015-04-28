package wenjin.dongyao.tianfang.object;

import java.awt.image.BufferedImage;

import wenjin.dongyao.tianfang.media.MediaLibrary;
import wenjin.dongyao.tianfang.thread.EnemiesThread;
import wenjin.dongyao.wang.datainit.Datainit;

public class Enemies {
	private int x;								//x-coordinate
	private int y;								//y-coordinate
	private BufferedImage showImage = null;		//Image
	private String type;						
	private String status;
	
	//Constructor
	public Enemies(int x, int y, String status, String type){
		this.x = x;
		this.y = y;
		this.type = type;
		this.status = status;
		setImage(status, type);
	}
	
	public void startThread(){
		new Thread(new EnemiesThread(this)).start();
	}
	
	public void moveleft(){
		x -= Datainit.getSpeedValue().getMushroom_and_turtle_h_speed();
	}
	
	public void moveright(){
		x += Datainit.getSpeedValue().getMushroom_and_turtle_h_speed();
	}
	
	public void flowermoveup(){
		y -= Datainit.getSpeedValue().getFlower_speed();
	}
	
	public void flowermovedown(){
		y += Datainit.getSpeedValue().getFlower_speed();
	}
	
	public void enemiesfallmove(){
		y += Datainit.getSpeedValue().getMushroom_and_turtle_d_speed();
	}
	
	//initialize the Image
	public void setImage(String status, String type)
	{
		if(type.equals("mushroom")){
			showImage = MediaLibrary.getMushroomImage().get(0);
		}else if(status.equals("left-move") && type.equals("turtle")){
			showImage = MediaLibrary.getTurtleImage().get(0);
		}else if(status.equals("right-move") && type.equals("turtle")){
			showImage = MediaLibrary.getTurtleImage().get(2);
		}else if(status.equals("up-move") && type.equals("flower")){
			showImage = MediaLibrary.getFlowerImage().get(0);
		}else if(status.equals("down-move") && type.equals("flower")){
			showImage = MediaLibrary.getFlowerImage().get(0);
		}
			
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
