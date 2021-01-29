package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.List;

public class Task12 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        Text result = new Text();

        int wordsToRemoveLength = Integer.parseInt(parameters);

        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            List<String> sentenceWords = textParser.getSentenceWords(sentence);
            for(String word : sentenceWords) {
                if(word.length() == wordsToRemoveLength
                        && !TextUtilities.isCharVowel(word.charAt(0))) {
                    sentence = sentence.replaceFirst(word, "");
                    sentence = TextUtilities.clearEmptySpace(sentence);
                    sentences.set(i, sentence);
                }
            }
        }

        String processedSentences = TextUtilities.stringListToLines(sentences);
        result.setContent(processedSentences);
        return result;
    }
}
