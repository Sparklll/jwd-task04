package by.training.jwd.task04.service.util;

import by.training.jwd.task04.entity.text.Text;

import java.util.List;

public final class TextUtilities {
    public static final String QUESTION_MARK = "?";
    public static final String VOWELS = "AEIOUYaeiouy";

    private TextUtilities() {
    }

    public static boolean isSentenceInterrogative(String sentence) {
        return sentence.endsWith(QUESTION_MARK);
    }

    public static boolean isCharVowel(char c) {
        return VOWELS.indexOf(c) > -1;
    }

    public static int findFirstConsonantIndex(String s) {
        int index = 0;
        for (char c : s.toCharArray()) {
            if (!isCharVowel(c)) {
                break;
            }
            index++;
        }
        return index;
    }

    public static int charOccurrences(String s, char c) {
        int occurrances = 0;
        for (char stringChar: s.toCharArray()) {
            if(stringChar == c) {
                occurrances++;
            }
        }
        return occurrances;
    }

    public static void clearEmptySpace(Text text) {
        String content = text.getContent();
        content = content.replaceAll("\\s+", " ");
        text.setContent(content);
    }

    public static String clearEmptySpace(String text) {
        return text.replaceAll("\\s+", " ");
    }

    public static String stringListToLines(List<String> stringList) {
        StringBuilder lines = new StringBuilder();
        stringList.forEach((s) -> {
            lines.append(s)
                    .append("\n");
        });
        return lines.toString();
    }
}
