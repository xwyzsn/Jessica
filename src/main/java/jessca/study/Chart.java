package jessca.study;

import lombok.Data;

@Data
public class Chart {
    String date ;
    int score;
    String username;

    public Chart(String date, int score, String username) {
        this.date = date;
        this.score = score;
        this.username = username;
    }

    public Chart() {
    }

    public Chart(String date, int score) {
        this.date = date;
        this.score = score;
    }
}
