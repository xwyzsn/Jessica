package jessca.study;

public class ToDo {
    String text;
    Integer id;
    String date;

    @Override
    public String toString() {
        return "ToDo{" +
                "text='" + text + '\'' +
                ", id=" + id +
                ", date='" + date + '\'' +
                '}';
    }

    public ToDo() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ToDo( String date,String text) {
        this.text = text;
        this.date = date;
    }

    public ToDo(String text, Integer id, String date) {
        this.text = text;
        this.id = id;
        this.date = date;
    }
}
