package jessca.study;

public class Study {
    String date ;
    int score;
    int id ;
    @Override
    public String toString() {
        return "Study{" +
                "date='" + date + '\'' +
                ", score=" + score +
                ", id=" + id +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Study() {
    }

    public Study(String date, int score) {
        this.date = date;
        this.score = score;
    }

    public Study(String date, int score, int id) {
        this.date = date;
        this.score = score;
        this.id = id;
    }
}
