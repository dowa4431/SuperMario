package sup.mario.test;

import java.util.List;

import sup.mario.game.GameWindow;
import sup.mario.game.Mario;

public class MarioTestMain {

	private static MarioTestFunction mtf = null;
	
	public static void main(String args[]){
		try {
			mtf = new MarioTestFunction();
			while(true){
				mtf.printMarioCoordinate();
				mtf.printReturn();
				mtf.setMarioOldCoordinate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
