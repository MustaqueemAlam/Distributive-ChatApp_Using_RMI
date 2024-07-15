/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    private List<ChatClient> clients;

    protected ChatServiceImpl() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, String sender) throws RemoteException {
        System.out.println(sender + ": " + message); // Display message on server side

        // Send message to all registered clients
        for (ChatClient client : clients) {
            client.receiveMessage(message, sender);
        }
    }

    @Override
    public void registerClient(ChatClient client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public void unregisterClient(ChatClient client) throws RemoteException {
        clients.remove(client);
    }

    public static void main(String[] args) {
        try {
            // Create an instance of the remote object
            ChatService chatService = new ChatServiceImpl();

            // Create the RMI registry on default port 1099
            java.rmi.registry.LocateRegistry.createRegistry(1099);

            // Bind the remote object to the registry
            java.rmi.Naming.rebind("rmi://localhost/ChatService", chatService);

            System.out.println("Chat Server started.");
        } catch (Exception e) {
            System.err.println("Chat Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
