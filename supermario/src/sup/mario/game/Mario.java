package sup.mario.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.lang.Math;


public class Mario implements Runnable {
	public int x;								//x-coordinate 
	public int y;								//y-coordinate 
	public BufferedImage marioImage = null ;	//Mario image
	public String faceToward;					//which direction that Maro faces to, Left or Right
	public int rightIndex = 0;					//get the right direction image
	public int leftIndex = 5;					//get left direction image
	public int xmove = 0;						//speed of Mario moves
	public int ymove = 0;
	public String jumpStatus = "down";			//Mario goes up or down
	public BackGround bg = null;				//Current background that Mario is in
	public boolean finishGame = false;			//judge if the game finished
	public int powerLeft = 0;					//power left for using inifinte jump
	public boolean powerRunOut;					//judge whether power left is equal to zero
	public boolean flagOnBot = false;			//flag on bottom which means game finished
	public boolean spacePress = false;			//
	public boolean upPress = false;				//there three booleans value is used for normal jump,
	public  boolean ifCanPressUp = true;		//so that the Mario can fall back to the ground

	private int s1 = 0;							//represent the score that player earns
	private int s2 = 0;							//represent the score that player earns				
	private int jumpMeasurement=0;				//if Mario jumpimg to the highest point, it will fall back
	private int questionMarkEat = 0;			//how many question Mark that Mario gets, gets three will add one inifinate jump chance
	private int originX;						//original x
	private int originY;						//original y
	private int life;							//life left
	private int score;							//scores that player gets
	private Thread t;							//create Thread in this class
	
	//Mario constructor
	public Mario(int x, int y, BackGround bg)
	{
		originX = x;							
		originY = y;
		this.x = originX;
		this.y = originY;
		this.faceToward = "right";
		this.bg = bg;
		this.powerLeft = 3;
			
		marioImage = MediaLibrary.marioImage.get(0);
		this.life = 3;
		this.score = 0;
		
		t = new Thread(this);
		t.start();	
	}
	
	//move method
	public void move()
	{
		if(faceToward.equals("right"))//change the faceToward in GameWindow
		{
			xmove = 5;
		}
		else if(faceToward.equals("left"))
		{
			xmove = -5;
		}
	}
	
	//move stops
	public void stop()
	{
		rightIndex = 0;
		leftIndex = 5;
		xmove = 0;
	}
	
	//jump stops
	public void jumpStop()
	{	
		if(ymove!=0&&jumpStatus.equals("down"))//only moves vertically and down, it could Stop Jumping. 
		{
			//sleep_time = 0;
			//if(sleep_time==0)
			//{
			ymove = 0;
			rightIndex = 0;
			leftIndex = 5;
				//sleep_time++;
			//}
		}
	}
	
	//Mario goes down if it is in the air
	public void down()
	{
		if(this.jumpStatus.equals("up"))		//if Mario is moving up, no down method
		{
			jump();
		}
		else
		{
			this.jumpStatus = "down";
			ymove = 10;
		}
	}

