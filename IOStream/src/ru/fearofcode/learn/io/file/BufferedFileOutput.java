package ru.fearofcode.learn.io.file;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
/**
 * Created by maks on 6/16/2017.
 */
public class BufferedFileOutput {

    public static void main(String[] args) {

        long timeStart = System.currentTimeMillis();



        try (FileOutputStream osFile = new FileOutputStream(Settings.nameFile);
            BufferedOutputStream buff = new BufferedOutputStream(osFile)){

            byte array[] = new byte[Settings.interactional];
            for (int i = 0; i < Settings.interactional; i++){
                array[i] = (byte)(Math.random() * 255);
            }
            buff.write(array);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
