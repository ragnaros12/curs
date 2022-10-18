package com.cursovay.curs.ui.position;

import com.cursovay.curs.HelloApplication;
import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.Position;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PositionController implements Initializable {
    public TableColumn<Position, String> idPosition;
    public TableColumn<Position, String> idDivision;

    public TableView<Position> Table;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // устанавливаем тип и значение которое должно хранится в колонке
        idPosition.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPosition()));
        idDivision.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDivision().getDivision()));

        // заполняем таблицу данными
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Position.class).getAll()
        ));
    }

    @FXML
    public void onAddClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/add_position.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Add position");
            stage.setScene(new Scene(root, 400, 600));
            stage.showAndWait();

            Table.setItems(FXCollections.observableList(
                    DaoManager.getInstance().findDao(Position.class).getAll()
            ));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRemoveClicked(MouseEvent event) {
        Position pos = Table.getSelectionModel().getSelectedItem();
        if(pos == null)
            return;
        if(!DaoManager.getInstance().findDao(Position.class).Remove(pos.getId())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.showAndWait();
            return;
        }
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Position.class).getAll()
        ));
    }

}
