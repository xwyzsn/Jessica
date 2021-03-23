package jessca.study;

public class Chart {
    String date ;
    int score;

    @Override
    public String toString() {
        return "Chart{" +
                "date='" + date + '\'' +
                ", score=" + score +
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

    public Chart() {
    }

    public Chart(String date, int score) {
        this.date = date;
        this.score = score;
    }
}
