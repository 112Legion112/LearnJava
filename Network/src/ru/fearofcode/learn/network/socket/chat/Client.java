package ru.fearofcode.learn.network.socket.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by maks on 6/25/2017.
 */
public class Client implements Runnable{
    private Socket serverSocket;
    private Thread listenerServerSocket = new Thread(this);

    public void connectToServer(){
        try {
            serverSocket = new Socket(Settings.ipAddressServer, Settings.portServer);

            try (
                DataInputStream dataInputStream = new DataInputStream(serverSocket.getInputStream())
            ) {
                //Receiving a old messages.
                int sizeMessages = dataInputStream.readInt();
                for (int i = 0; i < sizeMessages; i ++) {
                    String oldMess = dataInputStream.readUTF();
                    System.out.println(oldMess);
                }

                //Listens server socket
                listenerServerSocket.start();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                while(true){
                    String newMessage = bufferedReader.readLine();
                    try(
                        DataOutputStream dataOutputStream = new DataOutputStream(serverSocket.getOutputStream());
                    ) {
                        dataOutputStream.writeUTF(newMessage);
                        dataOutputStream.flush();
                    }catch(Exception e){
                        System.out.println("I couldn't send my new message");
                        e.printStackTrace();
                    }
                }
            }

        }catch(Exception e){
            System.out.println("Error in client: "+ e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * This thread listens on server socket for a new message from another clients.
     */
    @Override
    public void run() {
        try(
            DataInputStream dataInputStream = new DataInputStream(serverSocket.getInputStream());
        ){
            String newMessage = dataInputStream.readUTF();
            System.out.println(newMessage);
        }catch(Exception e){
            System.out.println("I couldn't listens server socket: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer();
    }

}