package com.eating.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.eating.pojo.Favourite;

import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FavouriteTest {

    /**
     * 查询所有
     */
    @Test
    public void testSelectAll() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        String sql = "select * from tb_favourite;";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        Favourite fav = null;
        List<Favourite> favourites = new ArrayList<>();
        while (rs.next()) {
            int userId = rs.getInt("user_id");
            int foodId = rs.getInt("food_id");
            fav = new Favourite();
            fav.setUserId(userId);
            fav.setFoodId(foodId);
            favourites.add(fav);
        }
        System.out.println(favourites);
        rs.close();
        pstmt.close();
        conn.close();
    }

}
