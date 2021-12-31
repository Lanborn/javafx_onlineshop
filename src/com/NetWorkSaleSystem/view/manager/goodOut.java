package com.NetWorkSaleSystem.view.manager;

import com.NetWorkSaleSystem.JDBCUtils.JDBCUtils;
import com.NetWorkSaleSystem.bean.CheckOut;
import com.NetWorkSaleSystem.bean.Good;
import com.NetWorkSaleSystem.bean.Manager;
import com.NetWorkSaleSystem.bean.Staff;
import com.NetWorkSaleSystem.dao.checkOutDao;
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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.List;

public class goodOut extends AnchorPane {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    private TableView<CheckOut> checkoutTv=new TableView();
    private List<CheckOut> checkOuts;
    private checkOutDao checkDao;
    private Manager manager;

    public goodOut(Manager manager){
        this.manager = manager;
        this.setPrefWidth(736);
        initPane();
    }

    private void initPane() {
        initTableView();
    }

    private void initTableView() {
        checkDao = new checkOutDao();
        checkOuts = checkDao.findCheck();
        ObservableList<CheckOut> data = FXCollections.observableArrayList(checkOuts);

        TableColumn uuid = new TableColumn("支付编号");
        uuid.setMinWidth(50);
        uuid.setCellValueFactory(new PropertyValueFactory<>("uuid"));

        TableColumn name = new TableColumn("收货人");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn address = new TableColumn("收货地址");
        address.setMinWidth(100);
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn phone = new TableColumn("电话");
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phone.setMinWidth(100);

        TableColumn cid = new TableColumn("顾客编号");
        cid.setMinWidth(50);
        cid.setCellValueFactory(new PropertyValueFactory<>("cid"));

        TableColumn gname = new TableColumn("商品名称");
        gname.setMinWidth(100);
        gname.setCellValueFactory(new PropertyValueFactory<>("gname"));

        TableColumn num = new TableColumn("商品数量");
        num.setMinWidth(50);
        num.setCellValueFactory(new PropertyValueFactory<>("num"));

        TableColumn editButtonCol = new TableColumn("商品出库");
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
                            Button button = new Button("出库");
                            button.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    PopWindow(checkoutTv.getItems().get(currentTablerow.getIndex()));
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
        checkoutTv.setItems(data);
        checkoutTv.getColumns().addAll(uuid,name,address,phone,cid,gname,editButtonCol);
        checkoutTv.setPrefHeight(400);

        AnchorPane.setTopAnchor(checkoutTv, 20.0);
        AnchorPane.setLeftAnchor(checkoutTv, 20.0);

        this.getChildren().addAll(checkoutTv);
    }

    public void PopWindow(CheckOut checkOut){
        Stage PopStage=new Stage();
        PopStage.setTitle("修改商品信息");

        Label uuid=new Label("支付编号");
        uuid.setPrefSize(100,50);
        uuid.setLayoutX(20);
        uuid.setLayoutY(50);

        TextField uuidField=new TextField();
        uuidField.setPrefSize(150,50);
        uuidField.setLayoutX(130);
        uuidField.setLayoutY(50);

        uuidField.setText(String.valueOf(checkOut.getUuid()));
        Button button =new Button("确定出库");
        button.setPrefSize(100,40);
        button.setLayoutX(170);
        button.setLayoutY(450);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection dbConn = null;
                String dbURL = "jdbc:mysql://localhost:3306/onlineshop?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
                try {

                //1.加载驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.连接
                dbConn = DriverManager.getConnection(dbURL, "root", "123123");
                dbConn.createStatement();

                String sql = "insert into saleorder(cname,gname,sname,num)"+"value(?,?,?,?)";
                PreparedStatement ps = dbConn.prepareStatement(sql);
                String sql1="select * from pay_view where uuid='"+uuidField.getText()+"'";
                PreparedStatement ps1 = dbConn.prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery();
                while(rs1.next()){
                    ps.setString(1,rs1.getString(3));
                    ps.setString(2,rs1.getString(2));
                    ps.setString(4,rs1.getString(4));

                    String sql2="select name from staff where sid='"+manager.getSid()+"'";
                    PreparedStatement ps2 = dbConn.prepareStatement(sql2);
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        ps.setString(3,rs2.getString(1));
                    }
                    //更新商品库存数量
                    String sql4="update goods set num=num-'"+rs1.getInt(4)+"'  where gid='"+rs1.getInt(5)+"'";
                    PreparedStatement ps4 = dbConn.prepareStatement(sql4);
                    ps4.executeUpdate();
                    ps.executeUpdate();
                }
                //出库成功删除订单中的数据
                String sql3="delete from orders where uuid='"+uuidField.getText()+"'";
                PreparedStatement ps3 = dbConn.prepareStatement(sql3);
                int i = ps3.executeUpdate();
                if(i!=0){
                    new Alert(Alert.AlertType.INFORMATION,"出库成功").showAndWait();
                }
            }catch(Exception a) {
                a.printStackTrace();
                System.out.println("连接数据库失败！");
            }finally {
                try {
                    dbConn.close();
                } catch (SQLException c) {
                    c.printStackTrace();
                }
                checkoutTv.refresh();
                PopStage.close();
            }
            }
        });
        Pane pane=new Pane();
        pane.setStyle("-fx-background-color: lightblue");
        pane.getChildren().addAll(uuid,uuidField,button);
        Scene scene=new Scene(pane);
        PopStage.setScene(scene);
        PopStage.setHeight(600);
        PopStage.setWidth(400);
        PopStage.show();
    }

}
