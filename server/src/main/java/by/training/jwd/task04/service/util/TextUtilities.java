package by.training.jwd.task04.service.util;

import by.training.jwd.task04.entity.text.Text;

public final class TextUtilities {
    public static final String QUESTION_MARK = "?";
    private TextUtilities() {
    }

    public static boolean isSentenceInterrogative(String sentence) {
        return sentence.endsWith(QUESTION_MARK);
    }

    public static void clearEmptySpace(Text text) {
        String content = text.getContent();
        content = content.replaceAll("\\s+", " ");
        text.setContent(content);
    }
}
