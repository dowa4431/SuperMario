package wenjin.dongyao.tianfang.window;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import wenjin.dongyao.tianfang.media.MediaLibrary;
import wenjin.dongyao.tianfang.thread.GameWindowThread;

public class GameWindow{
	
	public static void main(String[] args){
		new Thread(new GameWindowThread()).start();
	}
}
