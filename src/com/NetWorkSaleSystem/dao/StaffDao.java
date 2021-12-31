package com.NetWorkSaleSystem.dao;

import com.NetWorkSaleSystem.JDBCUtils.JDBCUtils;
import com.NetWorkSaleSystem.bean.Staff;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StaffDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public List<Staff> findStaffs(){
        String sql = "select sid,name,sex,dob,phone,IDcard,username from staff";
        return template.query(sql, new BeanPropertyRowMapper<>(Staff.class));
    }
}
