package sup.mario.test;

import java.util.List;

import sup.mario.game.GameWindow;
import sup.mario.game.Mario;
import sup.mario.game.allEnemy;

public class MarioTestFunction {

	private MarioTest mt = null;
	private boolean callprint = false;
	
	public MarioTestFunction(){
		try {
			Class gameclazz;
			gameclazz = Class.forName("sup.mario.game.GameWindow");
			Object gameInstance = gameclazz.newInstance();
			GameWindow gw = (GameWindow)gameInstance;
			Mario m = gw.superMario;
			List list = gw.nowBackGround.Enemy;
			mt = new MarioTest(m,list);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printMarioCoordinate(){
		callprint = false;
		if(mt.getMoldx() != mt.getM().x || mt.getMoldy() != mt.getM().y){
			System.out.print("Mario x-coordinate : " + mt.getM().x);
			System.out.print("     ");
			System.out.print("Mario y-coordinate :" + mt.getM().y);
			callprint = true;
		}
	}
	
	public void printEnemiesCoordinate(){
		int i = 0;
		callprint = false;
		for(allEnemy ae: mt.getEnemylist()){
			if(mt.getEoldx() != ae.x || mt.getEoldy() != ae.y){
				System.out.print("Enemy "+i+" x-coordinate : " + ae.x);
				System.out.print("     ");
				System.out.print("Enemy "+i+" y-coordinate :" + ae.y);
				callprint = true;
			}
		}
	}
	
	public void printReturn(){
		if(callprint){
			System.out.println();
		}
	}
	
	public void printSpace(){
		if(callprint){
			System.out.print("    ");
		}
	}
	
	public void setMarioOldCoordinate(){
		mt.setMoldx(mt.getM().x);
		mt.setMoldy(mt.getM().y);
	}

	public MarioTest getMt() {
		return mt;
	}

	public void setMt(MarioTest mt) {
		this.mt = mt;
	}
}
