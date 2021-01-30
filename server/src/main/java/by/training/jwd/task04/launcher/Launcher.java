package by.training.jwd.task04.launcher;

import by.training.jwd.task04.server.Server;
import by.training.jwd.task04.server.ServerConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Launcher {
    private static final Logger logger = LogManager.getLogger(Launcher.class);
    public static boolean isRunning = true;

    public static void main(String[] args) {
        Properties serverProperties = getServerProperties();
        int port = Integer.parseInt(serverProperties.getProperty("port"));
        Server server = null;

        try {
            server = new Server(port);
            server.openServerSocket();
            ServerSocket serverSocket = server.getServerSocket();
            logger.info("Server opened port");

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                ServerConnection serverConnection = new ServerConnection(clientSocket);
                new Thread(serverConnection).start();
                logger.info("New server connection");
            }

        } catch (IOException exception) {
            logger.error("Server crushes");
        } finally {
            if (server != null) {
                server.switchOff();
                logger.info("Server successfully closed port");
            }
        }
    }

    public static Properties getServerProperties() {
        Properties serverProperties = new Properties();

        try (InputStream serverResource =
                     Launcher.class.getClassLoader().getResourceAsStream("server.properties")) {
            serverProperties.load(serverResource);
            logger.info("Server properties was successfully loaded");
        } catch (IOException exception) {
            logger.error("Unable to load server properties");
        }
        return serverProperties;
    }
}