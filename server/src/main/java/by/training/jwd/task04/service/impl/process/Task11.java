package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.List;

public class Task11 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> sentences = textParser.getSentences(text);
        Text result = new Text();

        String[] params = parameters.split(" ");
        char firstLetter = params[0].charAt(0);
        char secondLetter = params[1].charAt(0);

        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            int firstLetterIndex = sentence.indexOf(firstLetter);
            int secondLetterIndex = sentence.lastIndexOf(secondLetter);

            if ((secondLetterIndex > firstLetterIndex) && firstLetterIndex >= 0) {
                String substrToRemove = sentence.substring(firstLetterIndex, secondLetterIndex + 1);
                sentence = sentence.replace(substrToRemove, "");
                sentence = TextUtilities.clearEmptySpace(sentence);
                sentences.set(i, sentence);
            }
        }

        String processedSentences = TextUtilities.stringListToLines(sentences);
        result.setContent(processedSentences);
        return result;
    }
}
