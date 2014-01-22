package com.edao.codes.gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
public class PopupWindows2 {
	static JFrame frame;
	static int width=320;
	static int height=240;
	static int SWidth=Toolkit.getDefaultToolkit().getScreenSize().width;//�õ���Ļ�Ŀ��
	static int SHeight=Toolkit.getDefaultToolkit().getScreenSize().height;//�õ���Ļ�ĸ߶�
	static int xCoor=SWidth-width;//�������Ͻǵ�x���
	static int yCoor=SHeight-height;//���ڵ��������յ�y��� 
	static int yCoor0=SHeight; 
	static float value=1.0f; 
	static Timer Ti0; 
	public PopupWindows2(){ 
		frame=new JFrame(); 
		frame.setTitle("��������2"); 
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(xCoor,yCoor0);//���ڵĳ�ʼλ��
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		Ti0=new Timer(100,new Tim_00());
		Ti0.start(); //�رմ��ں󣬴��ڵ�����Ļ
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				Timer Ti1=new Timer(100,new Tim_01());
				Ti1.start();
				} });
		} 
	static class Tim_00 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(yCoor0>yCoor){
				yCoor0-=4;
				frame.setLocation(xCoor,yCoor0);
			} else{
				frame.setLocation(xCoor,yCoor);
				Ti0.stop();
			}
		}
	}
	static class Tim_01 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			value-=0.02f;
			if(value>=0.02f){
				SwingUtilities.invokeLater(new Runnable() { public void run() {com.sun.awt.AWTUtilities.setWindowOpacity(frame,value);} }); }else{
					System.exit(0);
					}
			}
		}
	public static void main(String[] args){
		new PopupWindows2();
	}
}