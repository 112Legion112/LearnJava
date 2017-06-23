package ru.fearofcode.learn.io;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by maks on 6/21/2017.
 */

public class MyURL{
    static private URL url;
    static private URLConnection urlConnection;

    public static void main(String[] args) {
        try {
            url = new URL("http://myflex.org/yf/podru/budam497.mp3");
            urlConnection = url.openConnection();
        } catch (IOException e) {
            System.out.println("I can't connect the URL: " + e.getMessage());
            e.printStackTrace();
        }
        try(InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            FileOutputStream fileOutputStream = new FileOutputStream("budam.mp3");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)){

            int read;
            while( ( read = bufferedInputStream.read()) !=  -1){
                bufferedOutputStream.write(read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
