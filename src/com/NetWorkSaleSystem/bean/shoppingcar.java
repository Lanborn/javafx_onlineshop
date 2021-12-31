package com.NetWorkSaleSystem.bean;

public class shoppingcar {
    private Integer id;
    private String name;
    private Double price;
    private Integer num;
    private Integer cid;
    private Double allprice;
    private Integer gid;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Double getAllprice() {
        return allprice;
    }

    public void setAllprice(Double allprice) {
        this.allprice = allprice;
    }

    @Override
    public String toString() {
        return "shoppingcar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", cid=" + cid +
                ", allprice=" + allprice +
                ", gid=" + gid +
                '}';
    }
}
