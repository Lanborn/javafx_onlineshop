package com.NetWorkSaleSystem.dao;

import com.NetWorkSaleSystem.JDBCUtils.JDBCUtils;
import com.NetWorkSaleSystem.bean.CheckOut;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class checkOutDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public List<CheckOut> findCheck(){
        String sql = "select orders.uuid,orders.name,orders.address,orders.phone,orders.cid,pay_view.gname,pay_view.num from orders,pay_view where orders.uuid=pay_view.uuid";
        return template.query(sql,new BeanPropertyRowMapper<>(CheckOut.class));
    }

//    public int checkGood(CheckOut check){
//        String sql = "insert into saleorder((select cname fron customer where cid=?),gname,sname,num)"+"value(?,?,?,?)";
//        template.update(sql);
//    }
}
