package by.training.jwd.task04.service;

import by.training.jwd.task04.entity.text.Text;

public interface Process {
    Text perform(Text text, String parameters);
}
