module Login.Window {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens Login.Window to javafx.fxml;
    exports Login.Window;
}
