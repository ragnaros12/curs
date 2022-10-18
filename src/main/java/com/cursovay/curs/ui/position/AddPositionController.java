package com.cursovay.curs.ui.position;

import com.cursovay.curs.HelloApplication;
import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.Division;
import com.cursovay.curs.core.model.Position;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPositionController implements Initializable {
    public TextField position;
    public ComboBox<Division> division;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        division.setItems(FXCollections.observableList(DaoManager.getInstance().findDao(Division.class).getAll()));
        division.getSelectionModel().select(0);
    }
    @FXML
    public void onAddClicked(MouseEvent event) {
        DaoManager.getInstance().findDao(Position.class).Add(new Position(1, position.getText(),division.getSelectionModel().getSelectedItem()));
        ((Stage)position.getScene().getWindow()).close();
    }
}
