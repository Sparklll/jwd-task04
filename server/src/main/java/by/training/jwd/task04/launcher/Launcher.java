package by.training.jwd.task04.launcher;

import by.training.jwd.task04.server.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Launcher {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(10000);
            while(true) {
                s.accept();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
