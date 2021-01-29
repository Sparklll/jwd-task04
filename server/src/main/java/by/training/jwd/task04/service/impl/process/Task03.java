package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;

import java.util.*;

public class Task03 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        Text result = new Text();
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);

        try {
            List<String> firsSentenceWords = textParser.getSentenceWords(sentences.get(0));
            Map<String, Integer> firstSentenceWordsTextOccurrances = new HashMap<>();

            for (String word : firsSentenceWords) {
                firstSentenceWordsTextOccurrances.put(word, 0);
            }

            for (int i = 1; i < sentences.size(); i++) {
                List<String> sentenceWords = textParser.getSentenceWords(sentences.get(i));

                for (String word : firsSentenceWords) {
                    if (sentenceWords.contains(word)) {
                        int previousOccurrancesNumber = firstSentenceWordsTextOccurrances.get(word);
                        firstSentenceWordsTextOccurrances.put(word, ++previousOccurrancesNumber);
                        break;
                    }
                }
            }

            for (Map.Entry<String, Integer> entry : firstSentenceWordsTextOccurrances.entrySet()) {
                if (entry.getValue() == 0) {
                    result.setContent(entry.getKey());
                    break;
                }
            }

            return result;

        } catch (IndexOutOfBoundsException e) {

        }
        return new Text();
    }
}
