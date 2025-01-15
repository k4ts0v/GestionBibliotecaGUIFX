module com.lvg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;

    opens com.lvg to javafx.fxml;
    exports com.lvg;
}
