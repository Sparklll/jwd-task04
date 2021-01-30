package by.training.jwd.task04.controller;

import by.training.jwd.task04.entity.interaction.Request;
import by.training.jwd.task04.entity.interaction.Response;
import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.entity.text.TextProcessingRequest;
import by.training.jwd.task04.entity.text.TextProcessingResponse;
import by.training.jwd.task04.server.ServerConnection;
import by.training.jwd.task04.service.TextProcessorService;
import by.training.jwd.task04.service.impl.TextProcessorServiceImpl;

import java.util.Objects;

public class ServerConnectionController {
    private final ServerConnection serverConnection;

    public ServerConnectionController(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public ServerConnection getServerConnection() {
        return serverConnection;
    }

    public Response processClientRequest(Request clientRequest) {
        TextProcessingResponse serverResponse = new TextProcessingResponse();

        TextProcessingRequest textProcessingRequest = (TextProcessingRequest) clientRequest;
        Text text = textProcessingRequest.getText();
        String parameters = textProcessingRequest.getParameters();


        TextProcessorService textProcessorService = new TextProcessorServiceImpl();
        Text result = textProcessorService.processText(text, parameters);
        serverResponse.setText(result);

        return serverResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerConnectionController that = (ServerConnectionController) o;
        return Objects.equals(serverConnection, that.serverConnection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverConnection);
    }
}
