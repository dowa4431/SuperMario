package sup.mario.game;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/*
 * Create all Enemies behavior and property
 * 
 */
public class allEnemy implements Runnable{
	private BackGround bg = null;			//Enemy in which background
	private Thread t;
	private int flowerOnBotSleepTime = 0; 	// flower sleep time if it reaches the highest or lowest in the obstruction
	
	public int originX;						//original x-coordinate
	public int originY;						//original y-coordinate
	public String originStatus;				//Enemy's original status
	public int x;							//x-coordinate
	public int y;							//y-coordinate
	public String status;
	public BufferedImage showImage = null;	//Image
	public String type;						//type of Enemy, for construct different enemy
	
	//constructor
	public allEnemy(int x, int y, String type,String status,BackGround bg)
	{
		this.originX = x;
		this.originY = y;
		this.x = originX;
		this.y = originY;
		this.originStatus = status;
		this.status = status;
		this.bg = bg;
		this.type = type;
		
		t = new Thread(this);
		if(type.equals("mushroom")){
			showImage = MediaLibrary.mushroomImage.get(0);
		}
		else if(type.equals("turtle"))
		{
			showImage = MediaLibrary.turtleImage.get(0);
		}
		else if(type.equals("flower"))
		{
			showImage = MediaLibrary.flowerImage.get(0);
		}	
		t.start();											//Thread starts
	}



	public void run() {
		
		
		/*
		 * These variable is used to make the enemy moves
		 *  with a few image instead of only one image moving. 
		 */
		int getMushroomNumber = 0;
		int getFlowerNumber = 0;
		int getLeftTurtleNumber = 0;
		int getRightTurtleNumber = 2;
		int sleepTime = 0;				//sleep time during the image changed. 
		while(true)
		{
			boolean canRight = true;							//whether can go right
			boolean canLeft = true;								//whether can go left
			boolean flowerOnTop = false;						//whether flower is on the top of the obstruction
			boolean flowerOnBot = false;						//whether flower is on the bottom of the obstruction
			boolean onLand = false;								//whether enemy is on the land of the obstruction
			
			
			/*
			 * for loop to judge 4 types of boolean value that mentioned before
			 * 
			 */		
			for(int i=0;i<bg.Obst.size();i++)
			{
				Obstruction ob = bg.Obst.get(i);
				if((ob.y+60>y && ob.y-60<y)&&ob.x==x+60||x>=840)
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
				}
				if(ob.y == y + 60 && ob.x+60>x && ob.x-60<x)
				{
					flowerOnTop = true;
				}
				if(ob.y == y && ob.x+60>x && ob.x-60<x)
				{
					flowerOnBot = true;
				}
				if(Math.abs(ob.x - x)>=55&&Math.abs(ob.x - x)<60&&(ob.y+60>y && ob.y-60<y))
				{
					if(status.equals("left-move"))
					{
						x += 3;
					}
					else if(status.equals("right-move"))
					{
						x -= 3;
					}
				}
			}
			/*
			 * enemy can fall down
			 */
			if((!onLand&&type.equals("mushroom"))||(!onLand&&type.equals("turtle")))
			{
				y += 4;
			}
			/*
			 * enemy move right or left
			 */
			if(this.status.equals("left-move")||this.status.equals("right-move"))
			{
				if(sleepTime == 0)
				{
					if(this.type.equals("mushroom") && bg.ifKill==false)
					{
						showImage = MediaLibrary.mushroomImage.get(getMushroomNumber);
						getMushroomNumber++;
						if(getMushroomNumber>1)
						{
							getMushroomNumber = 0;
						}
					}
					if(this.type.equals("turtle") && bg.ifKill==false && this.status.equals("left-move"))
					{
						showImage = MediaLibrary.turtleImage.get(getLeftTurtleNumber);
						getLeftTurtleNumber ++;
						if(getLeftTurtleNumber>1)
						{
							getLeftTurtleNumber = 0;
						}
					}
					else if(this.type.equals("turtle") && bg.ifKill==false && this.status.equals("right-move"))
					{
						showImage = MediaLibrary.turtleImage.get(getRightTurtleNumber);
						getRightTurtleNumber ++;
						if(getRightTurtleNumber>3)
						{
							getRightTurtleNumber = 2;
						}
					}
				}
				sleepTime ++;
				if(sleepTime>5)
				{
					sleepTime = 0;
				}
				if(this.status.equals("left-move"))
				{
					if(canLeft&&this.status.equals("left-move"))
					{
						x -= 2;
					}else 
					{
						this.status = "right-move";
					}
				}
				else if(this.status.equals("right-move"))
				{
					if(canRight&&this.status.equals("right-move"))
					{
						x += 2;
					}else
					{
						this.status = "left-move";
					}
				}
			}
			
			/*
			 * enemy moves up or down
			 */
			if(this.status.equals("up-move")||this.status.equals("down-move"))
			{	
				if(sleepTime == 0)
				{
					if(this.type.equals("flower"))
					{
						showImage = MediaLibrary.flowerImage.get(getFlowerNumber);
						getFlowerNumber++;
						if(getFlowerNumber>1)
						{
							getFlowerNumber = 0;
						}
					}
				}
				sleepTime ++;
				if(sleepTime>5)
				{
					sleepTime = 0;
				}
				if(flowerOnBot)
				{
					this.status = "up-move";
					flowerOnBotSleepTime ++;
				}
				else if(flowerOnTop)
				{
					this.status = "down-move";
					flowerOnBotSleepTime ++;
				} 
					
				if(flowerOnBotSleepTime==50||flowerOnBotSleepTime==0)
				{
					
					flowerOnBotSleepTime = 0;
					if(flowerOnBotSleepTime<50)
					{
						if(this.status.equals("down-move"))
						{
							y += 2;
						}
				
						if(this.status.equals("up-move"))
						{
							y -= 2;
						}
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
}
