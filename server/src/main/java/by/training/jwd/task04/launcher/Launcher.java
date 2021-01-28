package by.training.jwd.task04.launcher;

import by.training.jwd.task04.server.Server;
import by.training.jwd.task04.server.ServerConnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Launcher {
    public static boolean isRunning = true;

    public static void main(String[] args) {
        Properties serverProperties = getServerProperties();
        int port = Integer.parseInt(serverProperties.getProperty("port"));
        Server server = null;

        try {
            server = new Server(port);
            server.openServerSocket();
            ServerSocket serverSocket = server.getServerSocket();

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                ServerConnection serverConnection = new ServerConnection(clientSocket);
                new Thread(serverConnection).start();
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (server != null) {
                server.switchOff();
            }
        }
    }

    public static Properties getServerProperties() {
        Properties serverProperties = new Properties();

        try (InputStream serverResource =
                     Launcher.class.getClassLoader().getResourceAsStream("server.properties")) {
            serverProperties.load(serverResource);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return serverProperties;
    }
}