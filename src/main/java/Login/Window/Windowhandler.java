package Login.Window;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class Windowhandler {
    
    public void load(String window) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(App.class.getResource(window+".fxml"));
        Scene scene = new Scene(fxmlloader.load(), 640, 480);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.setResizable(false);
        stage.show();
    }
}
