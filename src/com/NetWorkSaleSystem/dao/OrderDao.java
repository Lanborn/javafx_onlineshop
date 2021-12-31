package com.NetWorkSaleSystem.dao;

import com.NetWorkSaleSystem.JDBCUtils.JDBCUtils;
import com.NetWorkSaleSystem.bean.Order;
import com.NetWorkSaleSystem.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public List<Order> findOrder(User user){
        String sql = "select * from orders where cid = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Order.class),
                user.getCid());
    }
}
