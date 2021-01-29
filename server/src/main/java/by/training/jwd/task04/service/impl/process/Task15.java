package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.List;
import java.util.regex.Pattern;

public class Task15 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        Text result = new Text();

        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            List<String> sentenceWords = textParser.getSentenceWords(sentence);
            for (String word : sentenceWords) {
                String firstWordLetter = Character.toString(word.charAt(0));
                String lastWordLetter = Character.toString(word.charAt(word.length() - 1));

                String newWord = word.replaceAll(firstWordLetter, "")
                        .replaceAll(lastWordLetter, "");
                String wordPattern = "\\b" + word + "\\b";
                sentence = sentence.replaceFirst(wordPattern, newWord);
            }
            sentences.set(i, sentence);
        }


        String processedWords = TextUtilities.stringListToLines(sentences);
        result.setContent(processedWords);
        return result;
    }
}
