package com.cursovay.curs.ui.type;

import com.cursovay.curs.HelloApplication;
import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.Division;
import com.cursovay.curs.core.model.Position;
import com.cursovay.curs.core.model.TypeContract;
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

public class TypeContractController implements Initializable {
    public TableView<TypeContract> Table;
    public TableColumn<TypeContract, String> idPosition;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // устанавливаем тип и значение которое должно хранится в колонке
        idPosition.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTypeContract()));

        // заполняем таблицу данными
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(TypeContract.class).getAll()
        ));
    }

    @FXML
    public void onAddClicked(MouseEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Введите тип контракта");

        dialog.showAndWait().ifPresent(name -> {
            DaoManager.getInstance().findDao(TypeContract.class).Add(new TypeContract(1, name));
            Table.setItems(FXCollections.observableList(
                    DaoManager.getInstance().findDao(TypeContract.class).getAll()
            ));
        });
    }

    @FXML
    public void onRemoveClicked(MouseEvent event) {
        TypeContract pos = Table.getSelectionModel().getSelectedItem();
        if(pos == null)
            return;
        if(!DaoManager.getInstance().findDao(TypeContract.class).Remove(pos.getId())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.showAndWait();
            return;
        }
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(TypeContract.class).getAll()
        ));
    }

}
