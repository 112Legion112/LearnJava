package ru.fearofcode.learn.network.socket.fain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by maks on 6/23/2017.
 */
public class StockQuoteServer {
    public static void main(String[] args) {
        Socket client;

        try(ServerSocket serverSocket = new ServerSocket(3001))
        {
            // Create a server socket

            System.out.println("Waiting for a quote request...");
            while (true)
            {
                // Wait for a request
                client = serverSocket.accept();
                try(    BufferedReader inbound = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        OutputStream outbound = client.getOutputStream();) {
                    // Get the streams

                    String symbol = inbound.readLine();

                    //Generate a random stock price
                    String price = (new Double(Math.random() * 100)).toString();

                    outbound.write(("\n The price of " + symbol + " is " + price + "\n").getBytes());

                    System.out.println("Request for " + symbol + " has been processed - the price of " + symbol +
                            " is " + price + "\n");
                    outbound.write("End\n".getBytes());

                }
                client.close();
            }
        }catch (IOException ioe) {
            System.out.println("Error in Server: " + ioe);
        }
    }
}