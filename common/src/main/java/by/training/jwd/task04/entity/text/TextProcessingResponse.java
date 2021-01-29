package by.training.jwd.task04.entity.text;

import by.training.jwd.task04.entity.interaction.Response;

import java.util.Objects;

public class TextProcessingResponse extends Response {
    private static final long serialVersionUID = 7500128464733083848L;

    private Text text;

    public TextProcessingResponse() {
        text = new Text();
    }

    public TextProcessingResponse(Text text) {
        this.text = text;
    }

    public TextProcessingResponse(Text text, String parameters) {
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
        TextProcessingResponse that = (TextProcessingResponse) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}
