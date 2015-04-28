package wenjin.dongyao.tianfang.graphics;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import wenjin.dongyao.tianfang.bean.Background;
import wenjin.dongyao.tianfang.keylistener.MarioKeyListener;
import wenjin.dongyao.tianfang.media.MediaLibrary;
import wenjin.dongyao.tianfang.object.Enemies;
import wenjin.dongyao.tianfang.object.Mario;
import wenjin.dongyao.tianfang.object.Obstruction;
import wenjin.dongyao.wang.datainit.Datainit;

public class GameGraphics extends JFrame{
	
	private static Background background = null;
	private static int stagenum = 9;
	private int stagetotalnum;
	
	static{
		MediaLibrary.init();
		background = Datainit.getBackground(stagenum);
		for(Enemies en:background.getEnemiesList()){
			en.startThread();
		}
	}
	
	
	public GameGraphics(){
		try{
			this.setTitle("Flying Super Mario");							//set the Title of window
			this.setSize(900,600);											//
			int width = Toolkit.getDefaultToolkit().getScreenSize().width;	// set the Window size
			int height = Toolkit.getDefaultToolkit().getScreenSize().height;//		
			this.setLocation((width-900)/2, (height-600)/2);				//set the window at the center position when the window shows
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );			//set the default close operation
			this.setVisible(true);											//set the window visible
			this.setResizable(false);										//window can't be adjusted();
			this.addKeyListener(new MarioKeyListener(background.getMario()));										//add KeyListener, achieve the KeyListenner method. 
			
			File musicFile = new File("media/mario.midi");
			URI uri = musicFile.toURI();
			URL url;
			url = uri.toURL();
			AudioClip ac = Applet.newAudioClip(url);
			ac.loop();
			
			stagetotalnum = Datainit.getPropContainers().getTotalstagenum();
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "Mario gets initializing errors!");
			System.exit(0);
		}
	}
	
	public static Background getCurrentBackground(){
		return background;
	}
	public static void setCurrentBackground(Background background) {
		GameGraphics.background = background;
	}

	
	public void paint(Graphics g){
		BufferedImage image = new BufferedImage(900,600,BufferedImage.TYPE_3BYTE_BGR);	//set up a second BufferedImage, reason is to avoid the game graphics flashes. 
		Graphics g2 = image.getGraphics();
		
		try{
							//draw the background on the screen. 
			if(background.getMario().getX()>=900 && stagenum<stagetotalnum){
				//System.out.println(background.getMario().getX());
				stagenum++;
				//System.out.println(stagenum);
				background = Datainit.getBackground(stagenum);
				background.getMario().reset();
				for(Enemies en:background.getEnemiesList()){
					en.startThread();
				}
			//	System.out.println(background.getBackgroundimg());
			}
			g2.drawImage(background.getBackgroundimg(), 0, 0, this);
			List<Obstruction> oblist = background.getObstructionList();
			List<Enemies> enlist = background.getEnemiesList();
			Mario mario = background.getMario();
				
			g2.drawImage(mario.getShowImage(), mario.getX(), mario.getY(), this);
			for(Enemies en:enlist){
				g2.drawImage(en.getShowImage(), en.getX(), en.getY(), this);
			}
			for(Obstruction ob:oblist){
				g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
			}
			
			g.drawImage(image,0,0,this);
		}catch(Exception e)
		{
			
		}
	}
}
