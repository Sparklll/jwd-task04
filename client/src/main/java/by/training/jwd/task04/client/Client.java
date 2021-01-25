package by.training.jwd.task04.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Objects;

public class Client {
    private String host;
    private int port;
    private Socket clientSocket;
    private boolean isConnected = false;

    private OutputStream outputStream;
    private InputStream inputStream;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            clientSocket = new Socket(host, port);
            outputStream = clientSocket.getOutputStream();
            inputStream = clientSocket.getInputStream();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            clientSocket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public boolean isConnected() {
        try {
            outputStream.write(1);
            isConnected = true;
        } catch (Exception e) {
            isConnected = false;
        }
        return isConnected;
    }

    public boolean isClosed() {
        return clientSocket.isClosed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return port == client.port
                && Objects.equals(host, client.host)
                && Objects.equals(clientSocket, client.clientSocket)
                && Objects.equals(outputStream, client.outputStream)
                && Objects.equals(inputStream, client.inputStream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, clientSocket, outputStream, inputStream);
    }
}
