package ru.fearofcode.learn.io.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by maks on 6/20/2017.
 */
public class CharactersRead {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        try(FileInputStream myFile = new FileInputStream(Settings.nameFile2);
            InputStreamReader inputStreamReader = new InputStreamReader(myFile, "UTF8");
            Reader reader = new BufferedReader (inputStreamReader);
        ){
            int ch; // hte code of one character
            while((ch = reader.read()) > -1){
                System.out.print(ch+" ");
                buffer.append((char)ch);
            }
            System.out.println("\n" + buffer);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
