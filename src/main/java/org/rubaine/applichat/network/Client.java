package org.rubaine.applichat.network;

import org.rubaine.applichat.controller.ChatController;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private final Socket client;
    private final DataOutputStream out;
    private boolean connected;
    private String pseudo;
    private final ChatController controller;

    public Client(String host, int port, String pseudo, ChatController controller) throws IOException {
        this.client = new Socket(host, port);
        this.out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
        this.connected = true;
        this.controller = controller;
        this.pseudo = pseudo;
        startListener();
    }

    public void startListener() {
        Thread listenerThread = new ThreadClient(client, controller);
        listenerThread.start();
    }

    public void sendMessage(String message) {
        if (connected) {
            try {
                out.writeUTF(message);
                out.flush();
            } catch (IOException e) {
                System.out.println("Impossible d'envoyer le message");
            }
        }
    }

    public void close() {
        connected = false;
        try {
            if (client != null) {
                client.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            controller.displayMessage("Erreur lors de la déconnection");
        }
    }
}
