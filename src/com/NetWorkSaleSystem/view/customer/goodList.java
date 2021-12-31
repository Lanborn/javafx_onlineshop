package com.NetWorkSaleSystem.view.customer;

import com.NetWorkSaleSystem.bean.Good;
import com.NetWorkSaleSystem.bean.User;
import com.NetWorkSaleSystem.bean.shoppingcar;
import com.NetWorkSaleSystem.dao.goodsDao;
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

public class goodList extends AnchorPane {
    private TableView<Good> goodTv=new TableView();
    private User user;
    private List<Good> goods;
    private goodsDao goodDao;

    public goodList(User user){
        this.user= user;
        this.setPrefWidth(736);
        initPane();

    }

    private void initPane() {
        initTableView();
    }

    private void initTableView() {
        goodDao = new goodsDao();
        goods = goodDao.findgoods();
        ObservableList<Good> data = FXCollections.observableArrayList(goods);
        // 每个Table的列
        TableColumn gid = new TableColumn("商品编号");
        gid.setMinWidth(50);
        gid.setCellValueFactory(new PropertyValueFactory<>("gid"));

        TableColumn gname = new TableColumn("商品名称");
        gname.setMinWidth(150);
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

        // 加购按钮

        TableColumn addButtonCol=new TableColumn("加入购物车");
        addButtonCol.setMinWidth(150);
        addButtonCol.setCellFactory(new Callback<TableColumn, TableCell>() {
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
                            Button button=new Button("加购");
                            button.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    PopWindow(goodTv.getItems().get(currentTablerow.getIndex()));
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
        goodTv.setItems(data);
        goodTv.getColumns().addAll(gid,gname,price,manu,num,addButtonCol);
        goodTv.setPrefHeight(400);

        AnchorPane.setTopAnchor(goodTv, 20.0);
        AnchorPane.setLeftAnchor(goodTv, 100.0);

        this.getChildren().addAll(goodTv);
    }

    private void PopWindow(Good good) {
        Stage PopStage=new Stage();
        PopStage.setTitle("添加购物车");
        Label Buynum=new Label("购买数量");
        Buynum.setPrefSize(100,50);
        Buynum.setLayoutX(20);
        Buynum.setLayoutY(100);

        TextField BuynumField=new TextField();
        BuynumField.setPrefSize(250,50);
        BuynumField.setLayoutX(130);
        BuynumField.setLayoutY(100);

        Button button =new Button("确定加购");
        button.setPrefSize(100,40);
        button.setLayoutX(170);
        button.setLayoutY(450);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (new Alert(Alert.AlertType.CONFIRMATION, "确定修改吗?").showAndWait().get().getButtonData() == ButtonBar.ButtonData.OK_DONE)
                {
                    shoppingcar sc = new shoppingcar();
                    sc.setCid(user.getCid());
                    sc.setGid(good.getGid());
                    sc.setNum(Integer.valueOf(BuynumField.getText()));
                    int flag = goodDao.insertshopcar(sc);
                    if(flag>0){
                        new Alert(Alert.AlertType.INFORMATION,"加购成功！").showAndWait();
                        goodTv.refresh();
                        PopStage.close();
                    }
                    else{
                        new Alert(Alert.AlertType.ERROR,"加购失败！").showAndWait();
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
        pane.getChildren().addAll(Buynum,BuynumField,button);
        Scene scene=new Scene(pane);
        PopStage.setScene(scene);
        PopStage.setHeight(600);
        PopStage.setWidth(400);
        PopStage.show();
    }
}
