package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Task06 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> textWords = textParser.getTextWords(text);
        Text result = new Text();

        ListIterator<String> iterator = textWords.listIterator();
        while (iterator.hasNext()) {
            iterator.set(iterator.next().toLowerCase());
        }
        Collections.sort(textWords);

        StringBuilder words = new StringBuilder();
        char lastLetter = ' ';
        for (String word : textWords) {
            if (word.length() > 0) {
                char firstWordChar = word.charAt(0);
                if (lastLetter != firstWordChar) {
                    lastLetter = firstWordChar;
                    words.append("\n")
                            .append(word);
                } else {
                    words.append(word);
                }
                words.append(" ");
            }
        }

        result.setContent(words.toString());
        return result;
    }
}
