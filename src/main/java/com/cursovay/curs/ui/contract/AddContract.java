package com.cursovay.curs.ui.contract;

import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.Contract;
import com.cursovay.curs.core.model.Employee;
import com.cursovay.curs.core.model.KindContract;
import com.cursovay.curs.core.model.Position;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AddContract implements Initializable {


    public TextField number;
    public TextField Srok;
    public TextField Oklad;
    public TextField Stavka;
    public DatePicker dateEnd;
    public TextField reason;
    public DatePicker dateRast;
    public ComboBox<Employee> employee;
    public ComboBox<KindContract> kindContract;
    public ComboBox<Position> Doljn;

    @FXML
    public void onAddClicked(MouseEvent event) {
        if(DaoManager.getInstance().findDao(Contract.class).Add(new Contract(
                1, Integer.parseInt(number.getText()),
                Doljn.getSelectionModel().getSelectedItem(),
                employee.getSelectionModel().getSelectedItem(),
                kindContract.getSelectionModel().getSelectedItem(),
                new Date(Date.from(
                        dateEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()
                ).getTime()),
                Integer.parseInt(Srok.getText()),
                new Date(Date.from(
                        dateRast.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()
                ).getTime()),
                Integer.parseInt(Oklad.getText()),
                true,
                Integer.parseInt(Stavka.getText()),
                reason.getText()
                )) != null) {
            ((Stage) dateRast.getScene().getWindow()).close();
        }
        else{

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employee.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Employee.class).getAll()
        ));
        employee.getSelectionModel().select(0);
        kindContract.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(KindContract.class).getAll()
        ));
        kindContract.getSelectionModel().select(0);

        Doljn.setItems(FXCollections.observableList(
                DaoManager.getInstance().findDao(Position.class).getAll()
        ));
        Doljn.getSelectionModel().select(0);

    }
}
