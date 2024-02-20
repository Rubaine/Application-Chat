package org.rubaine.applichat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.rubaine.applichat.network.Client;

import java.io.IOException;
public class ChatController {

    @FXML
    private ListView<String> messageList;
    @FXML
    private TextField messageField;

    @FXML
    private Label serverInfoLabel;
    @FXML
    private Button disconnectButton; // Ajout du bouton Déconnexion


    public void updateServerInfo(String info) {
        serverInfoLabel.setText(info);
    }
    private Client chatClient;
    private String pseudo; // Ajouter un champ pour stocker le pseudo

    public void initializeClient(String pseudo) {
        this.pseudo = pseudo; // Stocker le pseudo

        try {
            chatClient = new Client("localhost", 8000, pseudo, this);
        } catch (IOException e) {
            displayMessage("Erreur de connexion: " + e.getMessage());
        }
    }

    @FXML
    protected void onSendButtonClick() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            String fullMessage = pseudo + ": " + message; // Préfixer le message avec le pseudo
            chatClient.sendMessage(fullMessage);
            messageField.clear();
        }
    }
    @FXML
    protected void onDisconnectButtonClick() {
        // Fermer la connexion avec le serveur
        if (chatClient != null) {
            chatClient.close();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/rubaine/applichat/views/connexion-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            Stage stage = new Stage();
            stage.setTitle("Connexion");
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) disconnectButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void displayMessage(String message) {
        if (message.startsWith("Serveur 1 - Connectés: ")) {
            updateServerInfo(message);
        } else {
            messageList.getItems().add(message);
        }
    }
}
