package Model;

public class ModelTodoList {
    String text;

    public ModelTodoList(String text) {
        this.text = text;
    }

    public ModelTodoList() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
