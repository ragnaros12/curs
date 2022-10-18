package com.cursovay.curs.ui.contract;

import com.cursovay.curs.HelloApplication;
import com.cursovay.curs.core.Dao;
import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.Contract;
import com.cursovay.curs.core.model.Position;
import javafx.beans.property.SimpleObjectProperty;
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
import java.util.Date;
import java.util.ResourceBundle;

public class ContractController implements Initializable {
    public TableView<Contract> Table;
    public TableColumn<Contract, Integer> idNumber;
    public TableColumn<Contract, String> idPosition;
    public TableColumn<Contract, String> idEmployee;
    public TableColumn<Contract, String> idKindContract;
    public TableColumn<Contract, Date> idData;
    public TableColumn<Contract, Integer> idSrok;
    public TableColumn<Contract, Date> idDataSrok;
    public TableColumn<Contract, Integer> idOklad;
    public TableColumn<Contract, Boolean> idWork;
    public TableColumn<Contract, Double> idStavka;
    public TableColumn<Contract, String> idReason;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idNumber.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getNumber()));
        idPosition.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getPosition().getPosition()));
        idEmployee.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getEmployee().getFirstName()));
        idKindContract.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getKindContract().getKindContract()));
        idData.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getDate()));
        idSrok.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getTerm()));
        idDataSrok.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getDateTerm()));
        idOklad.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getSalary()));
        idWork.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().isWork()));
        idStavka.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getBet()));
        idReason.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getReason()));


        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Contract.class).getAll()
        ));
    }

    @FXML
    public void onAddClicked(MouseEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(HelloApplication.class.getResource("/add_contract.fxml")).load();
            Stage stage = new Stage();
            stage.setTitle("Add contract");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Table.setItems(FXCollections.observableList(
                    DaoManager.getInstance().findDao(Contract.class).getAll()
            ));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRemoveClicked(MouseEvent event) {
        Contract pos = Table.getSelectionModel().getSelectedItem();
        if(pos == null)
            return;
        if(!DaoManager.getInstance().findDao(Contract.class).Remove(pos.getId())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.showAndWait();
            return;
        }
        Table.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Contract.class).getAll()
        ));
    }
}
