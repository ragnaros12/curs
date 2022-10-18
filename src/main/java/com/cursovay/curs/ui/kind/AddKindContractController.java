package com.cursovay.curs.ui.kind;

import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.Division;
import com.cursovay.curs.core.model.KindContract;
import com.cursovay.curs.core.model.Position;
import com.cursovay.curs.core.model.TypeContract;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddKindContractController implements Initializable {
    public TextField position;
    public ComboBox<TypeContract> division;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        division.setItems(FXCollections.observableList(DaoManager.getInstance().findDao(TypeContract.class).getAll()));
        division.getSelectionModel().select(0);
    }
    @FXML
    public void onAddClicked(MouseEvent event) {
        DaoManager.getInstance().findDao(KindContract.class).Add(new KindContract(1, division.getSelectionModel().getSelectedItem(), position.getText()));
        ((Stage)position.getScene().getWindow()).close();
    }
}
