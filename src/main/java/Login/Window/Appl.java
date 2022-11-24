package Login.Window;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.Vector;
import connect.Query;

public class Appl {

    @FXML
    private Button AddButton;

    @FXML
    private Button ClearButton;

    @FXML
    private Button ShowButton;

    @FXML
    private TextArea Output;

    @FXML
    private AnchorPane Window;

    @FXML
    private TextField gche;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField ginf;

    @FXML
    private TextField gphys;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    protected void initialize() {
        Show(null);
        ObservableList<String> choices = FXCollections.observableArrayList("M","F");
        gender.setItems(choices);
    }

    @FXML
    void Add(ActionEvent event) {
        
    }

    @FXML
    void Show(ActionEvent event) {
        String us = Query.request("students");
        String[] temp = us.split("\n");
        Vector<String> ids = new Vector<String>();
        Vector<String> names = new Vector<String>();
        Vector<String> genders = new Vector<String>();
        Vector<String> ginfs = new Vector<String>();
        Vector<String> gphyss = new Vector<String>();
        Vector<String> gches = new Vector<String>();
        for (int i = 0; i < temp.length; i++) {
            if (i%6 == 0) ids.add(temp[i].replace("id: ", ""));
            if ((i-1)%6 == 0) names.add(temp[i].replace("name: ", ""));
            if ((i-2)%6 == 0) genders.add(temp[i].replace("gender: ", ""));
            if ((i-3)%6 == 0) ginfs.add(temp[i].replace("ginf: ", ""));
            if ((i-4)%6 == 0) gphyss.add(temp[i].replace("gphys: ", ""));
            if ((i-5)%6 == 0) gches.add(temp[i].replace("gche: ", ""));
        }
        
    }

    @FXML
    void Clear(ActionEvent event) {
        id.setText(null);
        name.setText(null);
        
        ginf.setText(null);
        gphys.setText(null);
        gche.setText(null);
    }

}
