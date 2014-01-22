package com.edao.codes.codec.md5;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainPanel extends JFrame{
    File file;
    private String inString;
    private String compareString;
    private String outputString;
    
    static JTextField inputText = new JTextField();
    static JTextField outputText = new JTextField();
    static JTextField compareText = new JTextField();
    
    static JButton fileChoose = new JButton("���ļ�");
    static JButton compare = new JButton("�Ƚ�");
    static JButton empty = new JButton("���");
    static JButton freeback = new JButton("�����");
    
    // create main user interface
    MainPanel() {
        super("MD5У������V0.1");
        setSize(320, 160);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NewAction newAction = new NewAction();
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());

        JPanel labels = new JPanel();
        labels.setLayout(new GridLayout(3, 1));
        labels.add(new JLabel("  �ļ�ѡ��"));
        labels.add(new JLabel("��MD5ֵ��"));
        labels.add(new JLabel("���ο�ֵ��"));

        JPanel texts = new JPanel();
        GridLayout textLayout = new GridLayout(3, 1);
        textLayout.setVgap(5);
        texts.setLayout(textLayout);
        inputText.setEditable(false);
        outputText.setEditable(false);
        texts.add(inputText);
        texts.add(outputText);
        texts.add(compareText);

        JPanel buttons = new JPanel();
        /*
        FlowLayout buttonsLay = new FlowLayout();
        buttonsLay.setHgap(5);
        buttons.setLayout(buttonsLay);
        */
        fileChoose.addActionListener(newAction);
        compare.addActionListener(newAction);
        empty.addActionListener(newAction);
        freeback.addActionListener(newAction);
        buttons.add(fileChoose);
        buttons.add(compare);
        buttons.add(empty);
        buttons.add(freeback);

        jp.add(labels, BorderLayout.WEST);
        jp.add(texts, BorderLayout.CENTER);
        jp.add(buttons, BorderLayout.SOUTH);
        setContentPane(jp);
    }
    
    public static void main(String[] args) {
        MainPanel panel = new MainPanel();
        panel.setVisible(true);
    }
    
    // implements functions of buttons
    class NewAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton button = (JButton) e.getSource();
            
            if ( button == fileChoose) {                // �ļ�ѡ��
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(fileChoose);
                if ( JFileChooser.APPROVE_OPTION == returnVal) {
                    file = chooser.getSelectedFile();
                    inString = file.getName();
                    inputText.setText(inString);
                    MainAlgorithm md5 = new MainAlgorithm(file);
                    outputString = md5.compute();
                    outputText.setText(outputString);
                }
            } else if (button == compare) {             // MD5ֵ�Ƚ�
                if (outputText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "���ȼ���");
                } else {
                    // if md5 values are the same
                    if (outputText.getText().equals(compareText.getText())) {
                        JOptionPane.showMessageDialog(null, "MD5ֵ��ͬ");
                    } else {// md5 values are different
                        JOptionPane.showMessageDialog(null, "MD5ֵ��ͬ");
                    }
                }
            } else if (button == empty) {               // ���³�ʼ��
                inputText.setText(null);
                outputText.setText(null);
                compareText.setText(null);
                file = null;
                inString = null;
                outputString = null;
                compareString = null;
            } else {                                    // ��������
                try {
                    BareBonesBrowserLaunch.browse("http://blog.sina.com.cn/" +
                    		"s/blog_03f9d36e0100qjqp.html");
                } catch (IllegalArgumentException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (NoSuchMethodException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }
}

// open URL with a browser
class BareBonesBrowserLaunch {
    public static void openURL(String url) {
        try {
            browse(url);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,
             "Error attempting to launch web browser:\n" +
             e.getLocalizedMessage());
        }
    }

    static void browse(String url) throws ClassNotFoundException,
            IllegalAccessException, IllegalArgumentException,
            InterruptedException, InvocationTargetException, IOException,
            NoSuchMethodException {
        String osName = System.getProperty("os.name", "");
        if (osName.startsWith("Mac OS")) {
            Class fileMgr = Class.forName("com.apple.eio.FileManager");
            Method openURL = fileMgr.getDeclaredMethod("openURL",
                    new Class[] { String.class });
            openURL.invoke(null, new Object[] { url });
        } else if (osName.startsWith("Windows")) {
            Runtime.getRuntime().exec(
                    "rundll32 url.dll,FileProtocolHandler " + url);
        } else { // assume Unix or Linux
            String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
                    "mozilla", "netscape" };
            String browser = null;
            for (int count = 0; count < browsers.length && browser == null; count++)
                if (Runtime.getRuntime()
                        .exec(new String[] { "which", browsers[count] })
                        .waitFor() == 0)
                    browser = browsers[count];
            if (browser == null)
                throw new NoSuchMethodException("Could not find web browser");
            else
                Runtime.getRuntime().exec(new String[] { browser, url });
        }
    }

}

