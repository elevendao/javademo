package com.edao.codes.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;


import java.awt.*;
import java.awt.event.*;

public class FrameDemo {


        static JDialog myFrame=new JDialog();
        public static void main(String[] args) {
                myFrame.setUndecorated(true);//����ʾ���ڱ߿�ͱ���
                myFrame.setSize(300, 100);
                myFrame.setLayout(null);
                final JLabel jLabel=new JLabel("X");//�������Ͻ����رհ�ť
                jLabel.setFont(new Font("����", 0, 14));
                myFrame.getContentPane().setBackground(new Color(255, 255, 255));
                JPanel p=((JPanel)myFrame.getContentPane());
                p.setBorder(new LineBorder(new java.awt.Color(10,110,10), 1, false));
                myFrame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width-305, 
                  Toolkit.getDefaultToolkit().getScreenSize().height-135, 300, 100);
                myFrame.getContentPane().add(jLabel);
                jLabel.setBounds(280, 0, 20, 20);
                myFrame.setVisible(true);
                myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               
               jLabel.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
     myFrame.dispose();
    }
    public void mouseEntered(MouseEvent e) {     
     super.mouseEntered(e);
     jLabel.setForeground(Color.red);
    }
    public void mouseExited(MouseEvent e) {
     super.mouseExited(e);
     jLabel.setForeground(Color.BLACK);
    }
                
               });
        }
}