package com.NetWorkSaleSystem.view.manager;

import com.NetWorkSaleSystem.bean.Good;
import com.NetWorkSaleSystem.bean.User;
import com.NetWorkSaleSystem.bean.shoppingcar;
import com.NetWorkSaleSystem.dao.goodsDao;
import com.mysql.cj.xdevapi.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class goodManager extends AnchorPane {
    private TableView<Good> goodTv=new TableView();
    private List<Good> goods;
    private goodsDao goodDao;

    public goodManager(){
        this.setPrefWidth(736);
        initPane();
    }

    private void initPane() {
        initTableView();
        Button addButton  = new Button("添加商品");
        addButton.setPrefSize(200,40);
        addButton.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
        setTopAnchor(addButton,490.0);
        setLeftAnchor(addButton,300.0);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addWindow();
            }
        });
        this.getChildren().addAll(addButton);
    }

    private void initTableView() {
        goodDao = new goodsDao();
        goods = goodDao.findgoods();
        ObservableList<Good> data = FXCollections.observableArrayList(goods);
        // 每个Table的列
        TableColumn gid = new TableColumn("员工编号");
        gid.setMinWidth(50);
        gid.setCellValueFactory(new PropertyValueFactory<>("gid"));

        TableColumn gname = new TableColumn("商品名称");
        gname.setMinWidth(100);
        gname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("商品价格");
        price.setMinWidth(50);
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn manu = new TableColumn("生产厂家");
        manu.setCellValueFactory(new PropertyValueFactory<>("manu"));
        manu.setMinWidth(100);

        TableColumn num = new TableColumn("商品数量");
        num.setMinWidth(100);
        num.setCellValueFactory(new PropertyValueFactory<>("num"));

        TableColumn editButtonCol = new TableColumn("编辑商品");
        editButtonCol.setMinWidth(70);
        editButtonCol.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                TableCell cell = new TableCell() {
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        TableRow currentTablerow = this.getTableRow();
                        super.updateItem(item, empty);
                        if (empty == false && item == null) {
                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.CENTER);
                            Button button = new Button("编辑");
                            button.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    PopWindow(goodTv.getItems().get(currentTablerow.getIndex()));
                                }
                            });
                            hBox.getChildren().add(button);
                            this.setGraphic(hBox);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });

        TableColumn delButtonCol=new TableColumn("下架商品");
        delButtonCol.setMinWidth(70);
        delButtonCol.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                TableCell cell = new TableCell() {
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        TableRow currentTablerow = this.getTableRow();
                        super.updateItem(item, empty);
                        if (empty == false && item == null) {
                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.CENTER);
                            Button button = new Button("下架");
                            button.setStyle("-fx-background-color: red;-fx-text-fill: #ffffff");
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
//                                    PopWindow(goodTv.getItems().get(currentTablerow.getIndex()));
                                    if(goodDao.delGood( goodTv.getItems().get(currentTablerow.getIndex()))>0){
                                        new Alert(Alert.AlertType.INFORMATION,"删除成功！").showAndWait();
                                        goodTv.getItems().remove(currentTablerow.getIndex());
                                    }
                                }
                            });
                            hBox.getChildren().add(button);
                            this.setGraphic(hBox);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });

