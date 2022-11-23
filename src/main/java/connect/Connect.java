package connect;
import java.sql.Connection;
import java.sql.SQLException; 
import javafx.scene.control.Alert;
import java.sql.DriverManager; 

public class Connect { 
    public Connection connect(){ 
        String url= "jdbc:sqlite:db1.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System .out .println ("Connection to SQlite has been stablished");
        }
        catch (SQLException e){
            Alert alerta = new Alert (Alert .AlertType .ERROR); //JOptionPane.showMessageDialog (null,"Error: "+e.getMessage()); alerta .setHeaderText (null);
            alerta .setTitle ("Error");
            alerta .setContentText ( e .getMessage());
            alerta .show();
            System .out .println ( e .getMessage());
        }
        return conn ;
    }
}
