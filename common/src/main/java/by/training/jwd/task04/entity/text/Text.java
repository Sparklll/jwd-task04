package by.training.jwd.task04.entity.text;

import java.io.Serializable;
import java.util.Objects;

public class Text implements Serializable {
    private static final long serialVersionUID = 2875922765704550768L;

    private String text = "";

    public Text() {
    }

    public Text(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text1 = (Text) o;
        return Objects.equals(text, text1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
