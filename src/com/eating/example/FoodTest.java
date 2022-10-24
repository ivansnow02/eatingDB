package com.eating.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.eating.pojo.Food;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FoodTest {
    /**
     * 查询所有
     */
    @Test
    public void testSelectAll() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        String sql = "select * from tb_food;";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        Food food = null;
        List<Food> foods = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String foodName = rs.getString("name");
            String canteen = rs.getString("canteen");
            int floor = rs.getInt("floor");
            food = new Food();
            food.setId(id);
            food.setName(foodName);
            food.setCanteen(canteen);
            food.setFloor(floor);
            foods.add(food);
        }
        System.out.println(foods);
        rs.close();
        pstmt.close();
        conn.close();
    }

}
