package com.edao.codes.util;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2014-2-18
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-2-18
 */

/**
 * @author liushuai
 *
 */
public class FileRemover {
	JFrame jframe = new JFrame("File Remover");
	JPanel jpanel = new JPanel();
	JTextField jtf = new JTextField();
	JButton jbtn1 = new JButton("...");
	JButton jbtn2 = new JButton("delete");
	JFileChooser jfc = new JFileChooser();
	JTextArea jta = new JTextArea();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FileRemover().launch();
	}
	
	public void launch() {
		int width = 400;
		int height = 300;
		double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int x = (int) ((screenWidth - 400) / 2);
		int y = (int) ((screenHeight - 300) / 2);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setBounds(x, y, width, height);
		jframe.setVisible(true);
		jpanel.setLayout(null);
		jtf.setBounds(40, 20, 230, 30);
		jbtn1.setBounds(280, 20, 80, 30);
		jbtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int state = jfc.showOpenDialog(jpanel);
				if (state == 1) {
					return;
				} else {
					File file = jfc.getSelectedFile();
					jtf.setText(file.getAbsolutePath());
				}
			}
		});
		jbtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = jtf.getText();
				File file = new File(fileName);
				jta.setText("");
				deleteDir(file);
			}
		});
		jbtn2.setBounds(280, 65, 80, 30);
		jta.setBounds(40, 100, 320, 150);
		jta.setEditable(false);
		jta.setAutoscrolls(true);
		jta.setVisible(true);
		jta.setLineWrap(true);
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setBounds(40, 100, 320, 150);
		jpanel.add(jtf);
		jpanel.add(jbtn1);
		jpanel.add(jbtn2);
		jpanel.add(jsp);
		jframe.add(jpanel);
	}
	
	public void deleteDir(File dir) {
		if (dir.exists()) {
			System.out.println(dir.getAbsolutePath());
			String old = jta.getText();
			jta.setText(old+dir.getAbsolutePath()+"\n");
			if (dir.isDirectory()) {
				File[] files = dir.listFiles();
				if (files.length > 0) {
					for (int i=0; i<files.length; i++) {
						deleteDir(files[i]);
					}
				}
				dir.delete();
			} else {
				dir.delete();
			}
		}
	}
}
