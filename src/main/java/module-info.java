module Login.Window {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Login.Window to javafx.fxml;
    exports Login.Window;
}
