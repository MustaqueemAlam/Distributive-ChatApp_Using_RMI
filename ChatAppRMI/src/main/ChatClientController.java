package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClientController implements Initializable {

    private static final Logger logger = Logger.getLogger(ChatClientController.class.getName());

    @FXML
    private ListView<String> messageList;
    @FXML
    private TextField messageField; // Correct import for TextField from javafx.scene.control

    private ChatService chatService;
    private ChatClient client;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Connect to RMI server
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            chatService = (ChatService) registry.lookup("ChatService");

            // Create an instance of ChatClientImpl for this client
            client = new ChatClientImpl();
            // Register this client with the server
            chatService.registerClient(client);

            logger.info("Connected to RMI server.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to connect to RMI server", e);
        }
    }

    @FXML
    private void sendMessage(ActionEvent event) {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            try {
                // Send message to server
                chatService.sendMessage(message, "Me");
                messageList.getItems().add("Me: " + message);

                // Clear the message field
                messageField.clear();
            } catch (RemoteException e) {
                logger.log(Level.SEVERE, "Error sending message", e);
            }
        }
    }

    // This method is used to set up the client instance
    public void setClient(ChatClient client) {
        this.client = client;
    }

    // Method to connect the client to the RMI server
    public void connectToServer() {
        try {
            // Connect to RMI server (already done in initialize method)
            // Register this client with the server (already done in initialize method)
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error connecting to RMI server", e);
        }
    }
}
