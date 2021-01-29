package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.List;


public class Task16 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        Text result = new Text();

        String[] params = parameters.split(" ");
        int wordToReplaceLength = Integer.parseInt(params[0]);
        String replacement = params[1];

        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            List<String> sentenceWords = textParser.getSentenceWords(sentence);
            for (String word : sentenceWords) {
                if (word.length() == wordToReplaceLength) {
                    sentence = sentence.replaceFirst(word, replacement);
                }
            }
            sentences.set(i, sentence);
        }

        String processedSentences = TextUtilities.stringListToLines(sentences);
        result.setContent(processedSentences);
        return result;
    }
}
