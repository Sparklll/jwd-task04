package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.List;
import java.util.stream.Collectors;

public class Task02 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        Text result = new Text();

        sentences = sentences.stream()
                .sorted((s1, s2) -> {
                    int wordCount1 = textParser.getSentenceWords(s1).size();
                    int wordCount2 = textParser.getSentenceWords(s2).size();
                    return Integer.compare(wordCount1, wordCount2);
                }).collect(Collectors.toList());

        String processedSentences = TextUtilities.stringListToLines(sentences);
        result.setContent(processedSentences);
        return result;
    }
}