//

        goodTv.setItems(data);
        goodTv.getColumns().addAll(gid,gname,price,manu,num,editButtonCol,delButtonCol);
        goodTv.setPrefHeight(400);

        AnchorPane.setTopAnchor(goodTv, 20.0);
        AnchorPane.setLeftAnchor(goodTv, 20.0);

        this.getChildren().addAll(goodTv);
    }

    private void addWindow(){
        Stage PopStage=new Stage();
        PopStage.setTitle("修改商品信息");
        Label name=new Label("商品名称");
        name.setPrefSize(100,20);
        name.setLayoutX(20);
        name.setLayoutY(50);
        Label price = new Label("商品价格");
        price.setPrefSize(100,20);
        price.setLayoutX(20);
        price.setLayoutY(100);
        Label manu = new Label("生产厂家");
        manu.setPrefSize(100,20);
        manu.setLayoutX(20);
        manu.setLayoutY(150);
        Label num = new Label("商品库存");
        num.setPrefSize(100,20);
        num.setLayoutX(20);
        num.setLayoutY(200);


        TextField nameField=new TextField();
        nameField.setPrefSize(150,20);
        nameField.setLayoutX(130);
        nameField.setLayoutY(50);
        TextField priceField=new TextField();
        priceField.setPrefSize(150,30);
        priceField.setLayoutX(130);
        priceField.setLayoutY(100);
        TextField manuField = new TextField();
        manuField.setPrefSize(150,30);
        manuField.setLayoutX(130);
        manuField.setLayoutY(150);

        TextField numField = new TextField();
        numField.setPrefSize(150,30);
        numField.setLayoutX(130);
        numField.setLayoutY(200);

        Button button =new Button("确定添加");
        button.setPrefSize(100,40);
        button.setLayoutX(170);
        button.setLayoutY(450);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Good good = new Good();
                good.setName(nameField.getText());
                good.setPrice(Double.valueOf(priceField.getText()));
                good.setManu(manuField.getText());
                good.setNum(Integer.valueOf(numField.getText()));

                int i = goodDao.addGood(good);
                if(i>0){
                    new Alert(Alert.AlertType.INFORMATION,"添加成功").showAndWait();
                    goodTv.getItems().clear();
                    goodTv.getItems().addAll(FXCollections.observableArrayList(goodDao.findgoods()));
                    goodTv.refresh();
                    PopStage.close();
                }
                else{
                    new Alert(Alert.AlertType.ERROR,"添加失败！").showAndWait();
                    goodTv.refresh();
                    PopStage.close();
                }
            }
        });
        Pane pane=new Pane();
        pane.setStyle("-fx-background-color: lightblue");
        pane.getChildren().addAll(name,price,manu,num,nameField,priceField,manuField,numField,button);
        Scene scene=new Scene(pane);
        PopStage.setScene(scene);
        PopStage.setHeight(600);
        PopStage.setWidth(400);
        PopStage.show();

    }

    private void PopWindow(Good good) {
        Stage PopStage=new Stage();
        PopStage.setTitle("修改商品信息");
        Label name=new Label("商品名称");
        name.setPrefSize(100,20);
        name.setLayoutX(20);
        name.setLayoutY(50);
        Label price = new Label("商品价格");
        price.setPrefSize(100,20);
        price.setLayoutX(20);
        price.setLayoutY(100);
        Label num = new Label("商品库存");
        num.setPrefSize(100,20);
        num.setLayoutX(20);
        num.setLayoutY(150);

        TextField nameField=new TextField();
        nameField.setPrefSize(150,20);
        nameField.setLayoutX(130);
        nameField.setLayoutY(50);
        TextField priceField=new TextField();
        priceField.setPrefSize(150,30);
        priceField.setLayoutX(130);
        priceField.setLayoutY(100);
        TextField numField = new TextField();
        numField.setPrefSize(150,30);
        numField.setLayoutX(130);
        numField.setLayoutY(150);

        Button button =new Button("确定修改");
        button.setPrefSize(100,40);
        button.setLayoutX(170);
        button.setLayoutY(450);
        nameField.setText(good.getName());
        priceField.setText(String.valueOf(good.getPrice()));
        numField.setText(String.valueOf(good.getNum()));

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (new Alert(Alert.AlertType.CONFIRMATION, "确定修改吗?").showAndWait().get().getButtonData() == ButtonBar.ButtonData.OK_DONE)
                {

                    good.setName(nameField.getText());
                    good.setPrice(Double.valueOf(priceField.getText()));
                    good.setNum(Integer.valueOf(numField.getText()));
                    int flag = goodDao.updateGood(good);
                    if(flag>0){
                        new Alert(Alert.AlertType.INFORMATION,"修改成功！").showAndWait();
                        goodTv.refresh();
                        PopStage.close();
                    }
                    else{
                        new Alert(Alert.AlertType.ERROR,"修改失败！").showAndWait();
                        goodTv.refresh();
                        PopStage.close();
                    }
                }else {
                    Alert warning = new Alert(Alert.AlertType.WARNING, "系统异常请稍后再试");
                    warning.showAndWait();
                    PopStage.close();
                }
            }
        });

        Pane pane=new Pane();
        pane.setStyle("-fx-background-color: lightblue");
        pane.getChildren().addAll(name,price,num,nameField,priceField,numField,button);
        Scene scene=new Scene(pane);
        PopStage.setScene(scene);
        PopStage.setHeight(600);
        PopStage.setWidth(400);
        PopStage.show();
    }
}
