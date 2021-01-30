package by.training.jwd.task04.server;

import by.training.jwd.task04.controller.ServerConnectionController;
import by.training.jwd.task04.entity.interaction.Request;
import by.training.jwd.task04.entity.interaction.Response;
import by.training.jwd.task04.entity.interaction.TransferType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private static final Logger logger = LogManager.getLogger(ServerConnection.class);

    private final Socket clientSocket;
    private boolean connectionEstablished;

    public ServerConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.connectionEstablished = true;
        logger.info("Connection established with [" + clientSocket.getRemoteSocketAddress() + "]");
    }

    public void closeConnection() {
        try {
            if (clientSocket != null) {
                clientSocket.close();
                logger.info("Connection with [" + clientSocket.getRemoteSocketAddress() + "] closed successfully");
            }
        } catch (IOException exception) {
            logger.error("Unable to close connection with [" + clientSocket.getRemoteSocketAddress() + "]");
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

                logger.info("Handle request from user [" + clientSocket.getRemoteSocketAddress() + "] " +
                        "[request type - " + requestTransferType + "] " +
                        "[parameters - " + clientRequest.getParameters().trim() + "]");

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
        } catch (IOException | ClassNotFoundException exception) {
            logger.error("Server connection with [" + clientSocket.getInetAddress().getHostAddress() + "] " + "has been crushed");
        } finally {
            closeConnection();
        }
    }
}