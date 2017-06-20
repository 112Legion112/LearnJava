package ru.fearofcode.learn.io.file;

import java.io.FileOutputStream;
/**
 * Created by maks on 6/16/2017.
 */
public class FileOutput {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();

        try (FileOutputStream osFile = new FileOutputStream(Settings.nameFile)) {
            for (int i = 0; i < Settings.interactional; i++) {
                osFile.write((int) (Math.random() * 255));
            }
        } catch (Exception e) {
            System.out.println("Could not write to a file: " + e.toString());
        }

        long timeFinish = System.currentTimeMillis();
        System.out.println("Time of work "+ FileOutput.class + " " + (timeFinish - timeStart) / 1000 + " second");
    }
}
