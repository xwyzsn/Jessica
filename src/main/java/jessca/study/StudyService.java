package jessca.study;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Service
public class StudyService {

    public StudyService() throws Exception {
    }
    public List<Study> getStudy() throws Exception{
        Connection conn = DruidFactory.getConnection();
        List<Study> l = new ArrayList<Study>();
        Statement statement = conn.createStatement();
        String sql = "select * from study ";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String date = resultSet.getString(2);
            int score = resultSet.getInt(3);
            int id = resultSet.getInt(1);
            l.add(new Study(date,score,id));
        }
        statement.close();
        conn.close();
        return l;
    }

    public void postToDb(Study study) throws Exception {
        Connection conn = DruidFactory.getConnection();
        System.out.println(study.date);
        String date = study.date;
        Integer score = study.score;
        String sql = "insert into study(date,score) values("+" \""+date+"\" ,"+score+")";
        System.out.println(sql);
        Statement statement =conn.createStatement();
        int f = statement.executeUpdate(sql);
        if(f!=0){
            System.out.println("success");
        }
        else {
            System.out.println("fail");
        }

        statement.close();
        conn.close();
    }
    public List<ToDo> getTodo() throws Exception {
        Connection conn = DruidFactory.getConnection();
        Statement statement=conn.createStatement();
        String sql = "select * from todo";
        ResultSet resultSet =statement.executeQuery(sql);
        List<ToDo>list = new ArrayList<>();
        while(resultSet.next()){
            String context = resultSet.getString(2);
            int id = resultSet.getInt(1);
            String date = resultSet.getString(3);
            list.add(new ToDo(context,id,date));
        }
        statement.close();
        conn.close();

        return list;
    }

    public void deleteTo( int itemId) throws Exception {
        Connection conn = DruidFactory.getConnection();
        Statement statement=conn.createStatement();
        String sql ="delete  from todo where id = "+itemId;
        int f = statement.executeUpdate(sql);
        if(f!=0){
            System.out.println("delete success");
        }
        else {
            System.out.println("delete fail");
        }
        statement.close();
        conn.close();
    }

    public void addNewTodo(ToDo toDo) throws Exception {
        Connection conn = DruidFactory.getConnection();
        Statement statement = conn.createStatement();
        String date = toDo.date;
        String context=toDo.text;
        String sql = "insert into todo(LimitTime,content) values("+"\""+date+"\" ,\""+context+"\")";
        int f = statement.executeUpdate(sql);
        if(f!=0){
            System.out.println("success");
        }
        else{
            System.out.println("fail");
        }
        statement.close();
        conn.close();

    }

    public List<Chart> getChar() throws Exception {
        Connection conn = DruidFactory.getConnection();
        Statement statement = conn.createStatement();
        String sql ="SELECT date,sum(score) FROM study GROUP BY date ORDER BY date ";
        List<Chart>l=new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            String date = resultSet.getString(1);
            int score = resultSet.getInt(2);
            l.add(new Chart(date,score));
        }
        statement.close();
        conn.close();
        return l;

    }

    public List<Chart> getCharGreatZero() throws Exception {
        Connection conn = DruidFactory.getConnection();
        Statement statement = conn.createStatement();
        String sql ="SELECT date,sum(score) FROM study WHERE score>=0 GROUP BY date ORDER BY date ";
        List<Chart> l = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            String date = resultSet.getString(1);
            int score = resultSet.getInt(2);
            if(score>=24){
                continue;
            }
            l.add(new Chart(date,score));
        }
        statement.close();
        conn.close();
        return l;

    }

    public List<Word> getWord() throws Exception {
        Connection connection=DruidFactory.getConnection();
        Statement statement = connection.createStatement();
        String sql ="SELECT name,sum(number) as total ,date FROM word GROUP BY name,date  ORDER BY date ";
        ResultSet resultSet =statement.executeQuery(sql);
        List<Word>list=new ArrayList<>();
        while (resultSet.next()){
            String name=resultSet.getString(1);
            int number = resultSet.getInt(2);
            String date = resultSet.getString(3);

            Word word=new Word(name,number,date);

            list.add(word);
        }
        statement.close();
        connection.close();
        return list;

    }

    public void addWord(Word word) throws Exception {
        Connection connection = DruidFactory.getConnection();
        Statement statement = connection.createStatement();
        String sql = "INSERT into word(name,number,date) values( \""+word.name+"\","+word.number+",\""+word.date+"\" )";
        System.out.println(sql);
        int f =statement.executeUpdate(sql);

        if(f!=0){
            System.out.println("insert word success");
        }
        else{
            System.out.println("insert word fail");
        }
        statement.close();
        connection.close();

    }

    public List<TotalWord> getTotalWord() throws Exception {
        Connection connection=DruidFactory.getConnection();
        Statement statement=connection.createStatement();
        String sql="SELECT sum(number) as total,name FROM word  GROUP BY name";
        ResultSet resultSet =statement.executeQuery(sql);
        List<TotalWord>list=new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString(2);
            int total = resultSet.getInt(1);
            TotalWord totalWord=new TotalWord(name,total);
            list.add(totalWord);
        }
        statement.close();
        connection.close();
        return list;
    }

    public void saveUploadedFiles(final MultipartFile[] file, String title, String description ,String uploadtime) throws Exception {
        Connection connection = DruidFactory.getConnection();
        String sql = "insert into pic(path,`group`,description,uploadtime) values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (MultipartFile multipartFile:file) {
            final byte[] bytes = multipartFile.getBytes();
            final Path path = Paths.get("/home/jan/Desktop/picture/" + multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            preparedStatement.setString(1, String.valueOf(multipartFile.getOriginalFilename()));
            preparedStatement.setString(2, String.valueOf(title));
            preparedStatement.setString(3, String.valueOf(description));
            preparedStatement.setString(4,String.valueOf(uploadtime));
            preparedStatement.executeUpdate();

        }
        preparedStatement.close();
        connection.close();
    }

    public List<Picture> getPictureInfo() throws Exception {
        Connection connection = DruidFactory.getConnection();
        Statement statement = connection.createStatement();
        String sql ="select group_concat(`path`),`group`,description,uploadtime FROM pic  GROUP BY `group`";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Picture> list = new ArrayList<>();
        while (resultSet.next()){
            String pathString = resultSet.getString(1);
            String group = resultSet.getString(2);
            String description = resultSet.getString(3);
            String []path = pathString.split(",");
            String uploadtime = resultSet.getString(4);

            list.add(new Picture(path,group,description,uploadtime));
        }



        statement.close();
        connection.close();
        return list;
    }

    public List<Study> getGiftList() throws Exception {
        Connection connection = DruidFactory.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM study WHERE score<=-10 ORDER BY date DESC";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Study> list = new ArrayList<>();
        while (resultSet.next()){
            Integer id = resultSet.getInt(1);
            String date = resultSet.getString(2);
            Integer score = resultSet.getInt(3);
            String name = resultSet.getString(4);
            String finish = resultSet.getString(5);
            Study study = new Study(date,score,id,name,finish);
            list.add(study);
        }
        statement.close();
        connection.close();
        return list;

    }

    public void updateGiftStatus(Integer id, String finish) throws Exception {
        Connection connection = DruidFactory.getConnection();
        Statement statement = connection.createStatement();
        String sql = "update study  set gift_finish=\'"+finish+"\' where id=" +id+";";
        int f = statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }
}
