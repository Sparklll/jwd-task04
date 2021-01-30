package by.training.jwd.task04.client;

import by.training.jwd.task04.entity.interaction.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

import java.net.Socket;
import java.util.Objects;

public class Client {
    private static final Logger logger = LogManager.getLogger(Client.class);

    private String host;
    private int port;
    private Socket clientSocket;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            clientSocket = new Socket(host, port);
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            logger.info("Connection to the server successfully established. [Host] - " + host + " [Port] - " + port);
        } catch (IOException e) {
            logger.error("Unable to connect to the server");
        }
    }

    public void disconnect() {
        try {
            if (clientSocket != null) {
                objectInputStream.close();
                objectOutputStream.close();
                clientSocket.close();
                logger.info("Connection with server closed successfully");
            }
        } catch (IOException exception) {
            logger.error("Unable to disconnect from the server");
        }
    }

    public boolean isConnected() {
        boolean isConnected;
        try {
            objectOutputStream.writeObject(Request.getTestRequest());
            isConnected = true;
            logger.info("Connection status : connected");
        } catch (Exception e) {
            isConnected = false;
            logger.info("Connection status : not connected");
        }
        return isConnected;
    }

    public boolean isClosed() {
        return clientSocket.isClosed();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return port == client.port
                && Objects.equals(host, client.host)
                && Objects.equals(clientSocket, client.clientSocket)
                && Objects.equals(objectOutputStream, client.objectOutputStream)
                && Objects.equals(objectInputStream, client.objectInputStream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, clientSocket, objectOutputStream, objectInputStream);
    }
}
