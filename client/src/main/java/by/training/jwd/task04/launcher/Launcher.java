package by.training.jwd.task04.launcher;

import by.training.jwd.task04.client.Client;
import by.training.jwd.task04.controller.ConsoleController;
import by.training.jwd.task04.view.impl.ConsoleView;

import java.io.*;
import java.util.Properties;

public class Launcher {
    public static void main(String[] args) {
        Properties clientProperties = getClientProperties();
        String host = clientProperties.getProperty("host");
        int port = Integer.parseInt(clientProperties.getProperty("port"));

        Client client = new Client(host, port);
        ConsoleView consoleView = new ConsoleView();
        ConsoleController consoleController = new ConsoleController(consoleView);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userCommand;

        try {
            consoleView.printGreeting();
            consoleView.printConnectionAttempt();
            client.connect();

            boolean isConnected = client.isConnected();
            consoleView.printConnectionStatus(isConnected);

            if(!isConnected) {
                consoleView.printApplicationShutdown();
                return;
            }


            while(true) {
                consoleView.printClientLabel();
                userCommand = reader.readLine();

                consoleController.handleClientCommand(userCommand);
            }



        } catch (IOException exception) {
            exception.printStackTrace();
        }




    }

    public static Properties getClientProperties() {
        ClassLoader classLoader = Launcher.class.getClassLoader();
        InputStream clientResource = classLoader.getResourceAsStream("client.properties");
        Properties clientProperties = new Properties();
        try {
            clientProperties.load(clientResource);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return clientProperties;
    }
}
