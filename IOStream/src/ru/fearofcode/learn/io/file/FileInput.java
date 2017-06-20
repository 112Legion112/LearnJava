package ru.fearofcode.learn.io.file;

import java.io.FileInputStream;
/**
 * Created by maks on 6/16/2017.
 */
public class FileInput {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();

        try (FileInputStream isFile = new FileInputStream(Settings.nameFile);){
            for (int i = 0; i < Settings.interactional; i++){
                System.out.print(isFile.read());
            }
        }catch(Exception e){
            System.out.println("\nCould not read in file: " + e.toString());
        }

        long timeFinish = System.currentTimeMillis();
        System.out.println("\nTime of work " + FileInput.class + " " + (timeFinish - timeStart) / 1000 + " second");
    }
}
