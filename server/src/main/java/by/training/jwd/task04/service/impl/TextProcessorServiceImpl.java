package by.training.jwd.task04.service.impl;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.TextProcessorService;
import by.training.jwd.task04.service.factory.ProcessFactory;

public class TextProcessorServiceImpl implements TextProcessorService {
    @Override
    public Text processText(Text text, String parameters) {
        String[] splittedParameters = parameters.split("\n");
        int processId = Integer.parseInt(splittedParameters[0]);
        String additionalParameters = splittedParameters[1];

        Process processToPerform = ProcessFactory.createProcess(processId);
        Text result = processToPerform.perform(text, additionalParameters);

        return result;
    }
}
