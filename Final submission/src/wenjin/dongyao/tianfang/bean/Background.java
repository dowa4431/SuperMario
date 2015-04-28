package wenjin.dongyao.tianfang.bean;

import java.awt.image.BufferedImage;
import java.util.List;

import wenjin.dongyao.tianfang.object.Enemies;
import wenjin.dongyao.tianfang.object.Mario;
import wenjin.dongyao.tianfang.object.Obstruction;

public class Background {
	
	private List<Obstruction> obstructionList = null;
	private BufferedImage backgroundimg = null;
	private List<Enemies> enemiesList = null;
	private Mario mario = null;
	private int stage = 0;

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public List<Obstruction> getObstructionList() {
		return obstructionList;
	}

	public void setObstructionList(List<Obstruction> obstruction) {
		this.obstructionList = obstruction;
	}

	public List<Enemies> getEnemiesList() {
		return enemiesList;
	}

	public void setEnemiesList(List<Enemies> enemiesList) {
		this.enemiesList = enemiesList;
	}
	public Mario getMario() {
		return mario;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}

	public BufferedImage getBackgroundimg() {
		return backgroundimg;
	}

	public void setBackgroundimg(BufferedImage backgroundimg) {
		this.backgroundimg = backgroundimg;
	}
	

}
