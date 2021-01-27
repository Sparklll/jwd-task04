package by.training.jwd.task04.controller;

import by.training.jwd.task04.client.Client;
import by.training.jwd.task04.dao.TextDao;
import by.training.jwd.task04.dao.impl.TextDaoImpl;
import by.training.jwd.task04.entity.interaction.Request;
import by.training.jwd.task04.entity.interaction.Response;
import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.entity.text.TextProcessingRequest;
import by.training.jwd.task04.entity.text.TextProcessingResponse;
import by.training.jwd.task04.launcher.Launcher;
import by.training.jwd.task04.view.impl.ConsoleView;

import java.io.*;
import java.util.Objects;

public class ClientController {
    private final Client client;
    private ConsoleView consoleView;

    public ClientController(Client client, ConsoleView consoleView) {
        this.client = client;
        this.consoleView = consoleView;
    }

    public ConsoleView getConsoleView() {
        return consoleView;
    }

    public void setConsoleView(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    public void handleClientCommand(String clientCommand) {
        clientCommand = clientCommand
                .toLowerCase()
                .trim();

        switch (clientCommand) {
            case "operations" -> consoleView.printAvailableProcessingOperations();
            case "commands" -> consoleView.printAvailableCommands();
            case "exit" -> Launcher.isRunning = false;
            default -> {
                if (clientCommand.matches("[1-9]|1[0-6]")) { // 1-16 operation number
                    int operationNumber = Integer.parseInt(clientCommand);
                    String additionalParameters = readAdditionalParameters();

                    sendRequest(operationNumber, additionalParameters);
                    TextProcessingResponse response = (TextProcessingResponse) getResponse();

                    consoleView.printServerResponse(response);
                } else {
                    consoleView.printUnsupportedCommandExecution();
                }
            }
        }
    }

    public void sendRequest(int operationNumber, String additionalParameters) {
        String requestParameters = operationNumber + "\n" + additionalParameters;
        TextDao textDao = TextDaoImpl.getInstance();
        Text text = textDao.retrieveText();

        Request request = new TextProcessingRequest(text, requestParameters);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.writeObject(request);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Response getResponse() {
        Response response = new TextProcessingResponse();

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            response = (Response) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String readAdditionalParameters() {
        String parameters = "";
        consoleView.printRequestAdditionalParameters();
        try (BufferedReader reader
                     = new BufferedReader(new InputStreamReader(Launcher.DEFAULT_INPUT_STREAM))) {
            parameters = reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientController that = (ClientController) o;
        return Objects.equals(consoleView, that.consoleView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consoleView);
    }
}