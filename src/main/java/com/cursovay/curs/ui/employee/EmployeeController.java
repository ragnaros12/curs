package com.cursovay.curs.ui.employee;

import com.cursovay.curs.HelloApplication;
import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.Employee;
import com.cursovay.curs.core.model.Position;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    public TableView<Employee> Table;
    public TableColumn<Employee, String> idFio;
    public TableColumn<Employee, String> idInn;
    public TableColumn<Employee, String> idKpp;
    public TableColumn<Employee, String> idPassport;
    public TableColumn<Employee, String> idPhone;
    public TableColumn<Employee, String> idNationality;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // устанавливаем тип и значение которое должно хранится в колонке
        idFio.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFirstName() + " " + cell.getValue().getSecondName() + " " + cell.getValue().getLastName()));
        idInn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getInn()));
        idKpp.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getKpp()));
        idPassport.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPassport() + " " + cell.getValue().getWhoGet() + " " + cell.getValue().getDateIssue().toString()));
        idPhone.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPhone()));
        idNationality.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNationality().getFullNationality()));


        // заполняем таблицу данными
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Employee.class).getAll()
        ));
    }

    @FXML
    public void onAddClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/add_employee.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Add employee");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Table.setItems(FXCollections.observableList(
                    DaoManager.getInstance().findDao(Employee.class).getAll()
            ));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRemoveClicked(MouseEvent event) {
        Employee pos = Table.getSelectionModel().getSelectedItem();
        if (pos == null)
            return;
        if (!DaoManager.getInstance().findDao(Employee.class).Remove(pos.getId())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.showAndWait();
            return;
        }
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Employee.class).getAll()
        ));
    }
}
