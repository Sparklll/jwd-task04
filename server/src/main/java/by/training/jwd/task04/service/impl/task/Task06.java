package by.training.jwd.task04.service.impl.task;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;

import java.util.List;

public class Task06 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        return null;
    }
}
