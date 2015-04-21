package sup.mario.game;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameWindow extends JFrame implements KeyListener, Runnable {
//extends JFrame implements KeyListener, Runnable to listen the Keyboard and achieve the Thread method - use to repaint the JFrame
	
	public Mario superMario = null;				//initialize the Mario class. null type at the first
	public BackGround nowBackGround = null;		//initialize the BackGround class. null at the first. 
	
	private int nowBackGroundNumber = 0;		//indicate which background is the Mario in. Only being used in JFrame so its private. 

	public static void main(String[] args)
	{
		new GameWindow();
	}
	
	//Constructor of GameWindow
	public GameWindow()
	{
		try {
			MediaLibrary.init();										//Initialize all medium file in MediaLibrary class. 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setTitle("Flying Super Mario");							//set the Title of window
		this.setSize(900,600);											//
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;	// set the Window size
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;//		
		this.setLocation((width-900)/2, (height-600)/2);				//set the window at the center position when the window shows
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );			//set the default close operation
		this.setVisible(true);											//set the window visible
		this.setResizable(false);										//window can't be adjusted();
		
		this.addKeyListener(this);										//add KeyListener, achieve the KeyListenner method. 
		
		nowBackGround = new BackGround(1,false);						//initialize the first background. 
		superMario = new Mario(0,480,nowBackGround);					//initialize Mario. will send the x, y coordinate and background information to its constructor. 
		try {
			File musicFile = new File(System.getProperty("user.dir") + "/bin/" + "mario.midi");
			URI uri = musicFile.toURI();
			URL url;
			url = uri.toURL();
			AudioClip ac = Applet.newAudioClip(url);
			ac.loop();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Thread t = new Thread(this);									//initialize the Thread
		t.start();														//Thread starts
	}
	
	
	public void paint(Graphics g){
		BufferedImage image = new BufferedImage(900,600,BufferedImage.TYPE_3BYTE_BGR);	//set up a second BufferedImage, reason is to avoid the game graphics flashes. 
		Graphics g2 = image.getGraphics();
		
		try{
			g2.drawImage(this.nowBackGround.bgImage, 0, 0, this);						//draw the background on the screen. 
		
			Iterator<allEnemy> allEnemy_iter = this.nowBackGround.Enemy.iterator();		//draw all enemy on the screen. 
			while(allEnemy_iter.hasNext())
			{
				allEnemy alle = allEnemy_iter.next();
				g2.drawImage(alle.showImage,alle.x,alle.y,this);
			}
		
			Iterator<Obstruction> Obstruction_iter = this.nowBackGround.Obst.iterator();//draw all obstruction on the screen. 
			while(Obstruction_iter.hasNext())
			{
				Obstruction ob = Obstruction_iter.next();
				g2.drawImage(ob.showImage,ob.x,ob.y, this);
			}
			g2.drawImage(superMario.marioImage,superMario.x,superMario.y,this);			// draw Super Mario on the screen
			g2.drawString("Lives left:"+superMario.getLife(), 820, 50);
			g2.drawString("Score: "+superMario.getScore(), 20, 50);
			for(int i=0;i<superMario.powerLeft;i++)
			{
				g2.drawImage(MediaLibrary.Obst.get(12),20+30*i,55,this);
			}
			g.drawImage(image,0,0,this);
		}catch(Exception e)
		{
			
		}
	}
	
	public void keyPressed(KeyEvent e) {														//methods will be used by press the keyboard
		if(e.getKeyCode()==39 && superMario.finishGame==false)									//Keylistener, Mario goes right
		{
			superMario.faceToward = "right";
			superMario.move();
				
		}
		else if(e.getKeyCode()==37 && superMario.finishGame==false)								// Mario goes left
		{
			superMario.faceToward = "left";
			superMario.move();
		}
		else if(e.getKeyCode()==32 && superMario.finishGame==false && !superMario.powerRunOut)	//Mario jumps
		{
			superMario.spacePress = true;
			superMario.jump();
			
		}
		else if(e.getKeyCode()==38 && superMario.finishGame==false && superMario.ifCanPressUp)
		{
			superMario.upPress = true;
			superMario.jump();
		}
	}
	
	public void keyReleased(KeyEvent e) {	//methods will be used by releaseing the keyborad
		if(e.getKeyCode()==39)				// stop moving right
		{
			superMario.stop();		
		}
		
		else if(e.getKeyCode()==37)			// stop moving left
		{	
			superMario.stop();
		}
		else if(e.getKeyCode()==32)
		{
			superMario.spacePress = false;
			superMario.powerLeft --;
			superMario.jumpStatus = "down";
			superMario.down();	
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public BackGround changeBackground()	// use to change the background
	{
		nowBackGround = new BackGround(nowBackGroundNumber + 1,(nowBackGroundNumber + 1)==10?true:false);
		return nowBackGround;
	}
	
	public void run() {								// add run method to complete Thread
		while(true)
		{
			repaint();								// repaint the screen
			if(superMario.x>900)					//when the Mario runs over the Screen x-coordinate call changeBackground method. 
			{
				superMario.x = 0 ;					//when the background changed x will be 0
				superMario.y = superMario.y;		//y will keep the same
				nowBackGroundNumber ++ ;			//add the number 1, to initialize another background
				superMario.bg = changeBackground();	//background changed
			}
			if(superMario.getLife()<1)
			{
				
				int exitChoose = JOptionPane.showConfirmDialog(this, "Game is over. Do you want to try again?",
					      "Message", JOptionPane.YES_NO_OPTION);
				 
				 if(exitChoose==JOptionPane.NO_OPTION)
				 {
					System.exit(0);
				 }
				 else if(exitChoose==JOptionPane.YES_OPTION)
				 {
					 nowBackGround.allReset();
					 superMario.faceToward = "right";
					 superMario.marioImage = MediaLibrary.marioImage.get(0);
					 superMario.rightIndex = 0; 	
					 superMario.x = 0;
					 superMario.y = 480; 
					 superMario.xmove = 0; 
					 superMario.ymove = 0; 
					 superMario.setLife(3);
					 superMario.setScore(0);
					 superMario.powerLeft = 3;
				 }
			}
			else if(superMario.x>=760 && nowBackGround.flag && superMario.flagOnBot==true)
			{
				JOptionPane.showMessageDialog(this,"You finish the whole game! Your total scores are "+superMario.getScore()+ "!! Congratulations!!!");
				System.exit(0);
			}
			try {
				Thread.sleep(30);					//Thread sleep some time
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
