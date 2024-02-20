package org.rubaine.applichat.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadServer extends  Thread{

    private final Socket socket;

    public ThreadServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()))) {
            while (!socket.isClosed()) {
                String message = in.readUTF();

                synchronized (Server.listeClients) {
                    for (Socket clientSocket : Server.listeClients) {
                        if (!clientSocket.isClosed()) {
                            try {
                                DataOutputStream clientOut = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                                clientOut.writeUTF(message);
                                clientOut.flush();
                            } catch (IOException e) {
                                System.out.println("Erreur lors de l'envoi du message au client: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite: " + e.getMessage());
        } finally {
            synchronized (Server.listeClients) {
                Server.listeClients.remove(socket);
                System.out.println("Client déconnecté: " + socket.getInetAddress());
                Server.sendInfos();
            }
        }
    }
}
