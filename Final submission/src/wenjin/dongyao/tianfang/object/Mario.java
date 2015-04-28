package wenjin.dongyao.tianfang.object;

import java.awt.image.BufferedImage;
import java.util.List;

import wenjin.dongyao.tianfang.bean.Background;
import wenjin.dongyao.tianfang.graphics.GameGraphics;
import wenjin.dongyao.tianfang.media.MediaLibrary;
import wenjin.dongyao.tianfang.thread.MarioThread;
import wenjin.dongyao.wang.datainit.Datainit;

public class Mario {
	private int x;								//x-coordinate
	private int y;								//y-coordinate
	private BufferedImage showImage = null;		//Image
	private String type;						
	private String status;
	
	//Constructor
	public Mario(int x, int y){
		this.x = x;
		this.y = y;
		this.status = "stop";
		setImage();
		new Thread(new MarioThread(this)).start();
	}
	
	public void moveleft(){
		x -= Datainit.getSpeedValue().getMario_speed();
	}
	
	public void moveright(){
		x += Datainit.getSpeedValue().getMario_speed();
	}
	
	public void jump(){
		y -= 4;
	}
	
	public void fallmove(){
		y += 4;
	}
	
	public void stop(){
		
	}
	
	public void reset(){
		x = 0;
	}
	
	
	public void kill(List<Enemies> enemieslist){
		List<Enemies> enlist = GameGraphics.getCurrentBackground().getEnemiesList();
		for(Enemies en:enemieslist){
			enlist.remove(en);
		}
		
	}
	
	public void rebornreset(){
		x = 0;
		y = 480;
		Background background = Datainit.getBackground(GameGraphics.getCurrentBackground().getStage());
		GameGraphics.setCurrentBackground(background);
		List<Enemies> enlist = background.getEnemiesList();
		for(Enemies en:enlist){
			en.startThread();
		}
	}
	
	public void breakbricks(List<Obstruction> breakoblist){
		List<Obstruction> oblist = GameGraphics.getCurrentBackground().getObstructionList();
		for(Obstruction ob: breakoblist){
			if(ob.getType()==6){
				oblist.remove(ob);
			}else if(ob.getType()==10){
				ob.setType(8);
				ob.setImage(8);
			}
		}
	}
	
	//initialize the Image
	public void setImage()
	{
		this.showImage = MediaLibrary.getMarioImage().get(0);	
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
