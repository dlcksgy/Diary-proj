package com.apress.gerber.diary_proj;

import android.content.Context;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by Arduino on 2017-06-01.
 */

public class Diary {

    public static void inputDiary(String fileName, String input) {
        File file = new File(fileName);
        FileWriter fw = null;

        try {
            // open file.
            fw = new FileWriter(file);

            // write file.
            fw.write(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // close file.
        if (fw != null) {
            // catch Exception here or throw.
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String loadDiary(String fileName){
        File file = new File(fileName) ;
        FileReader fr = null ;
        char[] cbuf = new char[512] ;
        int size = 0 ;
        String ret = "";
        try {
            // open file.
            fr = new FileReader(file) ;

            // read file.
            while ((size = fr.read(cbuf)) != -1) {
                // TODO : use data
                for (int i=0; i<size; i++) {
                    ret += cbuf[i];
                }
            }

            fr.close() ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
        return ret;
    }

}
