

package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SousCategorie implements Serializable{
    
      private String nomSousCategorie;
   private Integer idSousCategorie;
   
    public SousCategorie() {
    }

    public SousCategorie(String nomSousCategorie, String nomCategorie) {
        this.nomSousCategorie = nomSousCategorie;
    }


    public String getNomSousCategorie() {
        return nomSousCategorie;
    }

    public void setNomSousCategorie(String nomSousCategorie) {
        this.nomSousCategorie = nomSousCategorie;
    }

    public Integer getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(Integer idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    
     public void chargerSousCategorie(){
        
        Integer id = this.idSousCategorie;
        
        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();
        

        try {

            String query = "SELECT * from souscategorie where idSousCategorie = ?"
                    + " ";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            

            if (rs.next()) {
                this.setNomSousCategorie(rs.getString("nomSouscategorie"));
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(beans.Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
   
    
    
}
