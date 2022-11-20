module com.cursovay.curs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    exports com.cursovay.curs;
    exports com.cursovay.curs.ui.main;
    exports com.cursovay.curs.ui.position;
    opens com.cursovay.curs.ui.position;
    opens com.cursovay.curs.ui.employee;
    opens com.cursovay.curs.ui.type;
    opens com.cursovay.curs.ui.kind;
    opens com.cursovay.curs.ui.contract;
    exports com.cursovay.curs.ui.division;
}