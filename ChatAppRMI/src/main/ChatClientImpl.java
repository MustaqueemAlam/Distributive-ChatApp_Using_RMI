/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    protected ChatClientImpl() throws RemoteException {
        super();
    }

    @Override
    public void receiveMessage(String message, String sender) throws RemoteException {
        // Implement how the client handles receiving messages
        System.out.println(sender + ": " + message); // Display message on client side
        // Update UI to display received message
    }

    public static void main(String[] args) {
        try {
            // Get remote object reference from RMI registry
            ChatService chatService = (ChatService) java.rmi.Naming.lookup("rmi://localhost/ChatService");

            // Create an instance of the client implementation
            ChatClient client = new ChatClientImpl();

            // Register client with the server
            chatService.registerClient(client);

            // Example: Send message to the server
            chatService.sendMessage("Hello from client!", "Client");

            // Example: Unregister client from the server
            chatService.unregisterClient(client);
        } catch (Exception e) {
            System.err.println("Chat Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
