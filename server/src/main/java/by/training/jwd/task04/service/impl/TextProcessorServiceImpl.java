package by.training.jwd.task04.service.impl;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.TextProcessorService;
import by.training.jwd.task04.service.factory.ProcessFactory;
import by.training.jwd.task04.service.parser.TextParser;
import by.training.jwd.task04.service.util.TextUtilities;

public class TextProcessorServiceImpl implements TextProcessorService {
    @Override
    public Text processText(Text text, String parameters) {
        String additionalParameters = "";
        int processId;

        String[] parametersParts = parameters.split("\n");
        processId = Integer.parseInt(parametersParts[0]);
        if(parametersParts.length > 1) {
            additionalParameters = parametersParts[1];
        }

        Text preparedText = TextParser
                .getInstance()
                .excludeCode(text);
        TextUtilities.clearEmptySpace(preparedText);
        Process processToPerform = ProcessFactory.createProcess(processId);
        Text result = processToPerform.perform(preparedText, additionalParameters);

        return result;
    }
}
