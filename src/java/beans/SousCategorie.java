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
import servlets.index;

public class SousCategorie implements Serializable {

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

    
    //fonction qui récupere le nom d'une sous categorie d'apres son id
    public void chargerSousCategorie() {

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
bdd.decoBdd(con);
    }

     //fonction pour récupérer toutes les sous categorie de la bdd associé à un id de catégorie en paramêtre
    public static ArrayList<SousCategorie> recupererSousCategorie(int id) {

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();
        ArrayList<beans.SousCategorie> listeSousCate = new ArrayList();

        try {

            String query = "select idsousCategorie, nomsouscategorie from souscategorie as so join categorie as ca"
                    + " on so.idcategorie = ca.idcategorie where ca.idcategorie= ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            

            while (rs.next()) {
                SousCategorie sousCate = new SousCategorie();
                sousCate.setIdSousCategorie(rs.getInt("idSouscategorie"));
                sousCate.setNomSousCategorie(rs.getString("nomSousCategorie"));
              
                listeSousCate.add(sousCate);
                
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(beans.Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }

        bdd.decoBdd(con);
        return listeSousCate;
    }
}
