package com.NetWorkSaleSystem.bean;

public class Good {
    private Integer gid;
    private String name;
    private Double price;
    private String manu;
    private Integer num;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Good{" +
                "gid=" + gid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", manu='" + manu + '\'' +
                ", num=" + num +
                '}';
    }
}
