package Login.Window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Appl {

    @FXML
    private Button AddButton;

    @FXML
    private Button ClearButton;

    @FXML
    private TextArea Output;

    @FXML
    private AnchorPane Window;

    @FXML
    private TextField gche;

    @FXML
    private TextField gender;

    @FXML
    private TextField ginf;

    @FXML
    private TextField gphys;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    void Add(ActionEvent event) {
        
    }

    @FXML
    void Clear(ActionEvent event) {
        id.setText(null);
        name.setText(null);
        gender.setText(null);
        ginf.setText(null);
        gphys.setText(null);
        gche.setText(null);
    }

}
