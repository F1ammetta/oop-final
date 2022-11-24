package Login.Window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import connect.Query;

public class Register {

    @FXML
    private Button ClearButton;

    @FXML
    private Button RegisterButton;

    @FXML
    private AnchorPane Window;

    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private Label passwd_error;

    @FXML
    private TextField user;

    @FXML
    private Label user_error;

    @FXML
    void Write(ActionEvent event) {
        if (user.getText().equals("")){
            user_error.setOpacity(1);
            return;
        }
        if (!(pass1.getText().equals(pass2.getText())) || pass1.getText().equals("")){
            passwd_error.setOpacity(1);
            return;
        }
        Query.insert(user.getText(), pass1.getText());
    }

    @FXML
    void clear(ActionEvent event) {
        user.setText(null);
        pass1.setText(null);
        pass2.setText(null);
    }

}
