package com.NetWorkSaleSystem.bean;

import sun.rmi.server.InactiveGroupException;

public class SaleOrder {
    private String cname;
    private String gname;
    private String sname;
    private Integer num;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SaleOrder{" +
                "cname='" + cname + '\'' +
                ", gname='" + gname + '\'' +
                ", sname='" + sname + '\'' +
                ", num=" + num +
                '}';
    }
}
