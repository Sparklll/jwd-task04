package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.*;
import java.util.stream.Collectors;

public class Task10 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        Text result = new Text();

        Map<String, Integer> paramWordsOccurrences = new HashMap<>();

        for (String param : parameters.split(" ")) {
            paramWordsOccurrences.put(param, 0);
        }

        sentences.forEach((s) -> {
            List<String> sentenceWords = textParser.getSentenceWords(s);
            for (Map.Entry<String, Integer> entry : paramWordsOccurrences.entrySet()) {
                int prevOccurrences = entry.getValue();
                int occurrencesInSentence = Collections.frequency(sentenceWords, entry.getKey());
                entry.setValue(prevOccurrences + occurrencesInSentence);
            }
        });

        List<String> occurrences = paramWordsOccurrences.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map((p) -> p.getKey() + " -- " + p.getValue())
                .collect(Collectors.toList());

        String lineOccurrences = TextUtilities.stringListToLines(occurrences);
        result.setContent(lineOccurrences);
        return result;
    }
}
