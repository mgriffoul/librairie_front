package beans;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Commentaire {

    private Integer idCommentaire;
    private String pseudo;
    private Timestamp date;
    private Integer note;
    private String commentaire;

    public Commentaire() {
    }

    public Integer getIdCommentaire() {
        return idCommentaire;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getDateFormat(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/ HH:mm");
        String s = sdf.format(this.getDate());
        
        return s;
    }

    public Integer getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setIdCommentaire(Integer idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    

    public void setNote(Integer note) {
        this.note = note;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public static ArrayList<Commentaire> recupererCommentaire(String isbn) {

        ArrayList<Commentaire> listeCommentaire = new ArrayList();

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();

        try {
            String query = "SELECT dbo.EDITION.ISBN, dbo.COMMENTAIRE.IDCOMMENTAIRE FROM dbo.COMMENTAIRE"
                    + " INNER JOIN dbo.LIGNECOMMANDE ON dbo.COMMENTAIRE.IDLIGNECOMMANDE = dbo.LIGNECOMMANDE.IDLIGNECOMMANDE"
                    + " INNER JOIN dbo.EDITION ON dbo.LIGNECOMMANDE.ISBN = dbo.EDITION.ISBN"
                    + " where dbo.EDITION.ISBN = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Commentaire coment = new Commentaire();
                coment.setIdCommentaire(rs.getInt("idCommentaire"));
                coment.chargerCommentaire();
                listeCommentaire.add(coment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Commentaire.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listeCommentaire;
    }

    public void chargerCommentaire() {

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();

        try {

            String query = "select * from COMMENTAIRE where IDCOMMENTAIRE= ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, this.getIdCommentaire());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.setPseudo(rs.getString("pseudo"));
                this.setCommentaire(rs.getString("commentaire"));
                this.setDate(rs.getTimestamp("date"));
                this.setNote(rs.getInt("note"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Commentaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void soumettreCom(String isbn) {

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();

        int idLigne = -1;

        //recuperation d'une ligne de commande correspondant au pseudo et à l'isbn en question
        try {
            String query = "select  idLigneCommande from commande as co JOIN LIGNECOMMANDE as li ON co.IDCOMMANDE=li.IDCOMMANDE"
                    + " JOIN EDITION as ed ON li.ISBN = ed.ISBN"
                    + " where ed.ISBN = ? and PSEUDO = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, isbn);
            ps.setString(2, this.getPseudo());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idLigne = rs.getInt("idLignecommande");
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Commentaire.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ecriture dans la table commentaire
        try {
            String query = "insert into COMMENTAIRE (IDLIGNECOMMANDE, PSEUDO, COMMENTAIRE, DATE, NOTE)"
                    + " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idLigne);
            ps.setString(2, this.getPseudo());
            ps.setString(3, this.getCommentaire());
            ps.setTimestamp(4, this.getDate());
            ps.setInt(5, this.getNote());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Commentaire.class.getName()).log(Level.SEVERE, null, ex);
        }

        bdd.decoBdd(con);

    }

}
