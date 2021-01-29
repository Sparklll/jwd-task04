package by.training.jwd.task04.service.impl.task;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;

import java.util.List;
import java.util.stream.Collectors;

public class Task02 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);

        sentences = sentences.stream()
                .sorted((s1, s2) -> {
                    int wordCount1 = textParser.getSentenceWords(s1).size();
                    int wordCount2 = textParser.getSentenceWords(s2).size();
                    return Integer.compare(wordCount2, wordCount1);
                }).collect(Collectors.toList());

        StringBuilder sentenceBuilder = new StringBuilder();
        sentences.forEach((s) -> sentenceBuilder
                .append(s)
                .append("\n")
        );

        return new Text(sentenceBuilder.toString());
    }
}
