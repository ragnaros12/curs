package com.cursovay.curs.ui.main;

import com.cursovay.curs.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    public Button Contracts;
    public Button Employees;
    public Button Division;
    public Button Position;

    @FXML
    public void onPositionClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/position.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Position");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onTypeClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/type_contract.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Type");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onKindClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/kind_contract.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Kind contract");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onEmployeeClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/employee.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Employee");
            stage.setScene(new Scene(root, 1200, 700));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onContractsClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/contract.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Contract");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onDivisionClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/division.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Division");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}