package com.edao.codes.gui;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class OutBox {
        private JFrame win;
        private JTextPane textArea;
        private JScrollPane scroll; // ��text���ݹ���Ҫ����
        private int x, y;

        private OutBox(Builder builder) {
                win = new JFrame();

                x = builder.x;
                y = builder.y;

                // ��û������λ�ò��� x �� y ��������Ĭ��λ��Ϊ��Ļ���½�
                if (x == 0 && y == 0) {
                        Toolkit tool = win.getToolkit();

                        Dimension size = tool.getScreenSize();
                        x = size.width - builder.width;
                        y = size.height - builder.height;
                }

                win.setTitle(builder.title);
                win.setBounds(x, y, builder.width, builder.height);

                textArea = new JTextPane();
                textArea.setEditable(false);
                textArea.setAutoscrolls(true);
                textArea.setText(builder.text);

                scroll = new JScrollPane(textArea);
                // ����������������ˮƽ�����ϴӲ���ʾ���������ڴ�ֱ����������Ҫ��ʾ������
                scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

                win.add(scroll);
                // ������ı䴰�ڴ�С
                win.setResizable(false);
                win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                win.setVisible(true);
                win.validate();

                // ��ʾ builder.delay ���Ӻ�رմ���
                try {
                        Thread.sleep(builder.delay);
                } catch (Exception e) {
                }
                // ��ʾʱ�䵽���˳�����
                // �������������ã���ʹ��
                // win.dispose();
                System.exit(0);
        }

        public static class Builder {
                private int width = 240; // ���ڿ�
                private int height = 160; // ���ڸ�
                private int x = 0; // ����x���
                private int y = 0; // ����y���
                private String title = "Tips"; // ���ڱ���
                private String text = ""; // ��������ʾ������
                private long delay = 5000L; // Ĭ����ʾ5���Ӻ�رմ���

                /**
                 * ���ô��ڴ�С
                 * 
                 * @param width
                 *            ���ڿ�ȣ�Ĭ��Ϊ<b>320</b>
                 * @param height
                 *            ���ڸ߶ȣ�Ĭ��Ϊ<b>240</b>
                 * @return ��ǰBuilderʵ��
                 */
                public Builder size(int width, int height) {
                        this.width = width;
                        this.height = height;
                        return this;
                }

                /**
                 * ���ô���λ�ã�Ĭ������Ļ���½�
                 * 
                 * @param x
                 *            ����Ļ�ϵ�<b>x</b>���
                 * @param y
                 *            ����Ļ�ϵ�<b>y</b>���
                 * @return ��ǰBuilderʵ��
                 */
                public Builder position(int x, int y) {
                        this.x = x;
                        this.y = y;
                        return this;
                }

                /**
                 * ���ô��ڱ���
                 * 
                 * @param title
                 *            ���ڱ��⣬Ĭ��Ϊ <b>Tip</b>
                 * @return ��ǰBuilderʵ��
                 */
                public Builder title(String title) {
                        this.title = title;
                        return this;
                }

                /**
                 * ���ô�����ʾ�ı�
                 * 
                 * @param text
                 *            Ҫ��ʾ���ı���Ĭ��Ϊ��
                 * @return ��ǰBuilderʵ��
                 */
                public Builder text(String text) {
                        this.text = text;
                        return this;
                }

                /**
                 * ���ô�����ʾʱ�䣬�ӳ�<b>delay</b>�����ر�
                 * 
                 * @param delay
                 *            �ӳ�ʱ�䣬Ĭ��Ϊ<b>5000 ����</b>
                 * @return ��ǰBuilderʵ��
                 */
                public Builder delay(long delay) {
                        this.delay = delay;
                        return this;
                }

                /**
                 * ����OutBoxʵ��
                 * 
                 * @return OutBoxʵ��
                 */
                public OutBox build() {
                        return new OutBox(this);
                }
        }
}