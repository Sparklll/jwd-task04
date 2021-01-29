package by.training.jwd.task04.entity.text;

import by.training.jwd.task04.entity.interaction.Request;

import java.util.Objects;

public class TextProcessingRequest extends Request {
    private static final long serialVersionUID = 2453491225836160966L;

    private Text text;

    public TextProcessingRequest() {
        text = new Text();
    }

    public TextProcessingRequest(Text text) {
        this.text = text;
    }

    public TextProcessingRequest(Text text, String parameters) {
        super(parameters);
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TextProcessingRequest that = (TextProcessingRequest) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}
