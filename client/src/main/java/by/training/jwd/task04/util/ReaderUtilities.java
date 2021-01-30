package by.training.jwd.task04.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ReaderUtilities {
    private static final Logger logger = LogManager.getLogger(ReaderUtilities.class);
    private ReaderUtilities() {

    }

    public static Path getFilePathFromResources(String fileName) {
        ClassLoader classLoader = ReaderUtilities.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.toPath();
    }

    public static String getFileContents(Path filePath) {
        String content = "";
        try {
            content = Files.readString(filePath);
        } catch (IOException exception) {
            logger.error("Unable to read text from file : " + filePath);
        }
        return content;
    }
}
