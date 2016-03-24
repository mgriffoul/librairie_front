/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.Bdd;
import java.io.Serializable;
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
public class Oeuvre implements Serializable{
    
    private Integer idOeuvre;
    private String titre;
    private String sousTitre;
    private String resume;

    public Oeuvre() {
    }

    public Integer getIdOeuvre() {
        return idOeuvre;
    }

    public String getTitre() {
        return titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public String getResume() {
        return resume;
    }

    public void setIdOeuvre(Integer idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
     return titre;
    }
    
    public void recupererId(){
        
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        
        String query = "SELECT titre, idOeuvre FROM oeuvre WHERE titre = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.getTitre());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                this.setIdOeuvre(rs.getInt("idOeuvre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Oeuvre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sauvegarderOeuvre(){
        
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        
        String query = "INSERT INTO oeuvre (titre, sousTitre, sumary)"
                + " VALUES"
                + " (?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.getTitre());
            
            if(this.getSousTitre()!=null){
                ps.setString(2, this.getSousTitre());
            }else{
                ps.setNull(2, java.sql.Types.VARCHAR);
            }
            
            if(this.getResume()!=null){
                ps.setString(3, this.getResume());
            }else{
                ps.setNull(3, java.sql.Types.VARCHAR);
            }
            
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Oeuvre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
        
    
    
}
