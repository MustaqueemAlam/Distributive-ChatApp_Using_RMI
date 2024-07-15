package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatClientApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatClient.fxml"));
        Parent root = loader.load();

        // Get the controller instance
        ChatClientController controller = loader.getController();

        // Set up RMI client (example)
        ChatClient client = new ChatClientImpl();
        controller.setClient(client); // Pass client to controller

        // Example: Connect to RMI server and register client
        controller.connectToServer();

        // Set up JavaFX scene
        Scene scene = new Scene(root);
        primaryStage.setTitle("Chat Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
