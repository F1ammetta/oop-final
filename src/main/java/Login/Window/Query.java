package Login.Window;
import java.sql.Connection;
import connect.Connect;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Query {
    public static String request(){
        String query = "SELECT campo1,campo2,… FROM Tabla WHERE condicion";
        String obj = "";
        Connect ocon = new Connect();
        try (Connection con = ocon.connect(); Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnas = rsMetaData.getColumnCount();
            while(rs.next()){
                for(int i = 1; i <= columnas; i++){
                String cv = rs.getString(i);
                obj = obj + rsMetaData.getColumnName(i)+ ":"+" "+cv+"\n";
                }
            }
        } catch (SQLException e) {
        System.out.println("Error: "+e.getMessage());
        }
        return obj;
    }

    public static void insert(){
        String query = "INSERT INTO Tabla(campo1,campo2… ,campon) VALUES("+valorCampo1+","+"'"+valorCampo2+","+valorCampoInt+");";
        Connect ocon = new Connect();
        try (Connection con = ocon.connect(); Statement st = con.createStatement()){
            st.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
