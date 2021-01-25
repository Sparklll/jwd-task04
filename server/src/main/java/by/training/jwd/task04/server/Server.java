package by.training.jwd.task04.server;

import java.net.ServerSocket;
import java.util.Objects;

public class Server {
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
