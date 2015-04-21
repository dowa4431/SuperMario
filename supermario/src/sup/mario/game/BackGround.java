package sup.mario.game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BackGround{
	
	public BufferedImage bgImage  = null;							//background image 
	public int sort;												//judge which background are currently at
	public boolean flag;											//judge if it is the last background
	public List<allEnemy> Enemy = new ArrayList<allEnemy>();		//List which saves all the Enemy object
	public List<Obstruction> Obst = new ArrayList<Obstruction>();	//List which saves all the Obstruction object
	public boolean ifKill = false;									//judge enemy whether killed by Mario
	
	private List<Obstruction> resetObst = new ArrayList<Obstruction>();		//List which saves all the removed Obstruction object
	private List<allEnemy> resetEnemy = new ArrayList<allEnemy>();			//List which saves all the removed Enemy object
	
	
	//Background constructor
	public BackGround(int sort, boolean flag)
	{
		this.sort = sort;										//set up the background number
		this.flag = flag;										//last background or not
		
		
		if(flag)
		{
			bgImage = MediaLibrary.endImage;					//if it is the last background, use the endImage. 
		}else{
			bgImage = MediaLibrary.BgImage;						// if not, use the BgImage
		}
		
		
		/*
		 * at these 10 if statements, they will design
		 * the 10 different scenes of the game
		 */
		
		if(sort==1)												//add all enemies and obstructions to the background. Depends on the background number
		{
			for(int i=0; i<16; i++)								//add ground
			{
				this.Obst.add(new Obstruction(i*60,540,1));
			}
			
			this.Obst.add(new Obstruction(120,360,10));			//add other obstructions
			this.Obst.add(new Obstruction(300,360,6));
			this.Obst.add(new Obstruction(360,360,10));
			this.Obst.add(new Obstruction(420,360,6));
			this.Obst.add(new Obstruction(480,360,10));
			this.Obst.add(new Obstruction(540,360,6));
			this.Obst.add(new Obstruction(420,180,10));
																//draw the pipe
			
			this.Obst.add(new Obstruction(660,540,4));
			this.Obst.add(new Obstruction(720,540,5));
			this.Obst.add(new Obstruction(660,480,2));
			this.Obst.add(new Obstruction(720,480,3));
			resetObst.addAll(Obst);
			
			this.Enemy.add(new allEnemy(600,480,"mushroom","left-move",this));		// add enemies
			this.Enemy.add(new allEnemy(690,460,"flower","up-move",this));
			resetEnemy.addAll(Enemy);		
		}
		
		if(sort==2)
		{
			Obst.removeAll(Obst);
			resetEnemy.removeAll(resetEnemy);
			for(int i=0; i<16; i++)
			{
				if(i!=7&&i!=8&&i!=9)
				this.Obst.add(new Obstruction(i*60,540,1));
			}
			this.Obst.add(new Obstruction(540,480,7));
			this.Obst.add(new Obstruction(600,480,7));
			this.Obst.add(new Obstruction(360,480,7));
			this.Obst.add(new Obstruction(360,420,7));
			this.Obst.add(new Obstruction(360,360,7));
			this.Obst.add(new Obstruction(300,480,7));
			this.Obst.add(new Obstruction(300,420,7));
			this.Obst.add(new Obstruction(240,480,7));
			this.Obst.add(new Obstruction(300,140,7));
			for(int i=5; i<15; i++)
			{
				if(i%2==0)
					this.Obst.add(new Obstruction(i*60,200,6));
				else 
					this.Obst.add(new Obstruction(i*60,200,10));
			}
			resetObst.addAll(Obst);
			
			this.Enemy.add(new allEnemy(600,0,"mushroom","left-move",this));
			this.Enemy.add(new allEnemy(500,0,"mushroom","left-move",this));
			this.Enemy.add(new allEnemy(400,0,"mushroom","left-move",this));
			this.Enemy.add(new allEnemy(300,0,"mushroom","left-move",this));
			this.Enemy.add(new allEnemy(200,0,"mushroom","left-move",this));
			this.Enemy.add(new allEnemy(840,480,"turtle","left-move",this));
			resetEnemy.addAll(Enemy);
		}
		
		if(sort==3)
		{
			Obst.removeAll(Obst);
			resetEnemy.removeAll(resetEnemy);
			for(int i=0; i<16; i++)
			{
				this.Obst.add(new Obstruction(i*60,540,1));
			}
			for(int j=0; j<4; j++)
			{
				this.Obst.add(new Obstruction(660,540-j*60,4));
				this.Obst.add(new Obstruction(720,540-j*60,5));
			}
			this.Obst.add(new Obstruction(660,360,2));
			this.Obst.add(new Obstruction(720,360,3));
			for(int i=4; i<12; i++)
			{
				if(i%2==0)
					this.Obst.add(new Obstruction(i*60,50,10));
			}
			for(int i=4; i<11; i++)
			{
				this.Obst.add(new Obstruction(i*60,200,7));
			}
			resetObst.addAll(Obst);
			
			this.Enemy.add(new allEnemy(690,360,"flower","up-move",this));
			this.Enemy.add(new allEnemy(540,480,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(350,480,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(200,480,"turtle","left-move",this));
			resetEnemy.addAll(Enemy);
		}
		
		if(sort==4)
		{
			Obst.removeAll(Obst);
			resetEnemy.removeAll(resetEnemy);
			for(int i=0; i<16; i++)
			{
				if(i<=1||i>=12)
					this.Obst.add(new Obstruction(i*60,540,1));
			}
			this.Obst.add(new Obstruction(140,360,7));
			this.Obst.add(new Obstruction(200,360,7));
			this.Obst.add(new Obstruction(260,360,7));
			this.Obst.add(new Obstruction(200,300,2));
			this.Obst.add(new Obstruction(260,300,3));
			this.Obst.add(new Obstruction(320,240,7));
			this.Obst.add(new Obstruction(460,60,10));
			resetObst.addAll(Obst);
			
			this.Enemy.add(new allEnemy(230,300,"flower","up-move",this));
			resetEnemy.addAll(Enemy);
		}
		
		if(sort==5)
		{	
			Obst.removeAll(Obst);
			resetEnemy.removeAll(resetEnemy);
			for(int i=0; i<16; i++)
			{
				this.Obst.add(new Obstruction(i*60,540,1));
			}
			for(int i=9; i<16; i++)
			{
				if(i%2!=0)this.Obst.add(new Obstruction(i*60+30,360,10));	
			}
			this.Obst.add(new Obstruction(140,480,7));
			this.Obst.add(new Obstruction(200,420,7));
			this.Obst.add(new Obstruction(260,360,7));
			this.Obst.add(new Obstruction(320,300,7));
			this.Obst.add(new Obstruction(380,240,7));
			this.Obst.add(new Obstruction(440,180,7));
			this.Obst.add(new Obstruction(500,180,7));
			this.Obst.add(new Obstruction(560,180,7));
			this.Obst.add(new Obstruction(620,180,7));
			this.Obst.add(new Obstruction(680,180,7));
			this.Obst.add(new Obstruction(740,180,7));
			this.Obst.add(new Obstruction(800,180,7));
			this.Obst.add(new Obstruction(860,180,7));
			this.Obst.add(new Obstruction(430,420,2));
			this.Obst.add(new Obstruction(490,420,3));
			this.Obst.add(new Obstruction(430,480,4));
			this.Obst.add(new Obstruction(490,480,5));
			resetObst.addAll(Obst);
			this.Enemy.add(new allEnemy(850,120,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(750,120,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(650,120,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(550,120,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(450,120,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(460,420,"flower","up-move",this));
			resetEnemy.addAll(Enemy);	
		}
		
		if(sort==6)
		{
			Obst.removeAll(Obst);
			resetEnemy.removeAll(resetEnemy);
			for(int i=0; i<16; i++)
			{
				this.Obst.add(new Obstruction(i*60,540,1));
			}
			this.Obst.add(new Obstruction(140,480,7));
			this.Obst.add(new Obstruction(200,420,7));
			this.Obst.add(new Obstruction(260,360,7));
			this.Obst.add(new Obstruction(320,300,7));
			this.Obst.add(new Obstruction(560,300,7));
			this.Obst.add(new Obstruction(500,240,7));
			this.Obst.add(new Obstruction(620,360,7));
			this.Obst.add(new Obstruction(740,480,7));
			resetObst.addAll(Obst);
			this.Enemy.add(new allEnemy(640,480,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(450,480,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(300,480,"turtle","left-move",this));
			resetEnemy.addAll(Enemy);
			
		}
		
		if(sort==7)
		{
			Obst.removeAll(Obst);
			resetEnemy.removeAll(resetEnemy);
			for(int i=0; i<16; i++)
			{
				this.Obst.add(new Obstruction(i*60,540,1));
			}
			
			for(int i=0; i<12; i++)
			{	
				this.Obst.add(new Obstruction(i*60,360,7));		
			}
			for(int i=4; i<15; i++)
			{
				this.Obst.add(new Obstruction(i*60,180,7));	
			}
			for(int i=4; i<9; i++)
			{	
				this.Obst.add(new Obstruction(840,60*i,1));		
			}
			resetObst.addAll(Obst);
			resetEnemy.addAll(Enemy);
		}
		
		if(sort==8)
		{
			Obst.removeAll(Obst);
			resetEnemy.removeAll(resetEnemy);
			for(int i=0; i<16; i++)
			{
				this.Obst.add(new Obstruction(i*60,540,1));
			}
			
			for(int i=4; i<9; i++)
			{	
				this.Obst.add(new Obstruction(840,60*i,1));		
			}
			
			for(int i=0; i<3; i++)
			{	
				this.Obst.add(new Obstruction(i*60,400,7));		
			}
					
			for(int i=0; i<3; i++)
			{	
				this.Obst.add(new Obstruction(i*60 + 240,100,7));		
			}
			
			this.Obst.add(new Obstruction(140,240,7));		
			this.Obst.add(new Obstruction(450,300,7));
			this.Obst.add(new Obstruction(550,220,7));
			this.Obst.add(new Obstruction(650,180,7));
			this.Obst.add(new Obstruction(20,100,10));
			this.Obst.add(new Obstruction(120,50,10));
			this.Obst.add(new Obstruction(550,50,10));
			resetObst.addAll(Obst);
			
			for(int i=0;i<8;i++)
			{
				if(i%2!=0)this.Enemy.add(new allEnemy(i*60+360,520,"flower","up-move",this));
				else this.Enemy.add(new allEnemy(i*60+360,480,"flower","up-move",this));
			}
			this.Enemy.add(new allEnemy(300,0,"turtle","left-move",this));
			
			resetEnemy.addAll(Enemy);
		}
		
		if(sort==9)
		{
			this.Obst.add(new Obstruction(15*60,540,1));
			for(int i=0; i<16; i++)
			{
				if(i%2==0)
				this.Obst.add(new Obstruction(i*60,540,1));
			}
			resetObst.addAll(Obst);
			resetEnemy.addAll(Enemy);
			
		}
		
		if(sort==10)
		{
			Obst.removeAll(Obst);
			resetEnemy.removeAll(resetEnemy);
			for(int i=0; i<15; i++)
			{
				this.Obst.add(new Obstruction(i*60,540,1));
			}
			this.Obst.add(new Obstruction(556,190,11));
			resetObst.addAll(Obst);
		}
	}

	//reset all the obstruction when mario dies
	public void resetObstruction(int sort)
	{
		if(this.Obst.get(sort).type==10)
		{
			this.Obst.set(sort, new Obstruction(this.Obst.get(sort).x, this.Obst.get(sort).y, 8));
		}
		else if(this.Obst.get(sort).type==6)
		{
			this.Obst.remove(sort);
		}
	}
	
	//when the mario moves to the trap
	public void meetTrap(int sort)
	{
		if(this.Obst.get(sort).type==8 && this.sort==4)
		{
			this.Enemy.add(new allEnemy(this.Obst.get(sort).x,this.Obst.get(sort).y ,"turtle","left-move",this));
			this.Enemy.add(new allEnemy(900,0,"mushroom","left-move",this));
			this.Enemy.add(new allEnemy(800,0,"mushroom","left-move",this));
			this.Enemy.add(new allEnemy(700,0,"mushroom","left-move",this));
			this.Enemy.add(new allEnemy(600,0,"mushroom","left-move",this));
			
			for(int i=7; i<15; i++)
			{
				this.Obst.add(new Obstruction(i*60,360,1));
			}
		}
		this.Obst.remove(sort);
	}
	
	//kill Enemy method
	public void killEnemy(int sort)//change the image when the Mario jumps on it. 
	{	
		if(this.Enemy.get(sort).type.equals("flower")==false)
		{
			if(this.Enemy.get(sort).type.equals("mushroom"))
			{
				this.Enemy.get(sort).showImage = MediaLibrary.mushroomImage.get(2);
				ifKill = true;
			}
			if(this.Enemy.get(sort).type.equals("turtle"))
			{
				this.Enemy.get(sort).showImage = MediaLibrary.turtleImage.get(4);
				ifKill = true;
			}
		}
	}
	//remove the enemy element from the Enemy List. 
	public void removeElement(int sort)//remove image after changing the image
	{
		if(this.Enemy.get(sort).type.equals("flower")==false)
		{
			this.Enemy.remove(sort);
		}
		ifKill = false;
	}
	
	//when Mario dies, all obstructions and enemies should be reset
	public void allReset()//reset all enemies and obstruction. 
	{
		Obst.removeAll(Obst);
		Obst.addAll(resetObst);
		Enemy.removeAll(Enemy);
		Iterator<allEnemy> iter = resetEnemy.iterator();
		while(iter.hasNext())
		{
			allEnemy ae = iter.next();
			Enemy.add(new allEnemy(ae.originX, ae.originY, ae.type, ae.originStatus,this));
		}
		
	}
	
}
