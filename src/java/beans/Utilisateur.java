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

            String query = "SELECT dbo.EDITION.ISBN, dbo.COMMENTAIRE.PSEUDO"
                    + " FROM dbo.EDITION INNER JOIN"
                    + " dbo.LIGNECOMMANDE ON dbo.EDITION.ISBN = dbo.LIGNECOMMANDE.ISBN INNER JOIN\n"
                    + " dbo.COMMENTAIRE ON dbo.LIGNECOMMANDE.IDLIGNECOMMANDE = dbo.COMMENTAIRE.IDLIGNECOMMANDE"
                    + " where  dbo.EDITION.ISBN = ? and dbo.COMMENTAIRE.PSEUDO= ?";

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
