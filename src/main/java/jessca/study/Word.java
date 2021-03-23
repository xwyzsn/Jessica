package jessca.study;

public class Word {
    String name;
    int number;
    String date;
    @Override
    public String toString() {
        return "word{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", date='" + date + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Word(String name, int number, String date) {
        this.name = name;
        this.number = number;
        this.date = date;
    }
}
