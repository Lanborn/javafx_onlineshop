package com.NetWorkSaleSystem.view;

import com.NetWorkSaleSystem.bean.Manager;
import com.NetWorkSaleSystem.bean.User;
import com.NetWorkSaleSystem.dao.userDao;
import com.NetWorkSaleSystem.view.customer.MainWindow;
import com.NetWorkSaleSystem.view.manager.MainWindow_m;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./fx/login.fxml"));
        Scene scene=new Scene(root);
        Button loginbutton=(Button)root.lookup("#loginbutton");
        Button loginmanger = (Button)root.lookup("#loginbutton1");
        TextField username = (TextField) root.lookup("#username");
        PasswordField password=(PasswordField)root.lookup("#password");
        loginbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User user=new User();

                user.setUsername(username.getText());
                user.setPassword(password.getText());

                User loginUser = new userDao().findUser(user);

//                System.out.println(loginUser);
                if (loginUser==null){
                    //弹出用户名或密码不正确用户框
                    new Alert(Alert.AlertType.WARNING, "用户名或密码错误！").showAndWait();
                }else {
                    //登录成功,切换画布
                    new Alert(Alert.AlertType.INFORMATION, "登录成功").showAndWait();
                    MainWindow windowShow=new MainWindow(loginUser);
                    scene.setRoot(windowShow);
                }

            }
        });

        loginmanger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Manager manager = new Manager();
                manager.setUsername((username.getText()));
                manager.setPassword(password.getText());
                Manager loginManager = new userDao().findManager(manager);
                System.out.println(loginManager);
                if (loginManager==null){
                    new Alert(Alert.AlertType.WARNING, "用户名或密码错误！").showAndWait();
                }else{
                    new Alert(Alert.AlertType.INFORMATION, "登录成功").showAndWait();
                    MainWindow_m windowShow_m=new MainWindow_m(loginManager);
                    scene.setRoot(windowShow_m);
                }
            }
        });
        root.getStylesheets().add(getClass().getResource("./fx/css/login.css").toExternalForm());

        stage.setScene(scene);
        stage.setWidth(1024);
        stage.setHeight(682);
        stage.setTitle("网络信息销售系统");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}