package jessca.study.entity;

import lombok.Data;

@Data
public class ToDo {
    public String content;
    public Integer id;
    public String limittime;
    public Integer userid;


    public ToDo() {
    }


    public ToDo( String date,String text) {
        this.content = text;
        this.limittime = date;
    }

    public ToDo(String content, String limittime, Integer userid) {
        this.content = content;
        this.limittime = limittime;
        this.userid = userid;
    }

    public ToDo(String text, Integer id, String date) {
        this.content = text;
        this.id = id;
        this.limittime = date;
    }

    public ToDo(String content, Integer id, String limittime, Integer userid) {
        this.content = content;
        this.id = id;
        this.limittime = limittime;
        this.userid = userid;
    }

}
