package by.training.jwd.task04.service.parser;

import by.training.jwd.task04.entity.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.text.BreakIterator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private static final String REGEX_PROPERTIES = "regex.properties";
    private static final String REGEX_CODE_PROPERTY = "regex.pattern.code";
    public static final Pattern CODE_PATTERN;
    private static Properties parserProperties;


    public static final Locale DEFAULT_LOCALE = Locale.US;
    private static TextParser instance;

    static { // mb take out somewhere
        try (InputStream inputStream = TextParser.class.getClassLoader().getResourceAsStream(REGEX_PROPERTIES)) {
            parserProperties = new Properties();
            parserProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String codePattern = parserProperties.getProperty(REGEX_CODE_PROPERTY);
        CODE_PATTERN = Pattern.compile(codePattern);
    }

    private TextParser() {

    }

    public static TextParser getInstance() {
        if (instance == null) {
            instance = new TextParser();
        }
        return instance;
    }

    public Text excludeCode(Text text) {
        Matcher matcher = CODE_PATTERN.matcher(text.getContent());
        String content = matcher.replaceAll("");
        return new Text(content);
    }

    public List<String> getSentences(Text text) {
        List<String> sentences = new ArrayList<>();

        BreakIterator iterator = BreakIterator.getSentenceInstance(DEFAULT_LOCALE);
        String content = text.getContent();
        iterator.setText(content);

        int start = iterator.first();
        int end = iterator.next();
        while (end != BreakIterator.DONE) {
            String sentence = content.substring(start, end).trim();
            sentences.add(sentence);

            start = end;
            end = iterator.next();
        }
        return sentences;
    }

    public List<String> getSentenceWords(String sentence) {
        List<String> sentenceWords = new ArrayList<>();

        BreakIterator iterator = BreakIterator.getWordInstance(DEFAULT_LOCALE);
        iterator.setText(sentence);

        int start = 0;
        int end = iterator.first();
        while (end != BreakIterator.DONE) {
            String word = sentence.substring(start, end);
            for (int i = start; i < end; i++) {
                if (Character.isLetter(sentence.codePointAt(i))) {
                    sentenceWords.add(word);
                    break;
                }
            }
            start = end;
            end = iterator.next();
        }

        return sentenceWords;
    }

    public List<String> getTextWords(Text text) {
        List<String> textSentences = getSentences(text);
        List<String> textWords = new ArrayList<>();

        textSentences.forEach((s) -> {
            List<String> sentenceWords = getSentenceWords(s);
            textWords.addAll(sentenceWords);
        });

        return textWords;
    }
}
