package com.NetWorkSaleSystem.JdbcTemplate;

import com.NetWorkSaleSystem.JDBCUtils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo2 {
    // 1. 获取jdbcTemplate对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //  Junit单元测试，可以让方法独立执行

    /*
     * 1.修改1号数据的 salary为10000
     * */
    @Test
    public void test1(){
        // 2. 定义sql语句
        String sql = "update emp set SAL = 10000 where EMPNO = 7369";
        // 3. 执行Sql
        int count = template.update(sql);
        System.out.println(count);
    }

    /*
     * 1. 添加一条记录
     *
     * */
    @Test
    public void test2(){
        String sql = "insert into emp(EMPNO,ENAME,DEPTNO) values(?,?,?)";

        int count =  template.update(sql,1015,"孙悟空",10);
        System.out.println(count);
    }

    /*
     * 删除刚才添加的记录
     * */

    @Test
    public void test3(){
        String sql = "delete from emp where EMPNO = ?";
        int count = template.update(sql,1015);
        System.out.println(count);
    }

    /*
     * 4. 查询EMPNO = 7369的记录,将其封装为Map集合
     * 注意： 这个方法查询的结果集长度只能是1
     * */

    @Test
    public void test4(){
        String sql = "select * from emp where EMPNO = ? or EMPNO = ?";
        Map<String, Object> map = template.queryForMap(sql, 7369.7499);
        System.out.println(map);
//        {EMPNO=7369, ENAME=SMITH, JOB=CLERK, MGR=7902, HIREDATE=1980-12-17, SAL=10000.0, COMM=null, DEPTNO=20}
    }

    /*
     * 查询所有记录，将其封装为List
     * 注意：将每一条记录封装为Map集合，再将Map集合装载到List集合中
     * */
    @Test
    public void test5(){
        String sql = "select * from emp";
        List<Map<String, Object>> List = template.queryForList(sql);
//        System.out.println(List);
        for (Map<String, Object> stringObjectMap : List) {
            System.out.println(stringObjectMap);
        }
    }

    /*
     * 6.2 查询所有记录，将其封装为EMP对象的List集合
     *
     * */

//    @Test
//    public void test6(){
//        String sql = "select * from emp";
//        List<Emp> list = template.query(sql, new RowMapper<Emp>() {
//            @Override
//            public Emp mapRow(ResultSet rs, int i) throws SQLException {
//                Emp emp = new Emp();
//                int Empno = rs.getInt("EMPNO");
//                String Ename = rs.getString("ENAME");
//                String Job = rs.getString("JOB");
//                int Mgr =  rs.getInt("MGR");
//                Date HireDate = rs.getDate("HIREDATE");
//                Double Sal =  rs.getDouble("SAL");
//                Double Comm = rs.getDouble("COMM");
//                int Deptno = rs.getInt("DEPTNO");
//
//                emp.setEmpno(Empno);
//                emp.setName(Ename);
//                emp.setJob(Job);
//                emp.setMgr(Mgr);
//                emp.setHiredte(HireDate);
//                emp.setSal(Sal);
//                emp.setComm(Comm);
//                emp.setDeptno(Deptno);
//                return emp;
//            }
//        });
//        for (Emp emp : list) {
//            System.out.println(emp);
//        }
//    }

    /*
     * 查询所有记录，将其封装为JavaBean对象
     * query的参数：RowMapper
     *  一般使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
     * new BeanPropertyRowMapper<类型>(类型.class)
     * */

//    @Test
//    public void test6_2(){
//        String sql = "select * from emp";
//        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
//        for (Emp emp : list) {
//            System.out.println(emp);
//        }
//    }

    /*
     * 查询总记录，将结果封装为对象
     * 一般用于聚合函数
     * */
    @Test
    public void test7(){
        String sql = "select count(EMPNO) from emp";
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);

    }

}
