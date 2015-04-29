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
//	private int flowerimgindex;
//	private int turtlerightimgindex;
//	private int mushroomimgindex;
//	private int turtleleftimgindex;
	private int imgindex;
	
	//Constructor
	public Enemies(int x, int y, String status, String type){
		this.x = x;
		this.y = y;
		this.type = type;
		this.status = status;
		setImage(status, type);
//		flowerimgindex = 0;
//		turtlerightimgindex = 0;
//		turtleleftimgindex = 0;
//		mushroomimgindex = 0;
		imgindex = 0;
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
			showImage = MediaLibrary.getMushroomImage().get(imgindex++);
		}else if(status.equals("left-move") && type.equals("turtle")){
			showImage = MediaLibrary.getTurtleImage().get(imgindex++);
		}else if(status.equals("right-move") && type.equals("turtle")){
			showImage = MediaLibrary.getTurtleImage().get(imgindex++);
		}else if(status.equals("up-move") && type.equals("flower")){
			showImage = MediaLibrary.getFlowerImage().get(imgindex++);
		}
		
		if(imgindex>1){
			imgindex = 0;
		}
	}
	
	public void getDieImage(String type){
		
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
