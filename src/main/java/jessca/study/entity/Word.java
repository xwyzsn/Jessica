package jessca.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Word {
    @TableId(value = "id",type = IdType.AUTO)
    public Integer id;
    public String name;
    public int number;
    public String date;
    @Override
    public String toString() {
        return "word{" +
                "gift_name='" + name + '\'' +
                ", number=" + number +
                ", date='" + date + '\'' +
                '}';
    }

    public Word(Integer id, String name, int number, String date) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.date = date;
    }

    public Word(String name, int number, String date) {
        this.name = name;
        this.number = number;
        this.date = date;
    }
}
