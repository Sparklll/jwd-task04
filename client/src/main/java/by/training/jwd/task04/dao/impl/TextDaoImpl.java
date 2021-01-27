package by.training.jwd.task04.dao.impl;

import by.training.jwd.task04.dao.TextDao;
import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.util.ReaderUtilities;

import java.nio.file.Path;

public class TextDaoImpl implements TextDao {
    private static TextDaoImpl instance;

    private TextDaoImpl() {
    }

    public static TextDaoImpl getInstance() {
        if (instance == null) {
            instance = new TextDaoImpl();
        }
        return instance;
    }

    @Override
    public Text retrieveText() {
        Path filePath = ReaderUtilities.getFilePathFromResources("book.txt");
        String fileContents = ReaderUtilities.getFileContents(filePath);
        Text text = new Text(fileContents);

        return text;
    }
}
