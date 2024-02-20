package org.rubaine.applichat.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final List<Socket> listeClients = new ArrayList<>();

    public static void sendInfos(){
        String info = "Serveur 1 - Connectés: "+listeClients.size();
        synchronized(listeClients){
            for(Socket clientSocket: listeClients){
                if(!clientSocket.isClosed()){
                    try{
                        DataOutputStream clientOut = new DataOutputStream(clientSocket.getOutputStream());
                        clientOut.writeUTF(info);
                        clientOut.flush();
                    }catch(IOException e){
                        System.out.println("Erreur lors de l'envoi des informations au client: "+e.getMessage());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try (ServerSocket serveur = new ServerSocket(8000)) {
            System.out.println("Serveur créé\nEn attente de connexion...");

            while (true) {
                Socket client = serveur.accept();
                System.out.println("Client connecté: " + client.getInetAddress());

                synchronized (listeClients) {
                    listeClients.add(client);
                    sendInfos();
                }

                ThreadServer t = new ThreadServer(client);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite: " + e.getMessage());
        }
    }
}
