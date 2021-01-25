package by.training.jwd.task04.view.impl;

import by.training.jwd.task04.view.View;

import java.io.*;

public class ConsoleView implements View {
    private static final String APP_LABEL = "[APP]: ";
    private static final String CLIENT_LABEL = "[CLIENT]: ";
    private static final String SERVER_LABEL = "[SERVER]: ";
    private static final String WARNING_LABEL = "[WARNING]";
    private static final OutputStream DEFAULT_OUTPUT_STREAM = System.out;

    public PrintWriter writer;

    public ConsoleView() {
        writer = new PrintWriter(DEFAULT_OUTPUT_STREAM, true);
    }

    public ConsoleView(Writer out) {
        writer = new PrintWriter(out, true);
    }

    public void printGreeting() {
        writer.println("<<Text processing App. Welcome!>>");
    }

    public void printConnectionAttempt() {
        writer.println(APP_LABEL + "Attempting to connect to the server...");
    }

    public void printConnectionStatus(boolean isConnected) {
        String status = isConnected ? "Successfully connected!" : "Server connection ERROR!";
        writer.println(APP_LABEL + status);
    }

    public void printClientLabel() {
        writer.printf(CLIENT_LABEL);
    }

    public void printUnsupportedCommandExecution() {
        writer.println(APP_LABEL + WARNING_LABEL + " Unknown command to execute");
    }

    public void printApplicationShutdown() {
        writer.println(APP_LABEL + "Shutting down the application");
    }
}
