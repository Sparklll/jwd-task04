package by.training.jwd.task04.service;

import by.training.jwd.task04.entity.text.Text;

public interface TextProcessorService {
    Text processText(Text text, String parameters);
}
