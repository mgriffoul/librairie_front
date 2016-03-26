

package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
    
     public  Connection connecterBdd() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur: " + ex.getMessage());
        }
        Connection connect = null;
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=librairie;user=sa;password=sa";

            connect = DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            System.err.println("Erreur :" + ex.getMessage());
        }
        return connect;
    }

    public  void decoBdd(Connection connect) {
        try {
            connect.close();
        } catch (SQLException ex) {
            System.err.println("Erreur " + ex.getMessage());
        }
    }
    
}
