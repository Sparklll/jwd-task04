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

    public void printClientLabel() {
        writer.printf(CLIENT_LABEL);
    }

    public void printConnectionAttempt() {
        writer.println(APP_LABEL + "Attempting to connect to the server...");
    }

    public void printConnectionStatus(boolean isConnected) {
        String status = isConnected ? "Successfully connected!" : "Server connection ERROR!";
        writer.println(APP_LABEL + status);
    }

    public void printUnsupportedCommandExecution() {
        writer.println(APP_LABEL + WARNING_LABEL + " Unknown command to execute");
    }

    public void printApplicationShutdown() {
        writer.println(APP_LABEL + "Shutting down the application");
    }

    public void printAvailableCommands() {
        writer.println(APP_LABEL + "You can use the following commands: ");
        writer.println("\t[operations] - displays a list of available operations");
        writer.println("\t[1-16] - selection the operation number to be performed");
        writer.println("\t[exit] - exits the program");
    }

    public void printAvailableProcessingOperations() {
        writer.println("Select number(1-16) of text processing operation: ");
        writer.println("(1) - Find the largest number of sentences of text that contain the same words.");
        writer.println("(2) - Display all sentences of the given text in ascending order of the number of \n" +
                "words in each of them.");
        writer.println("(3) - Find a word in the first sentence that is not in any of the other sentences.");
        writer.println("(4) - Find and print words of a given length in all interrogative sentences of the text.");
        writer.println("(5) - In each sentence of the text, swap the first word with the last, without changing \n" +
                "the length of the sentence.");
        writer.println("(6) - First type the words of the text in alphabetical order by first letter.\n" +
                "Words starting with a new letter are printed on the new line.");
        writer.println("(7) - Sort the text in ascending order of the number of vowels (the number of vowels to the \n" +
                "total number of letters in the word).");
        writer.println("(8) - Sort words of the text starting with vowels in alphabetical order according to the \n" +
                "first consonant of the word.");
        writer.println("(9) - Sort all words of the text in ascending order of the number of the specified letter in the word.\n" +
                "Arrange words with the same number of letters in alphabetical order.");
        writer.println("(10) - There is a text and a word list. For each word from the given list, find, how many times \n" +
                "it occurs in each sentence, and sort the words by decreasing of the total number of occurrences.");
        writer.println("(11) - In each sentence of the text, exclude the maximum length substring, starting and ending \n" +
                "with the specified characters.");
        writer.println("(12) - Remove from the text all words of a given length beginning with a consonant letter.");
        writer.println("(13) - Sort words in the text in descending order by the number of occurrences of a given character \n" +
                "and in case of equality alphabetically.");
        writer.println("(14) - In the given text, find a palindrome substring of the maximum length.");
        writer.println("(15) - Convert every word in the text by removing all subsequent (previous) words from it \n" +
                "occurrences of the first (last) letter of this word.");
        writer.println("(16) - In some sentence of the text, words of a given length replace the specified \n" +
                "substring, the length of which may not be the same as the length of the word.");
    }
}