	//Mario jumps method
	public void jump()
	{
		this.jumpStatus = "up";
		ymove = -10;
	}
	
	
	public void run()
	{	
		int ADD_ENEMY_SLEEP_TIME = 0;					//sleep time during adding the enemy
		int TURTLE_OR_MUSHROOM = 0;						//adding turtle or mushroom
		while(true)
		{	
			boolean canRight = true;					//as same as enemies 
			boolean canLeft = true;						//
			boolean onLand = false;						//
			boolean onBot = false;						//
			powerRunOut = false;						
			
			/*
			 * sence 7 and sence 9 will add the enemy 
			 * automatically during a specific time
			 */
			if(bg.sort==7)
			{
				if(ADD_ENEMY_SLEEP_TIME==0)
				{ 
					if(TURTLE_OR_MUSHROOM%2==0)
						bg.Enemy.add(new allEnemy(850,120,"turtle","left-move",bg));
					else 
						bg.Enemy.add(new allEnemy(850,120,"mushroom","left-move",bg));
				}
				ADD_ENEMY_SLEEP_TIME++;
				TURTLE_OR_MUSHROOM++;
				if(ADD_ENEMY_SLEEP_TIME>150)
					ADD_ENEMY_SLEEP_TIME = 0;
			}
			else if(bg.sort==9)
			{
				if(ADD_ENEMY_SLEEP_TIME==0)
				{ 
					if(TURTLE_OR_MUSHROOM%2==0)
					{
						for(int i=0;i<5;i++)
						{
							bg.Enemy.add(new allEnemy(240*i+420,0,"mushroom","left-move",bg));
							
						}
					}
					else 
					{
						for(int i=0;i<6;i++)
						{
							bg.Enemy.add(new allEnemy(160*i+420,0,"turtle","left-move",bg));
							
						}
					}
				}
				ADD_ENEMY_SLEEP_TIME++;
				TURTLE_OR_MUSHROOM++;
				if(ADD_ENEMY_SLEEP_TIME>150)
					ADD_ENEMY_SLEEP_TIME = 0;
			}
			
			
			/*
			 * this part of code is to finish the game
			 * when the flag goes down to the ground at the last background
			 * the user cannot control the Mario, and it will aumatically run
			 * to the gate. 
			 */
			if(!flagOnBot)
			{
				if((x >= 490 && x<=550 && y>=180 && bg.flag))
				{
					finishGame = true;
					stop();
					jumpStatus = "down";
					down();
					if(faceToward.equals("right"))
						marioImage = MediaLibrary.marioImage.get(rightIndex);
					else if(faceToward.equals("left"))
						marioImage = MediaLibrary.marioImage.get(leftIndex);
					if(y==480)
					{
						onLand = true;
					}
					Obstruction flag = bg.Obst.get(15);
					while(flag.y<450&&onLand)
					{
						flag.y += 5;
						try {
							Thread.sleep(30);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(flag.y>=450)
					{
						flagOnBot = true;
					}
				}
			}
			else if(flagOnBot)
			{
				faceToward = "right";
				move();
			}
			
			if(bg.flag==true && x==840)
			{
				canRight = false;
			}
			
			/*
			 * use imge to judge whether Mairo is dead
			 * if Mario is dead, call allRest method 
			 * in BackGround class
			 */
			if(marioImage==MediaLibrary.marioImage.get(10)||y>600)
			{
				this.life --;
				if(this.life<1)
				{
					marioImage = MediaLibrary.marioImage.get(10);
				}
				else
				{
					bg.allReset();
					faceToward = "right";
					x = originX;
					y = originY;
					powerLeft = 3;
					questionMarkEat = 0;
				}
			}
			
			/*
			 * add one life when the player gets every 1000 score
			 */
			if(s2>=1000)
			{
				life++;
				s2 = 0;
			}
			
			/*
			 * judge whether Mario meets the Obstruction
			 * For example, if the obstruction is at right of
			 * Mario. it cannot go to right
			 */
			
			for(int i=0;i<bg.Obst.size();i++)
			{	
				Obstruction ob = bg.Obst.get(i);
				if(ob.type!=11)
				{
					if((ob.y+60>y && ob.y-60<y)&&ob.x==x+60)
					{
						canRight = false;
					}
					if((ob.y+60>y && ob.y-60<y)&&ob.x==x-60||x<=0)
					{
						canLeft = false;
					}
					if(ob.y == y + 60 && ob.x+60>x && ob.x-60<x)
					{	
						onLand = true;
						jumpStop();
						if(bg.sort==4 && ob.x==320 && ob.y==240)
						{
							bg.meetTrap(i);
						}
						else if(bg.sort==4 && ob.x==720 && ob.y==540)
						{
							bg.meetTrap(i);
						}
						else if(bg.sort==6 && ob.x==500 && ob.y==240)
						{
							bg.meetTrap(i);
						}
						else if(bg.sort==6 && ob.x==260 && ob.y==360)
						{
							bg.meetTrap(i);
						}
						else if(bg.sort==5 && ob.x==440 && ob.y==180)
						{
							bg.meetTrap(i);
						}
						else if(bg.sort==8 && ob.x==450 && ob.y==300)
						{
							bg.meetTrap(i);
						}
					}
					if(ob.y == y - 60 && ob.x+50>x && ob.x-50<x)
					{
						onBot = true;
						if(ob.y == y - 60 && ob.x+30>x && ob.x-30<x && ob.type==10||ob.y == y - 60 && ob.x+30>x && ob.x-30<x && ob.type==6)
						{
							if(ob.type==10)
							{
								s1 = 30;
								s2 += s1;
								score += s1;
							}
							else if(ob.type==6)
							{
								s1 = 10;
								s2 += s1;
								score += s1;
							}
							
							if(ob.type==10)
							{
								questionMarkEat ++;
								if(questionMarkEat%3==0)
								{
									powerLeft++;
								}
								if(powerLeft>5)
								{
									powerLeft = 5;
								}
							}
							bg.resetObstruction(i);
							
							if(bg.sort==4 && ob.x==460 && ob.y==60)
								bg.meetTrap(i);
						}
						jumpStatus = "down";//change jump_status so that jumpStop() will be called
						jumpStop();
					}
					if(Math.abs(ob.x - x)==45&&(ob.y+60>y && ob.y-60<y))
					{
						if(faceToward.equals("left"))
						{
							x += 15;
						}
						else if(faceToward.equals("right"))
						{
							x -= 15;
						}
					}
				}
			}
			
			
			/*
			 * return true if number of power is zero
			 */
			if(powerLeft<=0)
			{
				powerLeft = 0;
				powerRunOut = true;
			}
			
			/*
			 * if Mario is on the air, player cannot press up arrow again
			 */
			if(!onLand)
			{
				ifCanPressUp = false;
				down();
			}
			else if(onLand)
			{
				ifCanPressUp = true;
			}
			
			/*
			 * change the jump status
			 */
			if(upPress && !spacePress)
			{
				if(jumpStatus.equals("up"))
					jumpMeasurement += ymove;
				if(jumpMeasurement<-190||onBot)
				{
					this.jumpStatus = "down";
					jumpMeasurement = 0;
				}
			}
			
			/*
			 * get Image when the Mario goes right or left
			 * 
			 */
			if(faceToward.equals("right"))
			{	
				marioImage = MediaLibrary.marioImage.get(rightIndex);
				if(canRight&&xmove>0)
				{
					x += xmove;
				}
				y += ymove;
				if(xmove!=0||ymove!=0)
					rightIndex++;
				if(rightIndex>4)
					rightIndex=0;	
			}
			if(faceToward.equals("left"))
			{
				marioImage = MediaLibrary.marioImage.get(leftIndex);
				if(canLeft&&xmove<0)
				{
					x += xmove;
				}
				y += ymove;
				if(xmove!=0||ymove!=0)
					leftIndex++;
				if(leftIndex>9)
					leftIndex = 5;		
			}	
			
			/*
			 * this part of code is used to judge Mario meets mushroom, turtle, or flower
			 * for example, Mario cannot kill flower. If Mario meets flower, it will 
			 * always be dead. 
			 */
			for(int j=0;j<bg.Enemy.size();j++)
			{
				allEnemy ae = bg.Enemy.get(j);
				
				if(ae.showImage==MediaLibrary.mushroomImage.get(2))//use Image to judge if enemy is dead. 
				{
					s1 = 50;
					s2 += s1;
					if(s1!=0)
						score = score + s1;
					bg.removeElement(j);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(ae.showImage==MediaLibrary.turtleImage.get(4))
				{
					s1 = 100;
					s2 += s1;
					if(s1!=0)
						score = score + s1;
					bg.removeElement(j);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(y + 60 >= ae.y && y + 30 <= ae.y && ae.x+60>x && ae.x-60<x && jumpStatus.equals("down") && ae.type.equals("flower")==false)//kill mushroom or turtle
				{
					bg.killEnemy(j);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(ae.type.equals("flower")&&ae.y-60<y && x+60>ae.x && x-60<ae.x)//meet flower, die directly, cannot kill flower
				{
					marioImage = MediaLibrary.marioImage.get(10);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(ae.y==y && x+60>=ae.x && x-60<=ae.x)//meet mushroom straight, could die
				{
					marioImage = MediaLibrary.marioImage.get(10);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(ae.y+60>y && Math.abs(ae.y-y)<60 && x+60>=ae.x && x-60<=ae.x && (jumpStatus.equals("up")||onLand==true))//meet enemy upward, could die
				{
					marioImage = MediaLibrary.marioImage.get(10);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(Math.abs(ae.x - x)<=55&&(ae.y+60>y && ae.y-60<y))
				{
					marioImage = MediaLibrary.marioImage.get(10);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}


}
