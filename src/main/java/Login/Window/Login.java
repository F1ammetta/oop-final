package Login.Window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Vector;

import connect.Query;


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
    private Hyperlink register;

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
        if (event.getCode().toString().equals("ENTER")){
            log(null);
        };
    }

    @FXML
    void log(MouseEvent event) {
        String us = Query.request("users");
        String[] temp = us.split("\n");
        Vector<String> users = new Vector<String>();
        Vector<String> passs = new Vector<String>();
        for (int i = 0; i < temp.length; i++) {
            if (i%2 == 0) users.add(temp[i].replace("username: ", ""));
            else passs.add(temp[i].replace("passwd: ", ""));
        }
        if (users.contains(user.getText())){
            if (pass.getText().equals(passs.get(users.indexOf(user.getText())))){
                System.out.println("Welcome");
            }
            else{
                // TODO: HANDLE WRONG PASSWORD
            }
        }
        else{
            // TODO: HANDLE MISSING USERNAME
        }
    }

    @FXML
    void register(ActionEvent event) {
        try {
            Stage stage = (Stage) register.getScene().getWindow();
            stage.close();
            Windowhandler windowhandler = new Windowhandler();
            windowhandler.load("register");
        } catch (Exception e) {

        }
    }

}
