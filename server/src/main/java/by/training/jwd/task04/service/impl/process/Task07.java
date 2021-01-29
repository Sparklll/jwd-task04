package by.training.jwd.task04.service.impl.process;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Task07 implements Process {
    @Override
    public Text perform(Text text, String parameters) {
        TextParser textParser = TextParser.getInstance();
        List<String> textWords = textParser.getTextWords(text);
        List<String> sortedWords;
        Text result = new Text();

        sortedWords = textWords.stream()
                .distinct()
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int vowelNumber1 = countVowelsInString(o1);
                        int vowelNumber2 = countVowelsInString(o2);
                        double vowelSaturation1 = (double) vowelNumber1 / o1.length();
                        double vowelSaturation2 = (double) vowelNumber2 / o2.length();
                        return Double.compare(vowelSaturation1, vowelSaturation2);
                    }
                }).collect(Collectors.toList());

        String processedWords = TextUtilities.stringListToLines(sortedWords);
        result.setContent(processedWords);
        return result;
    }

    private static int countVowelsInString(String s) {
        int vowelNumber = 0;
        for (char c : s.toCharArray()) {
            if (TextUtilities.isCharVowel(c)) {
                vowelNumber++;
            }
        }
        return vowelNumber;
    }
}

