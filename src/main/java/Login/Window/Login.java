package Login.Window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class Login {

    @FXML
    private Button ClearButton;

    @FXML
    private AnchorPane Window;

    @FXML
    private Button login;

    @FXML
    private Text label;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField user;
    
    @FXML
    void clear(ActionEvent event) {
        user.setText(null);
        pass.setText(null);
    }

    @FXML
    void keypress(KeyEvent event) {
        // if (event.getCode().toString().equals("ENTER"));
        // log(null);
    }

    @FXML
    void log(MouseEvent event) {
        // Log log = new Log();
        // try {
        //     log.load();
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }

    }

}
