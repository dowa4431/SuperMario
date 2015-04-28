package wenjin.dongyao.wang.datainit;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Node;

import wenjin.dongyao.tianfang.bean.Background;
import wenjin.dongyao.tianfang.bean.PropContainers;
import wenjin.dongyao.tianfang.bean.SpeedValue;
import wenjin.dongyao.tianfang.media.MediaLibrary;
import wenjin.dongyao.tianfang.object.Enemies;
import wenjin.dongyao.tianfang.object.Mario;
import wenjin.dongyao.tianfang.object.Obstruction;
import wenjin.dongyao.tianfang.utils.XmlUtils;

public class Datainit {
	
	private static Mario mario = null;
	private static SpeedValue sv = null;
	private static PropContainers pc = null;
	
	static{
		try{
			mario = new Mario(0,480);
			sv = new SpeedValue();
			pc = new PropContainers();
			InputStream in = Datainit.class.getClassLoader().getResourceAsStream("data.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			int mushroom_and_turtle_h_speed = Integer.parseInt(prop.getProperty("mushroom_and_turtle_h_speed"));
			int mushroom_and_turtle_d_speed = Integer.parseInt( prop.getProperty("mushroom_and_turtle_d_speed"));
			int flower_speed = Integer.parseInt( prop.getProperty("flower_speed"));
			int sleep_time = Integer.parseInt(prop.getProperty("sleep_time"));
			int mario_speed = Integer.parseInt(prop.getProperty("mario_speed"));
			int totalstagenum = Integer.parseInt(prop.getProperty("totalstagenum"));
			
			sv.setMushroom_and_turtle_d_speed(mushroom_and_turtle_d_speed);
			sv.setMushroom_and_turtle_h_speed(mushroom_and_turtle_h_speed);
			sv.setFlower_speed(flower_speed);
			sv.setSleep_time(sleep_time);
			sv.setMario_speed(mario_speed);
			pc.setTotalstagenum(totalstagenum);
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Background getBackground(int num){
		try {
			Background background = new Background();
			Document document =  XmlUtils.getDocument("background.xml");
			List<Node> list = document.selectNodes("//background//stage"+num+"//*");
			List<Obstruction> oblist = new ArrayList<Obstruction>();
			List<Enemies> enlist = new ArrayList<Enemies>();
			boolean endgame = false;
			for(Node node:list){
				String name = node.getName();
				if(name.equals("obstruction")){
					int x = Integer.parseInt(node.valueOf("@x"));
					int y = Integer.parseInt(node.valueOf("@y"));
					int type = Integer.parseInt(node.valueOf("@type"));
					Obstruction ob = new Obstruction(x,y,type);
					oblist.add(ob);
				}else if(name.equals("turtle") || name.equals("flower")|| name.equals("mushroom")){
					int x = Integer.parseInt(node.valueOf("@x"));
					int y = Integer.parseInt(node.valueOf("@y"));
					String status = node.valueOf("@status");
					String type = node.getName();
					Enemies en = new Enemies(x,y,status,type);
					enlist.add(en);		
				}else if(name.equals("endgame")){
					endgame = true;
				}
			}
			background.setObstructionList(oblist);
			background.setEnemiesList(enlist);
			background.setMario(mario);
			background.setStage(num);
			if(endgame){
				background.setBackgroundimg(MediaLibrary.getEndImage());
			}else{
				background.setBackgroundimg(MediaLibrary.getBgImage());
			}
			return background;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Background changeBackground(int num){
		return getBackground(num);
	}
	
	public static SpeedValue getSpeedValue(){
		return sv;
	}
	
	public static PropContainers getPropContainers(){
		return pc;
	}
}
