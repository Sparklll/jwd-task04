package by.training.jwd.task04.controller;

import by.training.jwd.task04.view.impl.ConsoleView;

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
        consoleView.printUnsupportedCommandExecution();
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
