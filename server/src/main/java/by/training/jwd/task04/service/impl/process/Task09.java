package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Task09 implements Process {
    private static final char DEFAULT_CHAR = 'a';

    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> textWords = textParser.getTextWords(text);
        List<String> sortedWords;
        Text result = new Text();

        char letter = parameters.length() > 0
                ? parameters.charAt(0)
                : DEFAULT_CHAR;

        sortedWords = textWords.stream()
                .distinct()
                .filter((w) -> w.indexOf(letter) > - 1)
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int letterOccurrences1 = TextUtilities.charOccurrences(o1, letter);
                        int letterOccurrences2 = TextUtilities.charOccurrences(o2, letter);
                        return Integer.compare(letterOccurrences1, letterOccurrences2);
                    }
                }.thenComparing(String::compareTo))
                .collect(Collectors.toList());

        String words = TextUtilities.stringListToLines(sortedWords);
        result.setContent(words);
        return result;
    }
}
