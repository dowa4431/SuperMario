package wenjin.dongyao.tianfang.thread;

import java.util.ArrayList;
import java.util.List;

import wenjin.dongyao.tianfang.graphics.GameGraphics;
import wenjin.dongyao.tianfang.keylistener.MarioKeyListener;
import wenjin.dongyao.tianfang.object.Enemies;
import wenjin.dongyao.tianfang.object.Mario;
import wenjin.dongyao.tianfang.object.Obstruction;
import wenjin.dongyao.wang.datainit.Datainit;

public class MarioThread implements Runnable {

	private Mario mario = null;
	private boolean cangoright;
	private boolean cangoleft;
	private boolean cangotop;
	private boolean cangodown;
	private boolean marioiskilled;
	private boolean enemiesiskilled;

	private static boolean isonair = false;

	public MarioThread(Mario mario) {
		this.mario = mario;
	}

	public static boolean isIsonair() {
		return isonair;
	}

	public static void setIsonair(boolean isonair) {
		MarioThread.isonair = isonair;
	}

	public void run() {
		int dovalue = 45;
		int jump_timer = 0;
		while (true) {
			cangoleft = true;
			cangotop = true;
			cangoright = true;
			cangodown = true;
			marioiskilled = false;
			enemiesiskilled = false;
			List<Obstruction> breakoblist = new ArrayList<Obstruction>();
			for (Obstruction ob : GameGraphics.getCurrentBackground()
					.getObstructionList()) {
				int d_x_value = this.mario.getX() - ob.getX();
				int d_y_value = this.mario.getY() - ob.getY();
				if ((d_x_value <= 47 && d_x_value >= 0 && Math.abs(d_y_value) < 60)
						|| this.mario.getX() == 0) {
					cangoleft = false;
				}
				if ((d_x_value >= -47 && d_x_value <= 0 && Math.abs(d_y_value) < 60)) {
					cangoright = false;
				}
				if (Math.abs(d_x_value) <= dovalue && d_y_value >= -60
						&& d_y_value <= 0) {
					cangodown = false;
				}
				if (Math.abs(d_x_value) <= dovalue && d_y_value <= 60
						&& d_y_value >= 0) {
					cangotop = false;
					breakoblist.add(ob);
				}
			}
			this.mario.breakbricks(breakoblist);
			if (MarioKeyListener.getKeyboardsignal()) {
				if (cangotop) {
					this.mario.jump();
					if (this.mario.getStatus().equals("left-move")) {
						if (cangoleft) {
							this.mario.moveleft();
						}
					} else if (this.mario.getStatus().equals("right-move")) {
						if (cangoright) {
							this.mario.moveright();
						}
					} else if (this.mario.getStatus().equals("stop")) {
						this.mario.setStatus("down-move");
					}
				}
				isonair = true;
				jump_timer++;
				if (jump_timer > 60 || !cangotop) {
					MarioKeyListener.setKeyboardsignal(false);
					jump_timer = 0;
				}
			} else {
				if (!cangodown) {
					isonair = false;
				}
				if (this.mario.getStatus().equals("right-move")) {
					if (cangoright) {
						this.mario.moveright();
					}
					if (cangodown) {
						this.mario.fallmove();
					}
				} else if (this.mario.getStatus().equals("left-move")) {
					if (cangoleft) {
						this.mario.moveleft();
					}
					if (cangodown) {
						this.mario.fallmove();
					}
				} else if (this.mario.getStatus().equals("down-move")) {
					isonair = true;
					if (!cangodown) {
						isonair = false;
					}
					if (cangodown) {
						this.mario.fallmove();
					}
				}
			}
			List<Enemies> killedList = new ArrayList<Enemies>();
			if (this.mario.getY() > 600) {
				marioiskilled = true;
				this.mario.rebornreset();
			} else {
				for (Enemies en : GameGraphics.getCurrentBackground()
						.getEnemiesList()) {
					int d_x_value = this.mario.getX() - en.getX();
					int d_y_value = this.mario.getY() - en.getY();
					if (Math.abs(d_x_value) <= 55 && Math.abs(d_y_value) <= 55) {
						if (this.mario.getY() >= en.getY()
								|| en.getType().equals("flower")) {
							marioiskilled = true;
							this.mario.rebornreset();
						} else {
							enemiesiskilled = true;
							killedList.add(en);
						}
					}

				}
			}
			this.mario.kill(killedList);

			// System.out.println("enemiesiskilled   " + enemiesiskilled);
			// System.out.println("marioiskilled     " + marioiskilled);
			// System.out.println("---------------------------");
			// System.out.println();
			try {
				Thread.sleep(Datainit.getSpeedValue().getSleep_time());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
