package by.training.jwd.task04.launcher;

import by.training.jwd.task04.client.Client;
import by.training.jwd.task04.controller.ClientController;
import by.training.jwd.task04.view.impl.ConsoleView;

import java.io.*;
import java.util.Properties;

public class Launcher {
    public static boolean isRunning = true;
    public static final InputStream DEFAULT_INPUT_STREAM = System.in;

    public static void main(String[] args) {
        Properties clientProperties = getClientProperties();
        String host = clientProperties.getProperty("host");
        int port = Integer.parseInt(clientProperties.getProperty("port"));

        Client client = null;

        try {
            client = new Client(host, port);
            ConsoleView consoleView = new ConsoleView();
            ClientController clientController = new ClientController(client, consoleView);
            BufferedReader reader = new BufferedReader(new InputStreamReader(DEFAULT_INPUT_STREAM));
            String userCommand;

            consoleView.printGreeting();
            consoleView.printConnectionAttempt();

            client.connect();
            boolean isConnected = client.isConnected();
            consoleView.printConnectionStatus(isConnected);

            if (!isConnected) {
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
                }
            }
            consoleView.printApplicationShutdown();

        } catch (IOException exception) {
            exception.printStackTrace();
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
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return clientProperties;
    }
}