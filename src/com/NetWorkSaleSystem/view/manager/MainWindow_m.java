package com.NetWorkSaleSystem.view.manager;

import com.NetWorkSaleSystem.bean.Manager;
import com.NetWorkSaleSystem.bean.User;
import com.NetWorkSaleSystem.view.customer.goodList;
import com.NetWorkSaleSystem.view.customer.orderList;
import com.NetWorkSaleSystem.view.customer.shoppingcarList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sun.reflect.generics.tree.Tree;

public class MainWindow_m extends BorderPane {
    private AnchorPane topAnchorPane;
    private AnchorPane leftAnchorPane;
    private AnchorPane rightanchorPan;
    private Manager manager;
    public MainWindow_m(Manager manager){
        this.manager=manager;
        initWindow();
    }

    private void initWindow() {
        //设置头部
        topAnchorPane=new AnchorPane();
        topAnchorPane.setStyle("-fx-background-color: #008c8c");
        topAnchorPane.setPrefHeight(70);
        topAnchorPane.setPrefWidth(1024);
        Text title=new Text("网络销售信息系统");
        title.setFont(new Font(40));
        title.setStyle("-fx-fill: rgb(255,255,255);");
        AnchorPane.setTopAnchor(title, 10.0);
        AnchorPane.setLeftAnchor(title, 350.0);
        topAnchorPane.getChildren().add(title);
        setTop(topAnchorPane);
        //设置左边
        leftAnchorPane=new AnchorPane();
        leftAnchorPane.setStyle("-fx-background-color: skyblue");
        leftAnchorPane.setPrefWidth(270);
        setLeft(leftAnchorPane);
        //设置右边
        rightanchorPan=new AnchorPane();
        rightanchorPan.setStyle("-fx-background-color: lightyellow");
        rightanchorPan.setPrefWidth(750);
        Text right=new Text("欢迎来到后台管理系统！");
        right.setFont(new Font(37));
        right.setStyle("-fx-fill: #1e88e5;");
        AnchorPane.setTopAnchor(right, 200.0);
        AnchorPane.setLeftAnchor(right, 150.0);
        rightanchorPan.getChildren().addAll(right);
        setRight(rightanchorPan);


        //设置底边
        AnchorPane bottomAnchorPane=new AnchorPane();
        bottomAnchorPane.setPrefHeight(0);
        bottomAnchorPane.setPrefWidth(1024);
        setBottom(bottomAnchorPane);

        //初始化相关组件
        initleftAnchorPane();
    }


    private void initleftAnchorPane() {

        TreeItem<String> storeManager=new TreeItem<>("商城");
        TreeItem<String> goodmanager = new TreeItem<> ("商品管理");


        TreeItem<String> orderform=new TreeItem<>("订单管理");
        TreeItem<String> goodout = new TreeItem<>("商品出库");
        TreeItem<String> orderdisplay = new TreeItem<>("销售清单");
        orderform.getChildren().addAll(goodout,orderdisplay);

        TreeItem<String> peopleList = new TreeItem<>("人员列表");

        TreeItem<String> staffInfoList = new TreeItem<>("员工列表");

        TreeItem<String> customerList = new TreeItem<>("顾客列表");
        peopleList.getChildren().addAll(staffInfoList,customerList);

        storeManager.getChildren().addAll(goodmanager,orderform,peopleList);
        TreeView<String> tree = new TreeView<>(storeManager);
        tree.setShowRoot(false);
        tree.setPrefSize(270,600);
        leftAnchorPane.getChildren().add(tree);

        tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    TreeItem<String>item =tree.getSelectionModel().getSelectedItem();
                    if(item.isLeaf()){
                        if(item.getValue().equals("商品管理")){
                            setRight(new goodManager());
                        }
                        if(item.getValue().equals("商品出库")){
                            setRight(new goodOut(manager));
                        }
                        if(item.getValue().equals("销售清单")){
                            setRight(new saleorder());
                        }
                        if(item.getValue().equals("员工列表")){
                            setRight(new staffList());
                        }
                        if(item.getValue().equals("顾客列表")){
                            setRight(new customerList());
                        }
                    }
                }
            }
        });
    }

    public AnchorPane getTopAnchorPane() {
        return topAnchorPane;
    }

    public void setTopAnchorPane(AnchorPane topAnchorPane) {
        this.topAnchorPane = topAnchorPane;
    }

    public AnchorPane getLeftAnchorPane() {
        return leftAnchorPane;
    }

    public void setLeftAnchorPane(AnchorPane leftAnchorPane) {
        this.leftAnchorPane = leftAnchorPane;
    }

    public AnchorPane getRightanchorPan() {
        return rightanchorPan;
    }

    public void setRightanchorPan(AnchorPane rightanchorPan) {
        this.rightanchorPan = rightanchorPan;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "MainWindow_m{" +
                "topAnchorPane=" + topAnchorPane +
                ", leftAnchorPane=" + leftAnchorPane +
                ", rightanchorPan=" + rightanchorPan +
                ", manager=" + manager +
                '}';
    }
}
