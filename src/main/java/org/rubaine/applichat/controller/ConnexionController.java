package org.rubaine.applichat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class ConnexionController {
    @FXML
    private TextField pseudoField;

    @FXML
    protected void onConnectButtonClick() throws IOException {
        String pseudo = pseudoField.getText().trim();
        if (!pseudo.isEmpty()) {
            openChatWindow(pseudo);
        }
    }

    private void openChatWindow(String pseudo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/rubaine/applichat/views/chat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        org.rubaine.applichat.controller.ChatController chatController = fxmlLoader.getController();
        chatController.initializeClient(pseudo);

        Stage stage = (Stage) pseudoField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
