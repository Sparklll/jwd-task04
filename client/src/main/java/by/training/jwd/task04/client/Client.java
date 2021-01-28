package by.training.jwd.task04.client;

import by.training.jwd.task04.entity.interaction.Request;

import java.io.*;

import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;

public class Client {
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
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (clientSocket != null) {
                objectInputStream.close();
                objectOutputStream.close();
                clientSocket.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean isConnected() {
        boolean isConnected;
        try {
            objectOutputStream.writeObject(Request.getTestRequest());
            isConnected = true;
        } catch (Exception e) {
            isConnected = false;
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
