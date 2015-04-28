package wenjin.dongyao.tianfang.keylistener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import wenjin.dongyao.tianfang.object.Mario;
import wenjin.dongyao.tianfang.thread.MarioThread;

public class MarioKeyListener implements KeyListener{
	private static boolean keyboardsignal = false;
	
	private Mario mario;
	
	public MarioKeyListener(Mario mario){
		this.mario = mario;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==39){
			mario.setStatus("right-move");		
		}
		if(e.getKeyCode()==37){
			mario.setStatus("left-move");
		}
		if(e.getKeyCode()==38){
			if(!MarioThread.isIsonair()){
				keyboardsignal = true;
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==39){
			mario.setStatus("down-move");
		}
		if(e.getKeyCode()==37){
			mario.setStatus("down-move");
		}
	}

	public static boolean getKeyboardsignal() {
		return keyboardsignal;
	}

	public static void setKeyboardsignal(boolean keyboardsignal) {
		MarioKeyListener.keyboardsignal = keyboardsignal;
	}

}
