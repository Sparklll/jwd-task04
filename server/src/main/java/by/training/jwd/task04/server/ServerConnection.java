package by.training.jwd.task04.server;

import by.training.jwd.task04.controller.ServerConnectionController;
import by.training.jwd.task04.entity.interaction.Request;
import by.training.jwd.task04.entity.interaction.Response;
import by.training.jwd.task04.entity.interaction.TransferType;

import java.io.*;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private final Socket clientSocket;
    private boolean connectionEstablished;

    public ServerConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.connectionEstablished = true;
    }

    public void closeConnection() {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public boolean isConnectionEstablished() {
        return connectionEstablished;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {

            ServerConnectionController connectionController = new ServerConnectionController(this);

            while (connectionEstablished) {
                Request clientRequest = (Request) objectInputStream.readObject();
                TransferType requestTransferType = clientRequest.getTransferType();

                if (requestTransferType == TransferType.DATA) {
                    Response serverResponse = connectionController.processClientRequest(clientRequest);
                    objectOutputStream.writeObject(serverResponse);
                } else if (requestTransferType == TransferType.TEST) {
                    String testRequestParameters = clientRequest.getParameters().toLowerCase().trim();
                    switch (testRequestParameters) {
                        case "disconnect" -> connectionEstablished = false;
                        default -> {
                        }
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}