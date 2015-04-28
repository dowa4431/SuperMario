package wenjin.tianfang.dongyao.test;

import java.io.File;

import wenjin.dongyao.tianfang.media.MediaLibrary;
import wenjin.dongyao.tianfang.window.GameWindow;
import wenjin.dongyao.wang.datainit.Datainit;

public class TestCode {

	public static void main(String[] args) {
		
		System.out.println(Datainit.getSpeedValue().getMushroom_and_turtle_h_speed());
		System.out.println(Datainit.getSpeedValue().getMushroom_and_turtle_d_speed());
		System.out.println(Datainit.getSpeedValue().getFlower_speed());
		System.out.println(Datainit.getSpeedValue().getMario_speed());
		//new GameWindow();
	}

}
