package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task08 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> textWords = textParser.getTextWords(text);
        List<String> sortedWords;
        Text result = new Text();

        sortedWords = textWords.stream()
                .distinct()
                .filter((w) -> TextUtilities.isCharVowel(w.charAt(0)))
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int firstConsonantIndex1 = TextUtilities.findFirstConsonantIndex(o1);
                        int firstConsonantIndex2 = TextUtilities.findFirstConsonantIndex(o2);


                        return o1.substring(firstConsonantIndex1)
                                .compareTo(o2.substring(firstConsonantIndex2));
                    }
                })
                .collect(Collectors.toList());

        String words = TextUtilities.stringListToLines(sortedWords);
        result.setContent(words);
        return result;
    }
}
