package Model;

public class ModelTodoList {
    String text;
    String key;
    public ModelTodoList(String text)
    {
        this.text = text;
//        this.key = key;
    }

    public ModelTodoList() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
