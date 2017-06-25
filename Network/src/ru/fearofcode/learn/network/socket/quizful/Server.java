package ru.fearofcode.learn.network.socket.quizful;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by maks on 6/24/2017.
 */
public class Server {
    public static void main(String[] args) {
        int port = 6666;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.print("Waiting for a client...");

            Socket socket = ss.accept();
            System.out.println("Got a client :)\n");

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;

            while (true){
                line = in.readUTF();
                System.out.println("The dumb client just me this line : " + line);
                System.out.println("I'm sending it back...");
                out.writeUTF(line);
                out.flush(); // We force a stream to finish data transmission - Заставляем поток закончить передачу данных
                System.out.println("Waiting for the next line...\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
