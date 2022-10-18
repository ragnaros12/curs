package com.cursovay.curs.ui.division;

import com.cursovay.curs.HelloApplication;
import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.Division;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DivisionController implements Initializable {
    public TableColumn<Division, String> idDivision;
    public TableView<Division> Table;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // устанавливаем тип и значение которое должно хранится в колонке
        idDivision.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDivision()));

        // заполняем таблицу данными
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Division.class).getAll()
        ));
    }

    @FXML
    public void onAddClicked(MouseEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Введите подразделение");

        dialog.showAndWait().ifPresent(name -> {
            DaoManager.getInstance().findDao(Division.class).Add(new Division(1, name));
            Table.setItems(FXCollections.observableList(
                    DaoManager.getInstance().findDao(Division.class).getAll()
            ));
        });
    }

    @FXML
    public void onRemoveClicked(MouseEvent event) {
        Division pos = Table.getSelectionModel().getSelectedItem();
        if(pos == null)
            return;
        if(!DaoManager.getInstance().findDao(Division.class).Remove(pos.getId())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.showAndWait();
            return;
        }
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Division.class).getAll()
        ));
    }
}
