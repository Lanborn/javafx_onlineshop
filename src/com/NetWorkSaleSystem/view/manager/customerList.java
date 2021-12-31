package com.NetWorkSaleSystem.view.manager;

import com.NetWorkSaleSystem.bean.Staff;
import com.NetWorkSaleSystem.bean.User;
import com.NetWorkSaleSystem.dao.StaffDao;
import com.NetWorkSaleSystem.dao.userDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class customerList extends AnchorPane {
    private TableView<User> customerTv=new TableView();
    private List<User> customers;
    private userDao customerDao;

    public customerList(){
        this.setPrefWidth(736);
        initPane();
    }

    private void initPane() {
        initTableView();
    }

    private void initTableView() {
        customerDao = new userDao();
        customers = customerDao.findUsersList();
        ObservableList<User> data = FXCollections.observableArrayList(customers);

        TableColumn cid = new TableColumn("顾客编号");
        cid.setMinWidth(50);
        cid.setCellValueFactory(new PropertyValueFactory<>("cid"));

        TableColumn name = new TableColumn("姓名");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn phone= new TableColumn("电话");
        phone.setMinWidth(100);
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn IDcard = new TableColumn("身份证");
        IDcard.setMinWidth(100);
        IDcard.setCellValueFactory(new PropertyValueFactory<>("IDcard"));

        TableColumn username = new TableColumn("用户名");
        username.setMinWidth(100);
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        customerTv.setItems(data);
        customerTv.getColumns().addAll(cid,name,phone,IDcard,username);
        customerTv.setPrefHeight(550);

        AnchorPane.setTopAnchor(customerTv, 20.0);
        AnchorPane.setLeftAnchor(customerTv, 20.0);

        this.getChildren().addAll(customerTv);
    }


}
