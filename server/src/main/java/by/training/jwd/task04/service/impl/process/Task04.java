package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.List;
import java.util.stream.Collectors;

public class Task04 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        Text result = new Text();

        int wordLength = Integer.parseInt(parameters);

        StringBuilder sentenceBuilder = new StringBuilder();
        sentences.stream()
                .filter(TextUtilities::isSentenceInterrogative)
                .forEach((s) -> {
                    sentenceBuilder
                            .append(s)
                            .append(" -- ");

                    List<String> suitableWords = textParser.getSentenceWords(s).stream()
                            .filter((word) -> word.length() == wordLength)
                            .distinct()
                            .collect(Collectors.toList());

                    suitableWords.forEach((word) -> sentenceBuilder
                            .append(word)
                            .append(" "));

                    sentenceBuilder
                            .append("\n");
                });

        result.setContent(sentenceBuilder.toString());
        return result;
    }
}
