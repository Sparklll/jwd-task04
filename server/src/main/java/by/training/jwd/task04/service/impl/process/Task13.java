package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task13 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> textWords = textParser.getTextWords(text);
        Text result = new Text();

        char c = parameters.length() > 0
                ? parameters.charAt(0)
                : ' ';

        List<String> processedWords = textWords.stream()
                .distinct()
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int firstWordCharOccurrences = TextUtilities.charOccurrences(o1, c);
                        int secondWordCharOccurrences = TextUtilities.charOccurrences(o2, c);
                        return Integer.compare(secondWordCharOccurrences, firstWordCharOccurrences);
                    }
                }.thenComparing(String::compareTo))
                .collect(Collectors.toList());

        String words = TextUtilities.stringListToLines(processedWords);
        result.setContent(words);
        return result;
    }
}
