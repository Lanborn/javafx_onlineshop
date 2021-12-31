package com.NetWorkSaleSystem.dao;

import com.NetWorkSaleSystem.JDBCUtils.JDBCUtils;
import com.NetWorkSaleSystem.bean.SaleOrder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class saleOrderDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public List<SaleOrder> findSaleOrder(){
        String sql="select * from saleorder";
        return template.query(sql,new BeanPropertyRowMapper<>(SaleOrder.class));
    }

}
