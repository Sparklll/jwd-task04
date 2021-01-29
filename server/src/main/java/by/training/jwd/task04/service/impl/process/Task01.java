package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;

import java.util.*;

public class Task01 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        Set<String> uniqueTextWords = new HashSet<>();
        Map<String, Integer> wordsSentencesOccurrences = new HashMap<>();

        sentences.forEach((sentence) -> {
            List<String> sentenceWords = textParser.getSentenceWords(sentence);
            uniqueTextWords.addAll(sentenceWords);
        });

        uniqueTextWords.forEach((word) -> {
            int occurrences = (int) sentences.stream().filter(sentence -> sentence.contains(word)).count();
            wordsSentencesOccurrences.put(word, occurrences);
        });

        return new Text(Collections.max(wordsSentencesOccurrences.values()).toString());
    }
}
