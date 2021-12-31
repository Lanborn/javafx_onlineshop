package com.NetWorkSaleSystem.view.customer;

import com.NetWorkSaleSystem.bean.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends BorderPane {
    private AnchorPane topAnchorPane;
    private AnchorPane leftAnchorPane;
    private AnchorPane rightanchorPan;
    private User user;
    public MainWindow(User user){
        this.user=user;
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
        Text right=new Text("欢迎来到LanBorn商城！");
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

        TreeItem<String> store=new TreeItem<>("商城");
        TreeItem<String> goodlist = new TreeItem<> ("商品列表");

        TreeItem<String> shoppingcar=new TreeItem<>("购物车");

        //3.投资方
        TreeItem<String> orderform=new TreeItem<>("订单列表");

        store.getChildren().add(goodlist);
        store.getChildren().add(shoppingcar);
        store.getChildren().add(orderform);
        TreeView<String> tree = new TreeView<>(store);
        tree.setShowRoot(false);
        tree.setPrefSize(270,600);
        leftAnchorPane.getChildren().add(tree);

        tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    TreeItem<String>item =tree.getSelectionModel().getSelectedItem();
                    if(item.isLeaf()){
                        if(item.getValue().equals("商品列表")){
                            setRight(new goodList(user));
                        }
                        if(item.getValue().equals("购物车")){
                            setRight(new shoppingcarList(user));
                        }
                        if(item.getValue().equals("订单列表")){
                            setRight(new orderList(user));
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
