package ru.fearofcode.learn.network.socket.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by maks on 6/25/2017.
 */
public class Server {
    private final List<Socket> clients = new LinkedList<>();
    private final List<String> messages = new LinkedList<>();
    private ServerSocket serverSocket;


    public void start() {
        try {
            messages.add("It is first test message");
            serverSocket = new ServerSocket(Settings.portServer);


            while (true) {
                Socket client = serverSocket.accept();
                newConnect(client);
                clients.add(client);

                /**
                 * This Thread listens socket of client.
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try (
                            DataInputStream dataInputStream = new DataInputStream(client.getInputStream())
                        ) {
                            while(true){
                                String newMessage = dataInputStream.readUTF();
                                newMessage(client, newMessage);
                            }
                        } catch (Exception e) {
                            System.out.println("Error in client: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }).start();
            }


        } catch (Exception e) {
            System.out.println("Error in Server: " + e.getMessage());
            e.printStackTrace();
        }
    }



    /**
     * This method send all old messages to new client.
     */
    private void newConnect(Socket client){
        try(
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        ){
            int size = messages.size();
            dataOutputStream.writeInt(size);
            for (String mess : messages) {
                dataOutputStream.writeUTF(mess);
            }
            //dataOutputStream.flush();

        }catch(Exception e){
            System.out.println("I couldn't send all messages to new client");
        }
    }

    /**
     * This method  send a new message from the client to all of clients other than this client.
     * @param from - from new message;
     * @param newMessage - new message :)
     */
    private void newMessage(Socket from, String newMessage){
        newMessage = from.getPort() + ": " + newMessage;
        for (Socket client : clients) {
            if (client != from){
                try(
                    DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
                ){
                    dataOutputStream.writeUTF(newMessage);
                    dataOutputStream.flush();
                }catch(Exception e){
                    System.out.println("I couldn't send new message this Client: " +client);
                    e.printStackTrace();
                }
            }
        }
        messages.add(newMessage);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
