package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.List;

public class Task05 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        Text result = new Text();

        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            List<String> sentenceWords = textParser.getSentenceWords(sentence);
            if (sentenceWords.size() >= 2) {
                String firstWord = sentenceWords.get(0);
                String lastWord = sentenceWords.get(sentenceWords.size() - 1);

                sentence = sentence.replaceFirst(firstWord, lastWord);
                int lastWordIndex = sentence.lastIndexOf(lastWord);

                String processedSentence = sentence.substring(0, lastWordIndex) + firstWord
                        + sentence.substring(lastWordIndex + lastWord.length());
                sentences.set(i, processedSentence);
            }
        }

        String processedSentences = TextUtilities.stringListToLines(sentences);
        result.setContent(processedSentences);
        return result;
    }
}
