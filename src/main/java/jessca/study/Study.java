package jessca.study;

public class Study {
    String date ;
    int score;
    int id ;
    String name;
    String gift_finish;

    public Study(String date, int score, int id, String name, String gift_finish) {
        this.date = date;
        this.score = score;
        this.id = id;
        this.name = name;
        this.gift_finish = gift_finish;
    }

    @Override
    public String toString() {
        return "Study{" +
                "date='" + date + '\'' +
                ", score=" + score +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", gift_finish='" + gift_finish + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGift_finish() {
        return gift_finish;
    }

    public void setGift_finish(String gift_finish) {
        this.gift_finish = gift_finish;
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
