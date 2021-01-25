package by.training.jwd.task04.controller;

import by.training.jwd.task04.launcher.Launcher;
import by.training.jwd.task04.view.impl.ConsoleView;

import java.util.Locale;
import java.util.Objects;

public class ConsoleController {
    private ConsoleView consoleView;

    public ConsoleController(ConsoleView consoleView) {
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
            case "exit" -> Launcher.isRunning = false;
            default -> {
                if(clientCommand.matches("[1-9]|1[0-6]")) { // 1-16

                } else {
                    consoleView.printUnsupportedCommandExecution();
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleController that = (ConsoleController) o;
        return Objects.equals(consoleView, that.consoleView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consoleView);
    }
}
