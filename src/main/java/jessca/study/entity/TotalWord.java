package jessca.study.entity;

public class TotalWord {
    String name;
    int total;

    @Override
    public String toString() {
        return "TotalWord{" +
                "gift_name='" + name + '\'' +
                ", total=" + total +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public TotalWord(String name, int total) {
        this.name = name;
        this.total = total;
    }
}
