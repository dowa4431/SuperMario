package wenjin.dongyao.tianfang.thread;

import wenjin.dongyao.tianfang.graphics.GameGraphics;
import wenjin.dongyao.tianfang.keylistener.MarioKeyListener;
import wenjin.dongyao.tianfang.object.Enemies;
import wenjin.dongyao.tianfang.object.Obstruction;
import wenjin.dongyao.wang.datainit.Datainit;

public class EnemiesThread implements Runnable {

	private Enemies enemies;
	private boolean cangotop;
	private boolean cangoright;
	private boolean cangoleft;
	private boolean cangodown;

	public EnemiesThread(Enemies enemies) {
		this.enemies = enemies;
	}

	public void run() {
		int flo_mov_recorder = 0;
		while (true) {
			
			cangoleft = true;
			cangotop = true;
			cangoright = true;
			cangodown = true;
			
			if (!this.enemies.getType().equals("flower")) {
				for (Obstruction ob : GameGraphics.getCurrentBackground().getObstructionList()) {
					int d_x_value = this.enemies.getX() - ob.getX();
					int d_y_value = this.enemies.getY() - ob.getY();
					if ((d_x_value <= 60 && d_x_value >= 0
							&& Math.abs(d_y_value) < 60 )|| this.enemies.getX()==0) {
						cangoleft = false;
						this.enemies.setStatus("right-move");
					}
					if ((d_x_value >= -60 && d_x_value <= 0
							&& Math.abs(d_y_value) < 60 )|| this.enemies.getX()==840) {
						cangoright = false;
						this.enemies.setStatus("left-move");
					}
					if (Math.abs(d_x_value) < 60 && d_y_value >= -60
							&& d_y_value <= 0) {
						cangodown = false;
					}
				}
				if (this.enemies.getStatus().equals("left-move")) {
					if (cangoleft) {
						this.enemies.moveleft();
					}
					if (cangodown) {
						this.enemies.enemiesfallmove();
					}
				}
				if (this.enemies.getStatus().equals("right-move")) {
					if (cangoright) {
						this.enemies.moveright();
					}
					if (cangodown) {
						this.enemies.enemiesfallmove();
					}
				}
				if (this.enemies.getStatus().equals("down-move")) {
					if (cangoright) {
						this.enemies.moveright();
					}
					if (cangodown) {
						this.enemies.enemiesfallmove();
					}
				}
			}
			if(this.enemies.getType().equals("flower")){
				if(flo_mov_recorder>=100){
					flo_mov_recorder = 0;
					if(this.enemies.getStatus().equals("up-move")){
						this.enemies.setStatus("down-move");
					}else{
						this.enemies.setStatus("up-move");
					}
				}
				
				if(this.enemies.getStatus().equals("down-move")){
					this.enemies.flowermovedown();
				}else{
					this.enemies.flowermoveup();
				}
				flo_mov_recorder++;
			}
			this.enemies.setImage(this.enemies.getStatus(), this.enemies.getType());
			try {
				if(this.enemies.getType().equals("flower")){
					Thread.sleep(30);
				}else{
					Thread.sleep(Datainit.getSpeedValue().getSleep_time());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
