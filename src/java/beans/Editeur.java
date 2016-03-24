/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.Bdd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GriffMacBook
 */
public class Editeur {

    private String nomEditeur;
    private Integer idEditeur;

    public Editeur() {
    }

    public String getNomEditeur() {
        return nomEditeur;
    }

    public Integer getIdEditeur() {
        return idEditeur;
    }

    public void setNomEditeur(String nomEditeur) {
        this.nomEditeur = nomEditeur;
    }

    public void setIdEditeur(Integer idEditeur) {
        this.idEditeur = idEditeur;
    }

    @Override
    public String toString() {
        return nomEditeur;
    }

    public void recupererId() {

        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();

        String query = "SELECT nomEditeur, idEditeur FROM editeur WHERE nomEditeur = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.getNomEditeur());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                this.setIdEditeur(rs.getInt("idEditeur"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Oeuvre.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void sauvegarderEditeur(){
        
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        
        String query = "INSERT INTO editeur (nomEditeur)"
                + " VALUES"
                + " (?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.getNomEditeur());
            
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Oeuvre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
