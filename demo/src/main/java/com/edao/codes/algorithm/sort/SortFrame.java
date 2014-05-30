/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2014-4-4
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-4-4
 */
package com.edao.codes.algorithm.sort;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author liushuai
 *
 */
public class SortFrame extends JFrame {
	
	JButton btn1;
	JButton btn2;
	Line line;
	int[] vals;
	Bar[] bars;
	int barWidth = 10;
	int barHeightUnit = 10;
	int startX = 100;
	int startY = 280;
	int frameWidth = 400;
	int frameHeight = 300;
	
	void initBars(int[] vals) {
		bars = new Bar[vals.length];
		for (int i=0; i<vals.length; i++) {
			bars[i] = new Bar(startX + i*barWidth, startY - vals[i] * barHeightUnit, barWidth, vals[i] * barHeightUnit);
		}
	}
	
	public void sort(Bar[] bars) {
		for (int i=0; i<bars.length-1; i++) {
	        for (int j=i+1; j<bars.length; j++) {
	        	if (bars[j].getHeight() < bars[i].getHeight()) {
	        		swapHeight(bars[i], bars[j]);
	        		repaint();
	        	}
	        }
	      }
	}
	
	public void quickSort(Bar[] bars, int start, int end) {
		int i=start;
		int j=end;
		while (i<j) {
			// 右侧扫描，找出第一个比key小的值，交换。
			while(i<j && bars[i].getHeight()<=bars[j].getHeight()) {
				j--;
			}
			if (i<j) {
				swapHeight(bars[i], bars[j]);
				repaint();
			}
			// 左侧扫描，找出第一个比key大的值，交换。此时key为arr[j]
			while (i<j && bars[i].getHeight()<bars[j].getHeight()) {
				i++;
			}
			if (i<j) {
				swapHeight(bars[i], bars[j]);
				repaint();
			}
		}
		
		// 递归调用， 把Key前面的完成排序
		if (i-start > 1) {
			quickSort(bars, start, i-1);
		}
		// 递归调用，把Key后面的完成排序
		if (end-i > 1) {
			quickSort(bars, i+1, end);
		}
	}
	
	public SortFrame() {
		line = new Line(50, 50, 100, 100);
		int[] barHeights = {4, 8, 5, 9, 7, 6, 3, 2, 11, 10, 1, 15};
		initBars(barHeights);
		this.setLayout(null);
		btn1 = new JButton("Click");
		btn1.setBounds(280, 20, 80, 30);
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//sort(bars);
				quickSort(bars, 0, bars.length-1);
			}
		});
		btn2 = new JButton("reset");
		btn2.setBounds(280, 60, 80, 30);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vals = new int[]{4, 8, 5, 9, 7, 6, 3, 2, 11, 10, 1, 15};
				initBars(vals);
				repaint();
			}
		});
		this.add(btn1);
		this.add(btn2);
	}
	
	void swapHeight(Bar bar1, Bar bar2) {
		int height = bar1.getHeight();
		bar1.setHeight(bar2.getHeight());
		bar1.setY(startY - bar1.height);
		bar2.setHeight(height);
		bar2.setY(startY - bar2.height);
	}
	
	public void paintLine(Graphics g, Line line) {
		g.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
	}
	
	public void paintBar(Graphics g, Bar bar) {
		g.drawRect(bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight());
	}
	
	public void launch() {
		double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int x = (int) ((screenWidth - frameWidth) / 2);
		int y = (int) ((screenHeight - frameHeight) / 2);
		this.setBounds(x, y, frameWidth, frameHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.repaint();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SortFrame test = new SortFrame();
		test.launch();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i=0; i<this.bars.length; i++) {
			this.paintBar(g, bars[i]);
		}
	}
}

class Bar {
	int x, y;
	int width, height;
	
	public Bar() {
	}
	
	public Bar(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void copyBar(Bar bar) {
		this.x = bar.x;
		this.y = bar.y;
		this.height = bar.height;
		this.width = bar.width;
	}
	
}

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}

class Line {
	int x1, y1, x2, y2;
	
	public Line(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
}
