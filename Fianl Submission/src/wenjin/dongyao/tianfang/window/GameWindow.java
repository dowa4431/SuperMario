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

/*
 * modified version from my super mario from 1300 final project
 * we use java JFrame to create the window
 * in this project, the class name MediaLibrary, GameGraphics is referenced from my 1300 final project
 * The image is downloading from website. 
 * 
 */



public class GameWindow{
	
	public static void main(String[] args){
		new Thread(new GameWindowThread()).start();
	}
}
