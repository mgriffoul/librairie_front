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
import java.util.Vector;

/**
 *
 * @author GriffMacBook
 */
public class Contributeur implements Serializable{
    
    private Integer idContributeur;
    private String nomContributeur;
    private String prenomContributeur;
    private String nationnalite;

    public Contributeur() {
    }

    public Integer getIdContributeur() {
        return idContributeur;
    }

    public String getNationnalite() {
        return nationnalite;
    }

    public String getNomContributeur() {
        return nomContributeur;
    }

    public String getPrenomContributeur() {
        return prenomContributeur;
    }

    public void setIdContributeur(Integer idContributeur) {
        this.idContributeur = idContributeur;
    }

    public void setNationnalite(String nationnalite) {
        this.nationnalite = nationnalite;
    }

    public void setNomContributeur(String nomContributeur) {
        this.nomContributeur = nomContributeur;
    }

    public void setPrenomContributeur(String prenomContributeur) {
        this.prenomContributeur = prenomContributeur;
    }

    @Override
    public String toString() {
    return prenomContributeur+" "+nomContributeur;   
    }
    
     public void recupererId(){
        
      Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        
        String query = "SELECT idContributeur, nomContributeur FROM contributeur WHERE nomcontributeur = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.getNomContributeur());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                this.setIdContributeur(rs.getInt("idContributeur"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getErrorCode());
        }
    }

    
    public void sauvegarderContributeur(){
        
         Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        
        String query = "INSERT INTO contributeur (nomContributeur, prenomContributeur, NATIONALITECONTRIBUTEUR)"
                + " VALUES"
                + " (?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.getNomContributeur());
            
            if(this.getPrenomContributeur()!=null){
            ps.setString(2, this.getPrenomContributeur());
            }else{
                 ps.setNull(2, java.sql.Types.VARCHAR);
            }
            
            if(this.getNationnalite()!=null){
                ps.setString(3, this.getNationnalite());
            }else{
                ps.setNull(3, java.sql.Types.VARCHAR);
            }
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println(ex.getErrorCode());
        }
    }
    
    public void updateContributeur(){
        
        System.out.println("update id ="+this.getIdContributeur());
        System.out.println("nom = "+this.nomContributeur+" prenom = "+prenomContributeur);
        
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        
        String query = "UPDATE CONTRIBUTEUR SET NOMCONTRIBUTEUR=?,"
                + "PRENOMCONTRIBUTEUR=?,"
                + "NATIONALITECONTRIBUTEUR=? "
                + "WHERE IDCONTRIBUTEUR='"+this.idContributeur+"'";
        System.out.println("try??");
        try {
            System.out.println("ps1");
            PreparedStatement ps = con.prepareStatement(query);
            System.out.println("ps2");
            ps.setString(1, this.nomContributeur);
            System.out.println("ps3");
            ps.setString(2, this.prenomContributeur);
            System.out.println("ps4");
            ps.setString(3, this.nationnalite);
            
            System.out.println("On va Update sur un air de Gilbert");
            
            ps.executeUpdate();
            
            System.out.println("Done");
            
        } catch (SQLException ex) {
            System.err.println(ex.getErrorCode());
        }
        
    }
    
    
    

public Vector creerVecteur(){
        Vector vv = new Vector();
        vv.add(this);
        vv.add(this.nomContributeur);
        vv.add(this.prenomContributeur);
        vv.add(this.nationnalite);
        return vv;
    }
 
    
    
   
}
