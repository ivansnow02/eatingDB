package com.eating.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.eating.pojo.User;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserTest {
    /**
     * 查询所有
     */
    @Test
    public void testSelectAll() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        String sql = "select * from tb_user;";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        User user = null;
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String userName = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            user = new User();
            user.setId(id);
            user.setUserName(userName);
            user.setEmail(email);
            user.setPassword(password);
            users.add(user);
        }
        System.out.println(users);
        rs.close();
        pstmt.close();
        conn.close();
    }

    /**
     * 添加
     */
    @Test
    public void testAdd() throws Exception {
        String userName = "chuoshen";
        String email = "123@12.com";
        String password = "121212";

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        String sql = "insert into tb_user(username, email, password) values(?,?,?);";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userName);
        pstmt.setString(2, email);
        pstmt.setString(3, password);

        int count = pstmt.executeUpdate();

        System.out.println(count > 0);
        pstmt.close();
        conn.close();
    }

}
