package com.cursovay.curs.ui.kind;

import com.cursovay.curs.HelloApplication;
import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.KindContract;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KindContractController implements Initializable {

    public TableView<KindContract> Table;
    public TableColumn<KindContract, String> idType;
    public TableColumn<KindContract, String> idKind;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // устанавливаем тип и значение которое должно хранится в колонке
        idType.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTypeContract().getTypeContract()));
        idKind.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getKindContract()));

        // заполняем таблицу данными
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(KindContract.class).getAll()
        ));
    }

    @FXML
    public void onAddClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/add_kind_contract.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Add Kind");
            stage.setScene(new Scene(root, 400, 600));
            stage.showAndWait();

            Table.setItems(FXCollections.observableList(
                    DaoManager.getInstance().findDao(KindContract.class).getAll()
            ));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRemoveClicked(MouseEvent event) {
        KindContract pos = Table.getSelectionModel().getSelectedItem();
        if(pos == null)
            return;
        if(!DaoManager.getInstance().findDao(KindContract.class).Remove(pos.getId())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.showAndWait();
            return;
        }
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(KindContract.class).getAll()
        ));
    }

}
