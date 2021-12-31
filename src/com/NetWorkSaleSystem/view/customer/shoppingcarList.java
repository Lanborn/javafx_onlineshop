package com.NetWorkSaleSystem.view.customer;

import com.NetWorkSaleSystem.bean.Good;
import com.NetWorkSaleSystem.bean.Order;
import com.NetWorkSaleSystem.bean.User;
import com.NetWorkSaleSystem.bean.shoppingcar;
import com.NetWorkSaleSystem.dao.shoppingcarDao;
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

public class shoppingcarList extends AnchorPane {
    private TableView<shoppingcar> scTv = new TableView();
    private User user;
    private List<shoppingcar> shoppingCars;
    private shoppingcarDao scDao;

    public shoppingcarList(User user){
        this.user = user;
        this.setPrefWidth(736);
        initPane();
    }

    private void initPane() {
        initTableView();
    }

    private void initTableView() {
        scDao = new shoppingcarDao();
        shoppingCars = scDao.findCar(user);
        System.out.println(shoppingCars);
        ObservableList<shoppingcar> data = FXCollections.observableArrayList(shoppingCars);

        // 每个Table的列
        TableColumn id = new TableColumn("购物车编号");
        id.setMinWidth(50);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn gname = new TableColumn("商品名称");
        gname.setMinWidth(150);
        gname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("商品价格");
        price.setMinWidth(50);
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



        TableColumn num = new TableColumn("购买数量");
        num.setMinWidth(50);
        num.setCellValueFactory(new PropertyValueFactory<>("num"));

        TableColumn allprice = new TableColumn("总价");
        allprice.setCellValueFactory(new PropertyValueFactory<>("allprice"));
        allprice.setMinWidth(100);

        // 购物车编辑
        TableColumn updateButtonCol=new TableColumn("支付");
        updateButtonCol.setMinWidth(75);
        updateButtonCol.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                TableCell cell=new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        TableRow currentTablerow=this.getTableRow();
                        super.updateItem(item, empty);
                        if (empty==false&&item==null){
                            HBox hBox=new HBox();
                            hBox.setAlignment(Pos.CENTER);
                            Button button=new Button("支付");
                            button.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    PopWindow(scTv.getItems().get(currentTablerow.getIndex()));
                                }
                            });
                            hBox.getChildren().add(button);
                            this.setGraphic(hBox);
                        }else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });

        // 购物车删除
        TableColumn daleteButtonCol=new TableColumn("删除");
        daleteButtonCol.setMinWidth(75);
        daleteButtonCol.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                TableCell cell=new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        TableRow currentTablerow=this.getTableRow();
                        if (empty==false){
                            Button button=new Button("删除");
                            button.setStyle("-fx-background-color: #ff5e3c;-fx-text-fill: #ffffff");
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"确认删除该信息吗？");
                                    if (alert.showAndWait().get().getButtonData()==ButtonBar.ButtonData.OK_DONE){

                                        if (scDao.delScShop( scTv.getItems().get(currentTablerow.getIndex()))>0){
                                            new Alert(Alert.AlertType.INFORMATION,"删除成功！").showAndWait();
                                            scTv.getItems().remove(currentTablerow.getIndex());
                                        }else {
                                            Alert warning=new Alert(Alert.AlertType.WARNING,"系统异常请稍后再试");
                                            warning.showAndWait();
                                        }
                                    }
                                }
                            });
                            setAlignment(Pos.CENTER);
                            this.setGraphic(button);
                            setText(null);
                        }else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });

        scTv.setItems(data);
        scTv.getColumns().addAll(id,gname,price,num,allprice,updateButtonCol,daleteButtonCol);
        scTv.setPrefHeight(550);
        AnchorPane.setTopAnchor(scTv, 20.0);
        AnchorPane.setLeftAnchor(scTv, 40.0);


        this.getChildren().addAll(scTv);

    }

    private void PopWindow(shoppingcar shoppingcar) {
        Stage PopStage=new Stage();
        PopStage.setTitle("支付界面");
        Label address=new Label("收获地址");
        Label name=new Label("联系人");
        Label phone=new Label("电话");
        address.setPrefSize(100,30);
        name.setPrefSize(100,30);
        phone.setPrefSize(100,30);
        address.setLayoutX(20);
        name.setLayoutX(20);
        phone.setLayoutX(20);
        address.setLayoutY(50);
        name.setLayoutY(150);
        phone.setLayoutY(280);

        TextField addressField = new TextField();
        TextField nameField=new TextField();
        TextField phoneField=new TextField();

        addressField.setPrefSize(150,30);
        nameField.setPrefSize(150,30);
        phoneField.setPrefSize(150,30);

        addressField.setLayoutX(130);
        phoneField.setLayoutX(130);
        nameField.setLayoutX(130);

        addressField.setLayoutY(50);
        nameField.setLayoutY(150);
        phoneField.setLayoutY(280);

        Button button =new Button("确定");
        button.setPrefSize(60,40);
        button.setLayoutX(170);
        button.setLayoutY(450);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (new Alert(Alert.AlertType.CONFIRMATION,"确定修改吗?").showAndWait().get().getButtonData()==ButtonBar.ButtonData.OK_DONE)
                {
                    Order order = new Order();
                    order.setCid(user.getCid());
                    order.setSpid(shoppingcar.getId());
                    order.setAddress(addressField.getText());
                    order.setName(nameField.getText());
                    order.setPhone(phoneField.getText());
                    if (scDao.toPay(order)>0){
                        new Alert(Alert.AlertType.INFORMATION,"支付成功！").showAndWait();
                        scTv.refresh();
                        PopStage.close();
                    }
                }else {
                    Alert warning=new Alert(Alert.AlertType.WARNING,"系统异常请稍后再试");
                    warning.showAndWait();
                    PopStage.close();
                }
            }
        });
        Pane pane=new Pane();
        pane.setStyle("-fx-background-color: lightblue");
        pane.getChildren().addAll(address,name,phone,addressField,nameField,phoneField,button);
        Scene scene=new Scene(pane);
        PopStage.setScene(scene);
        PopStage.setHeight(600);
        PopStage.setWidth(400);
        PopStage.show();


    }

}
