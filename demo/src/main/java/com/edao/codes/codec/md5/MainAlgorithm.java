package com.edao.codes.codec.md5;


import java.io.*;
import java.security.*;

import javax.swing.JOptionPane;

public class MainAlgorithm {
    MessageDigest md5;
    File file;
    
    MainAlgorithm( File inFile) {
        try {
            this.file = inFile;
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    String compute () {
        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Couldn't find the file.");
            e.printStackTrace();
        }
        
        byte[] input = new byte[1024];
        int hasRead = 0; 
        
        try {
            hasRead = fis.read(input);
            while ( hasRead > 0) {
                md5.update(input, 0, hasRead);
                hasRead = fis.read(input);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] result = md5.digest();
        StringBuffer valueHex = new StringBuffer();
        for ( int i = 0, tmp = 0; i < result.length; i++) {
            tmp = result[i] & 0xff;
            if (tmp < 16) {
                valueHex.append(0);
            }
            valueHex.append(Integer.toHexString(tmp));
        }
        return valueHex.toString();
    }
}
