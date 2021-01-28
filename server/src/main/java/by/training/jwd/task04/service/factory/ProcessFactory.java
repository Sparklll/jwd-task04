package by.training.jwd.task04.service.factory;

import by.training.jwd.task04.entity.text.Text;
import by.training.jwd.task04.service.Process;
import by.training.jwd.task04.service.impl.task.*;

public class ProcessFactory {
    public static Process createProcess(int processId) {
        return switch (processId) {
            case 1 -> new Task01();
            case 2 -> new Task02();
            case 3 -> new Task03();
            case 4 -> new Task04();
            case 5 -> new Task05();
            case 6 -> new Task06();
            case 7 -> new Task07();
            case 8 -> new Task08();
            case 9 -> new Task09();
            case 10 -> new Task10();
            case 11 -> new Task11();
            case 12 -> new Task12();
            case 13 -> new Task13();
            case 14 -> new Task14();
            case 15 -> new Task15();
            case 16 -> new Task16();
            default -> (Process) (text, parameters) -> new Text();
        };
    }
}
