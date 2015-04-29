package wenjin.dongyao.tianfang.thread;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import wenjin.dongyao.tianfang.graphics.GameGraphics;
import wenjin.dongyao.wang.datainit.Datainit;

public class GameWindowThread extends GameGraphics implements Runnable{
	

	public GameWindowThread(){
		super();
	}

	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(Datainit.getSpeedValue().getSleep_time());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
