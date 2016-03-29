package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilisateur {

    private String email;
    private String motDePasse;
    private String nom;
    private String pseudo;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Boolean verifPrevComent(String isbn) {
        Boolean pres = false;

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();

        try {

            String query = "SELECT dbo.COMMENTAIRE.PSEUDO, dbo.LIGNECOMMANDE.ISBN"
                    + " FROM  dbo.COMMENTAIRE INNER JOIN"
                    + " dbo.LIGNECOMMANDE ON dbo.COMMENTAIRE.IDLIGNECOMMANDE = dbo.LIGNECOMMANDE.IDLIGNECOMMANDE"
                    + " where dbo.LIGNECOMMANDE.ISBN= ? and dbo.COMMENTAIRE.PSEUDO= ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, isbn);
            ps.setString(2, this.getPseudo());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                pres = true;
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        bdd.decoBdd(con);
        return pres;
    }

     public Boolean verifierPrevAchat(String isbn) {
    
          Boolean pres = false;

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();

         try {

            String query = "SELECT dbo.COMMANDE.PSEUDO, dbo.EDITION.ISBN" 
                    +" FROM  dbo.COMMANDE INNER JOIN" 
                    +" dbo.LIGNECOMMANDE ON dbo.COMMANDE.IDCOMMANDE = dbo.LIGNECOMMANDE.IDCOMMANDE INNER JOIN" 
                    +" dbo.EDITION ON dbo.LIGNECOMMANDE.ISBN = dbo.EDITION.ISBN "
                    +" where dbo.EDITION.ISBN= ? and dbo.COMMANDE.PSEUDO= ?";

            
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, isbn);
            ps.setString(2, this.getPseudo());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                pres = true;
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        bdd.decoBdd(con);
         
         return pres;
     }
}
