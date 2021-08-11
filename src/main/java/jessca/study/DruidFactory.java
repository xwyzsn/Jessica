package jessca.study;

import java.sql.Connection;

import com.alibaba.druid.pool.DruidDataSource;
public class DruidFactory {
    private static DruidDataSource dataSource = null;

    public static void init() throws Exception {

        dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("zzh0117");//aliyun :zzh0117  windows:12345678
        dataSource.setUrl("jdbc:mysql://localhost:3306/db1?useUnicode=true&characterEncoding=utf-8");//aliyun : db1 windows:student
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
    }

    public static Connection getConnection() throws Exception {
        if(null == dataSource)
        {
            init();
        }
        return dataSource.getConnection();
    }
}
