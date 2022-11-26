package Login.Window;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.Formatter;

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
    private TextArea Output2;

    @FXML
    private Label number_error;

    @FXML
    private Label gender_error;

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

    private HashMap<String, Label> labels = new HashMap<String, Label>();

    @FXML
    private TextField name;

    @FXML
    protected void initialize() {
        labels.put("NumberFormatException", number_error);
        labels.put("NullPointerException", gender_error);
        Show(null);
        ObservableList<String> choices = FXCollections.observableArrayList("M","F");
        gender.setItems(choices);
    }

    @FXML
    void Add(ActionEvent event) {
        try {
            for (Label l : labels.values()) l.setOpacity(0);
            Query.insert(Integer.parseInt(id.getText()), name.getText(), gender.getValue().charAt(0), Float.parseFloat(ginf.getText()), Float.parseFloat(gphys.getText()), Float.parseFloat(gche.getText()));
        } catch (Exception e) {
            labels.get(e.getClass().getSimpleName()).setOpacity(1);
        }
        Show(null);
    }

    @FXML
    void Show(ActionEvent event) {
        String us = Query.request("students");
        String[] temp = us.split("\n");
        Vector<Integer> ids = new Vector<Integer>();
        Vector<String> names = new Vector<String>();
        Vector<Character> genders = new Vector<Character>();
        Vector<Float> ginfs = new Vector<Float>();
        Vector<Float> gphyss = new Vector<Float>();
        Vector<Float> gches = new Vector<Float>();
        for (int i = 0; i < temp.length; i++) {
            if (i%6 == 0) ids.add((int)Integer.parseInt(temp[i].replace("id: ", "")));
            if ((i-1)%6 == 0) names.add(temp[i].replace("name: ", ""));
            if ((i-2)%6 == 0) genders.add((temp[i].replace("gender: ", "")).charAt(0));
            if ((i-3)%6 == 0) ginfs.add((float)Math.round(Float.parseFloat(temp[i].replace("ginf: ", ""))*100)/100);
            if ((i-4)%6 == 0) gphyss.add((float)Math.round(Float.parseFloat(temp[i].replace("gphys: ", ""))*100)/100);
            if ((i-5)%6 == 0) gches.add((float)Math.round(Float.parseFloat(temp[i].replace("gche: ", ""))*100)/100);
        }
        Formatter fmt = new Formatter();
        Object[] head = {"ID","NAME","GENDER","INFORMATICS","PHYSICS","CHEMISTRY"};
        fmt.format("|%-5s|%-10s|%-10s|%-12s|%-10s|%-10s|\n", head);
        Object[][] temp1 = {ids.toArray(), names.toArray(), genders.toArray(), ginfs.toArray(), gphyss.toArray(), gches.toArray()};
        Object[][] str = new Object[ids.size()][temp1.length];
        for (int i = 0; i < temp1.length; i++) {
            for (int j = 0; j < ids.size(); j++) {
                str[j][i] = temp1[i][j];
            }
        }
        for (int i = 0; i < str.length; i++) { 
            fmt.format("|%-5d|%-10s|%-10s|%-12s|%-10s|%-10s|\n", (Object[]) str[i]);
        }
        Output.setText(fmt.out().toString());
        fmt.close();
        HashMap<Vector<Float>, Vector<Float>> rels = new HashMap<Vector<Float>, Vector<Float>>();
        Vector<Float> inf = new Vector<Float>(6);
        Vector<Float> phy = new Vector<Float>(6);
        Vector<Float> che = new Vector<Float>(6);
        inf.addAll(List.of(0f, 0f, 0f, 0f, 0f, 0f, 1f));
        phy.addAll(List.of(0f, 0f, 0f, 0f, 0f, 0f, 2f));
        che.addAll(List.of(0f, 0f, 0f, 0f, 0f, 0f, 3f));
        Vector<Vector<Float>> vectors = new Vector<Vector<Float>>(3);
        vectors.add(inf); vectors.add(phy); vectors.add(che);
        rels.put(inf, ginfs); rels.put(phy, gphyss); rels.put(che, gches);
        for (int j = 0; j < 3; j++){
            Vector<Float> related = rels.get(vectors.get(j));
            for (int i = 0; i < ids.size(); i++){
                vectors.get(j).set(0, vectors.get(j).get(0)+related.get(i));
                if (isrange("DEFICIENT", related.get(i))) vectors.get(j).set(1, vectors.get(j).get(1) + 1);
                if (isrange("INSUFICIENT", related.get(i))) vectors.get(j).set(2, vectors.get(j).get(2) + 1);
                if (isrange("REGULAR", related.get(i))) vectors.get(j).set(3, vectors.get(j).get(3) + 1);
                if (isrange("OUTSTANDING", related.get(i))) vectors.get(j).set(4, vectors.get(j).get(4) + 1);
                if (isrange("EXCELLENT", related.get(i))) vectors.get(j).set(5, vectors.get(j).get(5) + 1);
            }
            for (int i = 0; i < 6; i++){
                vectors.get(j).set(i, vectors.get(j).get(i)/ids.size());
                if (i > 0) vectors.get(j).set(i, vectors.get(j).get(i)*100);
                vectors.get(j).set(i, (float)Math.round(vectors.get(j).get(i)*100)/100);
            }
        }
        inf.remove(6);
        Object[] infcorp = {"Informatics",inf.get(0),inf.get(1),inf.get(2),inf.get(3),inf.get(4),inf.get(5)};
        phy.remove(6);
        Object[] phycorp = {"Physics",phy.get(0),phy.get(1),phy.get(2),phy.get(3),phy.get(4),phy.get(5)};
        che.remove(6);
        Object[] checorp = {"Chemistry",che.get(0),che.get(1),che.get(2),che.get(3),che.get(4),che.get(5)};
        Object[] head1 = {"SIGNATURE","AVG","%DEFICIENT","%INSUFICIENT","%REGULAR","%OUTSTANDING","%EXCELLENT"};
        Formatter fmt2 = new Formatter();
        fmt2.format("|%-11s|%-6s|%-10s|%-12s|%-9s|%-12s|%-10s|\n", head1);
        fmt2.format("|%-11s|%-6s|%-10s|%-12s|%-9s|%-12s|%-10s|\n", infcorp);
        fmt2.format("|%-11s|%-6s|%-10s|%-12s|%-9s|%-12s|%-10s|\n", phycorp);
        fmt2.format("|%-11s|%-6s|%-10s|%-12s|%-9s|%-12s|%-10s|\n", checorp);
        Output2.setText(fmt2.out().toString());
        fmt2.close();
    }

    @FXML
    void enter(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            Add(null);
        };
    }

    @FXML
    void Clear(ActionEvent event) {
        id.setText(null);
        name.setText(null);
        ginf.setText(null);
        gphys.setText(null);
        gche.setText(null);
    }

    boolean isrange(String range, float x){
        switch (range) {
            default:
                return false;
            case "DEFICIENT":
                return x>=0 && x<=30;
            case "INSUFICIENT":
                return x>30 && x<=60;
            case "REGULAR":
                return x>60 && x<=80;
            case "OUTSTANDING":
                return x>80 && x<=90;
            case "EXCELLENT":
                return x>90 && x<=100;
        }
    }

}
