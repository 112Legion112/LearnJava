package ru.fearofcode.learn.network.socket.quizful;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by maks on 6/24/2017.
 */
public class Client {
    public static void main(String[] args) {
        int serverPort = 6666;
        String address = "127.0.0.1"; // = localhost;

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("Any of you heard of a socket with IP address " + address + "" +
                    "\nand port " + serverPort + "?");

            Socket socket = new Socket(ipAddress, serverPort);
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String line = null;
            System.out.println("Type in something and press ENTER." +
                    " Will send it to the server and tell you what it thinks.\n");

            while (true) {
                line = keyboard.readLine();

                System.out.println("Sending this line to the server...");
                out.writeUTF(line);
                out.flush();
                line = in.readUTF();
                System.out.println("The server was very polite. It sent me this :\n" +
                        line);
                System.out.println("Looks like the server is pleased with us.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
