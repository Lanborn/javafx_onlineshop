package com.NetWorkSaleSystem.dao;

import com.NetWorkSaleSystem.JDBCUtils.JDBCUtils;
import com.NetWorkSaleSystem.bean.Good;
import com.NetWorkSaleSystem.bean.shoppingcar;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class goodsDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public List<Good> findgoods(){
        try {
            String sql = "select * from goods";
            return template.query(sql, new BeanPropertyRowMapper<>(Good.class));
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    public int insertshopcar(shoppingcar sc){
        String sql = "insert into shopcar(cid,gid,num)"+"value(?,?,?)";
        int count = template.update(sql, sc.getCid(), sc.getGid(), sc.getNum());
        return count;
    }

    public int updateGood(Good good){
        String sql = "update goods set name = ?,price=?,num=? where gid=?";
        int count = template.update(sql, good.getName(),good.getPrice(),good.getNum(),good.getGid());
        return  count;
    }
    public int delGood(Good good){
        String sql = "delete from goods where gid = ?";
        int count = template.update(sql, good.getGid());
        return count;
    }

    public int addGood(Good good){
        String sql = "insert into goods(name,price,manu,num) values(?,?,?,?)";
        int count = template.update(sql, good.getName(), good.getPrice(), good.getManu(), good.getNum());
        return count;
    }
}
