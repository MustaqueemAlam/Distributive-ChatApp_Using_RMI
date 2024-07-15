/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Mustaqueem Alam
 */
public class ChatServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the remote object
            ChatService chatService = new ChatServiceImpl();

            // Create the RMI registry on default port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the remote object to the registry
            registry.rebind("ChatService", chatService);

            System.out.println("Chat Server started.");
        } catch (Exception e) {
            System.err.println("Chat Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}