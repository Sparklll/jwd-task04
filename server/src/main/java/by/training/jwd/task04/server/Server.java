package by.training.jwd.task04.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;

public class Server {
    public static final Logger logger = LogManager.getLogger(Server.class);

    private int port;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void openServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Successfully opened server port : " + port);
        } catch (IOException exception) {
            logger.error("Unable to open server port");
        }
    }

    public void switchOff() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
                logger.info("Server was switched off");
            }
        } catch (IOException exception) {
            logger.error("Unable to switch off the server");
        }
    }

    private boolean isClosed() {
        return serverSocket.isClosed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return port == server.port
                && Objects.equals(serverSocket, server.serverSocket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, serverSocket);
    }
}