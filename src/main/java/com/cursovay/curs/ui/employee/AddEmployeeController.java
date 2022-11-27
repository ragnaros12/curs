package com.cursovay.curs.ui.employee;

import com.cursovay.curs.core.DaoManager;
import com.cursovay.curs.core.model.Employee;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    public TextField name;
    public TextField secondName;
    public TextField lastName;
    public TextField passport;
    public TextField whoGet;
    public DatePicker dateIssue;
    public TextField inn;
    public TextField kpp;
    public TextField address;
    public TextField family;
    public TextField phone;
    public TextField birthPlace;
    public TextField certificate;
    public DatePicker birthDay;
    public TextField nationality;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @FXML
    public void onAddClicked(MouseEvent event) {
        if(DaoManager.getInstance().findDao(Employee.class).Add(new Employee(
                1,
                nationality.getText(),
                name.getText(),
                secondName.getText(),
                lastName.getText(),
                passport.getText(),
                whoGet.getText(),
                address.getText(),
                family.getText(),
                certificate.getText(),
                kpp.getText(),
                phone.getText(),
                birthPlace.getText(),
                new Date(Date.from(
                        birthDay.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()
                ).getTime()),
                new Date(Date.from(
                        dateIssue.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()
                ).getTime())
        )) != null) {
            ((Stage) nationality.getScene().getWindow()).close();
        }
        else{

        }

    }
}
