package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Categorie implements Serializable {

    private String nomCategorie;
    private Integer idCategorie;
    private ArrayList<SousCategorie> SousCategorie;

    public Categorie() {
    }

    public Categorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Categorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public ArrayList<SousCategorie> getSousCategorie() {
        return SousCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setSousCategorie(ArrayList<SousCategorie> SousCategorie) {
        this.SousCategorie = SousCategorie;
    }

    public static ArrayList<Categorie> initSidebar() {

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();
        ArrayList<beans.Categorie> listeCategorie = new ArrayList();

        ArrayList<Edition> listeEditionMoment = new ArrayList();

        try {
            Statement stmt = con.createStatement();

            String query = "SELECT * FROM categorie";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                beans.Categorie cate = new beans.Categorie();
                cate.setIdCategorie(rs.getInt("idcategorie"));
                cate.setNomCategorie(rs.getString("nomcategorie"));
                listeCategorie.add(cate);
            }

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(beans.Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
bdd.decoBdd(con);
        return listeCategorie;
    }

  

    public void chargerCategorie(){
        
        Integer id = this.idCategorie;
        
        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();
        

        try {

            String query = "SELECT * from categorie where idCategorie = ?"
                    + " ";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            

            if (rs.next()) {
                this.setNomCategorie(rs.getString("nomcategorie"));
                
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(beans.Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        bdd.decoBdd(con);
        
        
    }
    
    
    
}
