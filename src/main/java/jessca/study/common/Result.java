package jessca.study.common;

import lombok.Data;

@Data
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public static Result succ(Object data){

        return succ(200,"success",data);
    }
    public static Result succ(int i, String success, Object data){
        Result r = new Result();
        r.setCode(i);
        r.setMsg(success);
        r.setData(data);
        return r;

    }
    public static Result fail(Object data){
        return fail(400,"fail",data);
    }
    public static Result fail(int i,String fail,Object data){
        Result r = new Result();
        r.setData(data);
        r.setCode(i);
        r.setMsg(fail);
        return r;
    }


}
