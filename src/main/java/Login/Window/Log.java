package Login.Window;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Log {
    
    public void load() throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
