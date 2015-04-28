package sup.mario.test;

import java.util.List;

import sup.mario.game.Mario;
import sup.mario.game.allEnemy;

public class MarioTest {
	
	private Mario m;
	private List<allEnemy> enemylist;
	private int moldx = -1;
	private int moldy = -1;
	private int eoldx = -1;
	private int eoldy = -1;
	
	public int getEoldx() {
		return eoldx;
	}


	public void setEoldx(int eoldx) {
		this.eoldx = eoldx;
	}


	public int getEoldy() {
		return eoldy;
	}


	public void setEoldy(int eoldy) {
		this.eoldy = eoldy;
	}


	public MarioTest(Mario m, List list){
		this.m = m;
		this.enemylist = list;
	}


	public Mario getM() {
		return m;
	}


	public void setM(Mario m) {
		this.m = m;
	}


	public List<allEnemy> getEnemylist() {
		return enemylist;
	}


	public void setEnemylist(List enemylist) {
		this.enemylist = enemylist;
	}


	public int getMoldx() {
		return moldx;
	}


	public void setMoldx(int moldx) {
		this.moldx = moldx;
	}


	public int getMoldy() {
		return moldy;
	}


	public void setMoldy(int moldy) {
		this.moldy = moldy;
	}



	
	
	
	
}
