package ru.fearofcode.learn.io.file;

import java.io.*;

/**
 * Created by maks on 6/20/2017.
 */
public class CharactersWrite {
    public static void main(String[] args) {

        try (FileOutputStream myFile = new FileOutputStream(Settings.nameFile2);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(myFile,"UTF8");
             Writer writer = new BufferedWriter(outputStreamWriter)
        ){
            writer.write("Hello world!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
