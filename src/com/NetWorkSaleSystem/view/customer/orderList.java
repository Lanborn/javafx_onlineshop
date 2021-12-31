package com.NetWorkSaleSystem.view.customer;

import com.NetWorkSaleSystem.bean.Order;
import com.NetWorkSaleSystem.bean.User;
import com.NetWorkSaleSystem.bean.shoppingcar;
import com.NetWorkSaleSystem.dao.OrderDao;
import com.NetWorkSaleSystem.dao.shoppingcarDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class orderList extends AnchorPane {
    private TableView<Order> orderTv = new TableView();
    private User user;
    private List<Order> Orders;
    private OrderDao orderDao;
    public orderList(User user){
        this.user=user;
        this.setPrefWidth(736);
        initPane();
    }

    private void initPane() {
        initTableView();
    }

    private void initTableView() {
        orderDao = new OrderDao();
        Orders = orderDao.findOrder(user);
        ObservableList<Order> data = FXCollections.observableArrayList(Orders);

        // 每个Table的列
        TableColumn id = new TableColumn("订单编号");
        id.setMinWidth(50);
        id.setCellValueFactory(new PropertyValueFactory<>("uuid"));

        TableColumn gname = new TableColumn("联系人");
        gname.setMinWidth(150);
        gname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn address = new TableColumn("收货地址");
        address.setMinWidth(50);
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn phone = new TableColumn("电话");
        phone.setMinWidth(50);
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn spid = new TableColumn("购物车编号");
        spid.setCellValueFactory(new PropertyValueFactory<>("spid"));
        spid.setMinWidth(100);

        orderTv.setItems(data);
        orderTv.getColumns().addAll(id,gname,address,phone,spid);
        orderTv.setPrefHeight(400);
        AnchorPane.setTopAnchor(orderTv, 20.0);
        AnchorPane.setLeftAnchor(orderTv, 100.0);


        this.getChildren().addAll(orderTv);
    }
}
