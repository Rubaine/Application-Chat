package org.rubaine.applichat.network;

import org.rubaine.applichat.controller.ChatController;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
public class ThreadClient extends  Thread{

    private final Socket socket;
    private final ChatController controller;

    ThreadClient(Socket socket, ChatController controller) {
        this.socket = socket;
        this.controller = controller;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()))) {
            while (!socket.isClosed()) {
                String message = in.readUTF();
                javafx.application.Platform.runLater(() -> controller.displayMessage(message));
            }
        } catch (IOException e) {
            javafx.application.Platform.runLater(() ->
                    controller.displayMessage("Erreur: " + e.getMessage()));
        } finally {
            javafx.application.Platform.runLater(() ->
                    controller.displayMessage("Client déconnecté: " + socket.getInetAddress()));
        }
    }
}
