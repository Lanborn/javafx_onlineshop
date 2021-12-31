package com.NetWorkSaleSystem.view.manager;

import com.NetWorkSaleSystem.bean.Good;
import com.NetWorkSaleSystem.bean.SaleOrder;
import com.NetWorkSaleSystem.dao.saleOrderDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class saleorder extends AnchorPane {
    private TableView<SaleOrder> saleorderTv=new TableView();
    private List<SaleOrder> saleorders;
    private saleOrderDao saleOrderDao;

    public saleorder(){
        this.setPrefWidth(736);
        initPane();
    }

    private void initPane() {
        initTableView();
    }

    private void initTableView() {
        saleOrderDao = new saleOrderDao();
        saleorders = saleOrderDao.findSaleOrder();

        ObservableList<SaleOrder> data = FXCollections.observableArrayList(saleorders);

        TableColumn cname = new TableColumn("顾客姓名");
        cname.setMinWidth(50);
        cname.setCellValueFactory(new PropertyValueFactory<>("cname"));

        TableColumn gname = new TableColumn("商品名称");
        gname.setMinWidth(100);
        gname.setCellValueFactory(new PropertyValueFactory<>("gname"));

        TableColumn sname = new TableColumn("营业员姓名");
        sname.setMinWidth(50);
        sname.setCellValueFactory(new PropertyValueFactory<>("sname"));

        TableColumn num = new TableColumn("商品数量");
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        num.setMinWidth(100);

        saleorderTv.setItems(data);
        saleorderTv.getColumns().addAll(cname,sname,gname,num);
        saleorderTv.setPrefHeight(400);

        AnchorPane.setTopAnchor(saleorderTv, 20.0);
        AnchorPane.setLeftAnchor(saleorderTv, 20.0);

        this.getChildren().addAll(saleorderTv);
    }
}
