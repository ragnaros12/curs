package com.cursovay.curs;

import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.daos.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class HelloApplication extends Application {
    static Connection sqlServer;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException, SQLException {

        sqlServer = DriverManager.getConnection("jdbc:postgresql://localhost:5432/xxxx", "postgres", "root");
        DaoManager.getInstance().addDao(new NationalityDao(sqlServer));
        DaoManager.getInstance().addDao(new TypeContractDao(sqlServer));
        DaoManager.getInstance().addDao(new KindContractDao(sqlServer));
        DaoManager.getInstance().addDao(new DivisionDao(sqlServer));
        DaoManager.getInstance().addDao(new PositionDao(sqlServer));
        DaoManager.getInstance().addDao(new EmployeeDao(sqlServer));
        DaoManager.getInstance().addDao(new ContractDao(sqlServer));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Main window");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}