package org.rubaine.applichat.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChatApplication extends  Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplication.class.getResource("/org/rubaine/applichat/views/connexion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        scene.getStylesheets().add(Objects.requireNonNull(ChatApplication.class.getResource("/org/rubaine/applichat/util/connexion-style.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(ChatApplication.class.getResource("/org/rubaine/applichat/util/chat-style.css")).toExternalForm());


        stage.setTitle("Connexion au Chat");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
