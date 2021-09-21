package jessca.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jessca.study.Configure.DruidFactory;
import jessca.study.Utils.DateUtils;
import jessca.study.entity.*;
import jessca.study.mapper.TodoMapper;
import jessca.study.mapper.UserMapper;
import jessca.study.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jessca.study.entity.Study;
import jessca.study.entity.ToDo;
import jessca.study.entity.Word;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudyService {




    public StudyService() throws Exception {
    }
    public List<Study> getStudy(String username) throws Exception{
        Connection conn = DruidFactory.getConnection();
        List<Study> l = new ArrayList<Study>();
        Statement statement = conn.createStatement();
        String sql = "select * from study where username = \""+username+"\"";
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(resultSet);
        while (resultSet.next()){
            String date = resultSet.getString(2);
            int score = resultSet.getInt(3);
            System.out.println(score);
            int id = resultSet.getInt(1);
            l.add(new Study(date,score,id));
        }
        statement.close();
        conn.close();
        return l;
    }

    public void postToDb(Study study) throws Exception {
        Connection conn = DruidFactory.getConnection();

        System.out.println(study);
        String date = study.date;
        Integer score = study.score;
        String name = null;
        String sql ="";
        String username = study.username;
        Integer res=null;
        if(study.gift_name !=null&&!study.gift_name.equals("")){
            name=study.gift_name;
            sql = "insert into study(date,score,gift_name,username) values(?,?,?,?) ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,date);
            preparedStatement.setInt(2,score);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,username);
            res = preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        else {
            sql = "insert into study(date,score,username) values(?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,date);
            preparedStatement.setInt(2,score);
            preparedStatement.setString(3,username);
            res = preparedStatement.executeUpdate();

            preparedStatement.close();
        }
//        System.out.println(sql);
        if(res!=0){
            System.out.println("success");
        }
        else {
            System.out.println("fail");
        }

        conn.close();
    }
    public List<ToDo> getTodo() throws Exception {
        Connection conn = DruidFactory.getConnection();
        Statement statement=conn.createStatement();
        String sql = "select * from to_do";
        System.out.println(sql);
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
        String sql ="delete  from to_do where id = "+itemId;
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
        String date = toDo.limittime;
        String context=toDo.content;
        Integer userid = toDo.userid;
        String sql = "insert into to_do(LimitTime,content,userId) values(?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,date);
        preparedStatement.setString(2,context);
        preparedStatement.setInt(3,userid);
        int f = preparedStatement.executeUpdate();
        System.out.println(sql);
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
        String sql ="SELECT date,sum(score),username FROM study GROUP BY date,username ORDER BY date ";
        List<Chart>l=new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            String date = resultSet.getString(1);
            int score = resultSet.getInt(2);
            String username = resultSet.getString(3);
            l.add(new Chart(date,score,username));
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
        String sql = "SELECT * FROM study WHERE score<0 and gift_name is NOT NULL  ORDER BY date DESC";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Study> list = new ArrayList<>();
        while (resultSet.next()){
            Integer id = resultSet.getInt(1);
            String date = resultSet.getString(2);
            Integer score = resultSet.getInt(3);
            String name = resultSet.getString(4);
            String finish = resultSet.getString(5);
            String username = resultSet.getString(6);
            Study study = new Study(date,score,id,name,finish,username);
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


    @Autowired
    private MailService mailService;
    @Autowired
    private TodoMapper todoMapper;
    @Autowired
    private DateUtils dateUtils;
    @Autowired
    private UserMapper userMapper;
    public void sendMail() {
        java.util.Date date = new Date();
        String deadline = dateUtils.addDate(date,3);
        String notifyLine = dateUtils.addDate(date,-3);
        QueryWrapper<ToDo>queryWrapper =new QueryWrapper<>();
        queryWrapper.and(i->i.lt("limittime",deadline).gt("limittime",notifyLine));
        List<ToDo> toDoList = todoMapper.selectList(queryWrapper);
        for(ToDo toDo:toDoList){

            User user=userMapper.selectById(toDo.getUserid());
            mailService.sendSimpleMail(user.getEmail(),toDo.getContent().toString()+"少于三天,请及时处理","代办事项提醒!");
        }

        System.out.println(toDoList);
    }

    public User getUser(String username) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name",username);
        User user = userMapper.selectOne(userQueryWrapper);
        return user;
    }

    @Autowired
    private WordMapper wordMapper;
    @Autowired
    private ScoreService scoreService;
    public void wordToStudy() {
        QueryWrapper<Word> wordQueryWrapper = new QueryWrapper<>();
        String today = dateUtils.getToday();
        String lastMonth = dateUtils.subMonth();
        wordQueryWrapper.and(i->i.lt("date",today).ge("date",lastMonth));
        List<Word> wordList = wordMapper.selectList(wordQueryWrapper);
        HashMap<String,List<Word>> hashMap = new HashMap<String,List<Word>>();
        wordList.forEach(word -> {
            if(!hashMap.containsKey(word.name)){
                List<Word> tempList = new ArrayList<>();
                tempList.add(word);
                hashMap.put(word.name,tempList);
            }else {
                hashMap.get(word.name).add(word);
            }
        });
        List<Study> list = new ArrayList<>();
        hashMap.forEach((k,v)->{
            AtomicInteger sum = new AtomicInteger();
            v.forEach(word -> {
                sum.addAndGet(word.number);
            });
            list.add(new Study(dateUtils.getToday (), (int) Math.floor(sum.get()/10),"f",k));
        });
        scoreService.saveBatch(list);
    }
}
