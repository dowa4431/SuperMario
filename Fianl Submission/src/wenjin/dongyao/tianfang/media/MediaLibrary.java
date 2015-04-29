package wenjin.dongyao.tianfang.media;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
/*
 * 
 * 
 * Reference Code
 * 
 * 
 * 
//This class is about the music and pictures, all method should be static. 

package wenjin.zhang.Mario;

import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MediaLibrary {
	public static List<BufferedImage> marioImage = new ArrayList<BufferedImage>();		//Import all Mario Images
	public static BufferedImage startImage = null;										//Import start background image
	public static BufferedImage endImage = null;										//import end background image
	public static BufferedImage BgImage = null;											//import background image
	public static List<BufferedImage> flowerImage = new ArrayList<BufferedImage>();		//Create flower List, easy to manage	
	public static List<BufferedImage> mushroomImage = new ArrayList<BufferedImage>();	//Create mushroom List, easy to manage
	public static List<BufferedImage> turtleImage = new ArrayList<BufferedImage>();		//Create turtle List, easy to manage
	public static List<BufferedImage> Obst = new ArrayList<BufferedImage>();			//Create obstruction List, easy to manage
	public static String filePath = System.getProperty("user.dir") + "/bin/";			//Source position
		
	//method to initialize all image
	public static void init() throws IOException 
	{
		for(int i=1; i<=10; i++)
		{
			marioImage.add(ImageIO.read(new File(filePath + i + ".gif")));
		}
		marioImage.add(ImageIO.read(new File(filePath + "over.gif")));
		
		startImage = ImageIO.read(new File(filePath + "start.gif"));
		endImage = ImageIO.read(new File(filePath + "firststageend.gif"));
		BgImage = ImageIO.read(new File(filePath + "firststage.gif"));
		
		for(int i=1; i<=2; i++)
		{
			flowerImage.add(ImageIO.read(new File(filePath + "flower" + i + ".gif")));
		}
		
		for(int i=1; i<=3; i++)
		{
			mushroomImage.add(ImageIO.read(new File(filePath + "triangle" + i + ".gif")));
		}
		
		for(int i=1; i<=5; i++)
		{
			turtleImage.add(ImageIO.read(new File(filePath + "Turtle" + i + ".gif")));
		}
		
		for(int i=1; i<=2; i++)
		{
			Obst.add(ImageIO.read(new File(filePath + "land" + i + ".gif")));
		}
	
		for(int i=1; i<=4; i++)
		{
			Obst.add(ImageIO.read(new File(filePath + "pipe" + i + ".gif")));
		}
		
		for(int i=1; i<=4; i++)
		{
			Obst.add(ImageIO.read(new File(filePath + "stone" + i + ".gif")));
		}
		
		Obst.add(ImageIO.read(new File(filePath + "qmark.gif")));
		Obst.add(ImageIO.read(new File(filePath + "flag.gif")));
		Obst.add(ImageIO.read(new File(filePath + "power.JPG")));
	}
	
	
}
*/


public class MediaLibrary {
	// Import all Mario Images
	private static List<BufferedImage> marioImage = new ArrayList<BufferedImage>();
	// Import start background image
	private static BufferedImage startImage = null;
	// import end background image
	private static BufferedImage endImage = null;
	// import background image
	private static BufferedImage bgImage = null;
	// Create flower List, easy to manage
	private static List<BufferedImage> flowerImage = new ArrayList<BufferedImage>();
	// Create mushroom List, easy to manage
	private static List<BufferedImage> mushroomImage = new ArrayList<BufferedImage>();
	// Create turtle List, easy to manage
	private static List<BufferedImage> turtleImage = new ArrayList<BufferedImage>();
	// Create obstruction List, easy to manage
	private static List<BufferedImage> Obst = new ArrayList<BufferedImage>();
	// Source position
	private static String filePath = System.getProperty("user.dir") + "/media/";
	// method to initialize all image
	
	
	
	public static void init(){
		try{
			for (int i = 1; i <= 10; i++) {
				marioImage.add(ImageIO.read(new File(filePath + i + ".gif")));
			}
			marioImage.add(ImageIO.read(new File(filePath + "over.gif")));
	
			startImage = ImageIO.read(new File(filePath + "start.gif"));
			endImage = ImageIO.read(new File(filePath + "firststageend.gif"));
			bgImage = ImageIO.read(new File(filePath + "firststage.gif"));
	
			for (int i = 1; i <= 2; i++) {
				flowerImage.add(ImageIO.read(new File(filePath + "flower" + i
						+ ".gif")));
			}
	
			for (int i = 1; i <= 3; i++) {
				mushroomImage.add(ImageIO.read(new File(filePath + "triangle" + i
						+ ".gif")));
			}
	
			for (int i = 1; i <= 5; i++) {
				turtleImage.add(ImageIO.read(new File(filePath + "Turtle" + i
						+ ".gif")));
			}
	
			for (int i = 1; i <= 2; i++) {
				Obst.add(ImageIO.read(new File(filePath + "land" + i + ".gif")));
			}
	
			for (int i = 1; i <= 4; i++) {
				Obst.add(ImageIO.read(new File(filePath + "pipe" + i + ".gif")));
			}
	
			for (int i = 1; i <= 4; i++) {
				Obst.add(ImageIO.read(new File(filePath + "stone" + i + ".gif")));
			}
	
			Obst.add(ImageIO.read(new File(filePath + "qmark.gif")));
			Obst.add(ImageIO.read(new File(filePath + "flag.gif")));
			Obst.add(ImageIO.read(new File(filePath + "power.JPG")));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public static List<BufferedImage> getMarioImage() {
		return marioImage;
	}

	public static BufferedImage getStartImage() {
		return startImage;
	}

	public static BufferedImage getEndImage() {
		return endImage;
	}

	public static BufferedImage getBgImage() {
		return bgImage;
	}

	public static List<BufferedImage> getFlowerImage() {
		return flowerImage;
	}

	public static List<BufferedImage> getMushroomImage() {
		return mushroomImage;
	}

	public static List<BufferedImage> getTurtleImage() {
		return turtleImage;
	}

	public static List<BufferedImage> getObst() {
		return Obst;
	}

	public static String getFilePath() {
		return filePath;
	}

}
