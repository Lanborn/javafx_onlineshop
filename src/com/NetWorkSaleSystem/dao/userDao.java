package com.NetWorkSaleSystem.dao;

import com.NetWorkSaleSystem.JDBCUtils.JDBCUtils;
import com.NetWorkSaleSystem.bean.Manager;
import com.NetWorkSaleSystem.bean.User;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class userDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public User findUser(User user){

        try{
            String sql = "select * from customer where username=? AND password = ?";
            User loginuser = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    user.getUsername(),
                    user.getPassword());
            return loginuser;

        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
    public List<User> findUsersList(){
        String sql = "select cid,name,phone,IDcard,username from customer";
        return template.query(sql,new BeanPropertyRowMapper<>(User.class));
    }


    public Manager findManager(Manager manager){
        try{
            String sql = "select * from staff where username = ? AND password = ?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Manager.class),
                    manager.getUsername(),
                    manager.getPassword());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
