package com.NetWorkSaleSystem.view.manager;

import com.NetWorkSaleSystem.bean.Good;
import com.NetWorkSaleSystem.bean.Staff;
import com.NetWorkSaleSystem.dao.StaffDao;
import com.NetWorkSaleSystem.dao.goodsDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class staffList extends AnchorPane {
    private TableView<Staff> staffTv=new TableView();
    private List<Staff> staffs;
    private StaffDao staffDao;

    public staffList(){
        this.setPrefWidth(736);
        initPane();

    }

    private void initPane() {
        initTableView();
    }

    private void initTableView() {
        staffDao = new StaffDao();
        staffs = staffDao.findStaffs();
        ObservableList<Staff> data = FXCollections.observableArrayList(staffs);

        TableColumn sid = new TableColumn("员工编号");
        sid.setMinWidth(50);
        sid.setCellValueFactory(new PropertyValueFactory<>("sid"));

        TableColumn name = new TableColumn("姓名");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn sex = new TableColumn("性别");
        sex.setMinWidth(50);
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));

        TableColumn dob = new TableColumn("生日");
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        dob.setMinWidth(100);

        TableColumn phone= new TableColumn("电话");
        phone.setMinWidth(100);
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn IDcard = new TableColumn("身份证");
        IDcard.setMinWidth(100);
        IDcard.setCellValueFactory(new PropertyValueFactory<>("IDcard"));

        TableColumn username = new TableColumn("用户名");
        username.setMinWidth(100);
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        staffTv.setItems(data);
        staffTv.getColumns().addAll(sid,name,sex,dob,phone,IDcard,username);
        staffTv.setPrefHeight(550);

        AnchorPane.setTopAnchor(staffTv, 20.0);
        AnchorPane.setLeftAnchor(staffTv, 20.0);

        this.getChildren().addAll(staffTv);
    }
}
