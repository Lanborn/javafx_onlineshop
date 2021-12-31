package com.NetWorkSaleSystem.dao;

import com.NetWorkSaleSystem.JDBCUtils.JDBCUtils;
import com.NetWorkSaleSystem.bean.Order;
import com.NetWorkSaleSystem.bean.User;
import com.NetWorkSaleSystem.bean.shoppingcar;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class shoppingcarDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public List<shoppingcar> findCar(User user){
        try{
            String sql = "select * from shop_view where cid=?";
            return template.query(sql, new BeanPropertyRowMapper<>(shoppingcar.class),
                    user.getCid());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int delScShop(shoppingcar shopCar){
        String sql = "delete from shopcar where id=?";
        int count = template.update(sql, shopCar.getId());
        return  count;
    }

    public int toPay(Order order){
        String sql = "insert into orders(name,address,phone,cid,spid) values(?,?,?,?,?)";
        int count = template.update(sql, order.getName(),order.getAddress(),order.getPhone(),order.getCid(),order.getSpid());
        return count;
    }
}
