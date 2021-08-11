package jessca.study;

import lombok.Data;

@Data
public class Study {
    String date ;
    int score;
    int id ;
    String name;
    String gift_finish;
    String username;

    public Study(String date, int score, int id, String name, String gift_finish) {
        this.date = date;
        this.score = score;
        this.id = id;
        this.name = name;
        this.gift_finish = gift_finish;
    }

    public Study(String date, int score, String name, String gift_finish, String username) {
        this.date = date;
        this.score = score;
        this.name = name;
        this.gift_finish = gift_finish;
        this.username = username;
    }

    public Study(String date, int score, int id, String name, String gift_finish, String username) {
        this.date = date;
        this.score = score;
        this.id = id;
        this.name = name;
        this.gift_finish = gift_finish;
        this.username = username;
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
