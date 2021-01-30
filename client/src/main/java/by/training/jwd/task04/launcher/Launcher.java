package by.training.jwd.task04.launcher;

import by.training.jwd.task04.client.Client;
import by.training.jwd.task04.controller.ClientController;
import by.training.jwd.task04.view.impl.ConsoleView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class Launcher {
    private static final Logger logger = LogManager.getLogger(Launcher.class);
    public static boolean isRunning = true;


    public static void main(String[] args) {
        Properties clientProperties = getClientProperties();
        String host = clientProperties.getProperty("host");
        int port = Integer.parseInt(clientProperties.getProperty("port"));

        Client client = null;

        try {
            client = new Client(host, port);
            ConsoleView consoleView = new ConsoleView();
            ClientController clientController = new ClientController(client, consoleView);
            BufferedReader reader = consoleView.getReader();

            String userCommand;

            consoleView.printGreeting();
            consoleView.printConnectionAttempt();

            client.connect();
            boolean isConnected = client.isConnected();
            consoleView.printConnectionStatus(isConnected);

            if (!isConnected) {
                logger.info("Unable to connect to the server");
                consoleView.printApplicationShutdown();
                return;
            }

            consoleView.printAvailableCommands();
            while (isRunning) {
                consoleView.printClientLabel();

                userCommand = reader.readLine();
                clientController.handleClientCommand(userCommand);

                if (!client.isConnected()) {
                    isRunning = false;
                    consoleView.printConnectionLost();
                }
            }
            consoleView.printApplicationShutdown();
            logger.info("Application shutdown");
        } catch (IOException exception) {
            logger.error("Application crash");
        } finally {
            if (client != null) {
                client.disconnect();
            }
        }
    }

    public static Properties getClientProperties() {
        Properties clientProperties = new Properties();

        try (InputStream clientResource =
                     Launcher.class.getClassLoader().getResourceAsStream("client.properties")) {
            clientProperties.load(clientResource);
            logger.info("Client properties was successfully loaded");
        } catch (IOException exception) {
            logger.error("Unable to load client properties");
        }
        return clientProperties;
    }
}