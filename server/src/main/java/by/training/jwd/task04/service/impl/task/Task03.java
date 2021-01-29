package by.training.jwd.task04.service.impl.task;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;

import java.util.List;

public class Task03 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);

        try {
            List<String> firsSentenceWords = textParser.getSentenceWords(sentences.get(0));

            for (int i = 1; i < sentences.size(); i++) {
                List<String> sentenceWords = textParser.getSentenceWords(sentences.get(i));

                for (String word: firsSentenceWords) {
                    if(sentenceWords.contains(word)) {
                        return new Text(word);
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {

        }
        return new Text();
    }
}
