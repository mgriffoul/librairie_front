package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import servlets.index;

public class Edition implements Serializable {

    private Isbn isbn;
    private Oeuvre oeuvre;
    private Auteur auteur;
    private Traducteur traducteur;
    private Prefaceur prefaceur;
    private Tva tva;
    private Editeur editeur;
    private SousCategorie sousCategorie;
    private Integer nombrePage;
    private Date dateParution;
    private Float prixVenteHt;
    private Float prixAchatHt;
    private Float poids;
    private Integer stock;
    private String lienVignetteAccueil;
    private String lienVignetteFocus;
    private String lienVignettePanier;
    private String Langue;
    private String description;
    private String statutEdition;
    private String dimmension;
    private String formatEdition;
    private String remarqueEdition;
    private ListeMotCle listeMotClef;
    private Integer note;

    public Edition() {
    }

    public String getNomPrefaceur() {
        String p = "";

        if (this.getPrefaceur() != null) {
            if (this.getPrefaceur().getNomContributeur() != null && this.getPrefaceur().getPrenomContributeur() != null) {
                p = this.getPrefaceur().getPrenomContributeur() + " " + this.getPrefaceur().getNomContributeur();
            }
        }
        return p;
    }

    public String getNomTraducteur() {
        String p = "";

        if (this.getTraducteur() != null) {
            if (this.getTraducteur().getNomContributeur() != null && this.getTraducteur().getPrenomContributeur() != null) {
                p = this.getTraducteur().getPrenomContributeur() + " " + this.getTraducteur().getNomContributeur();
            }
        }
        return p;
    }

    public SousCategorie getSousCategorie() {
        return sousCategorie;
    }

    //propriete pour les notes
    public Integer getNote() {
        return note;
    }

    public String getLienVignetteAccueil() {
        return lienVignetteAccueil;
    }

    public String getLienVignetteFocus() {
        return lienVignetteFocus;
    }

    public String getLienVignettePanier() {
        return lienVignettePanier;
    }

    public String getResume() {
        String r = "";
        if (this.getOeuvre() != null) {
            r = this.getOeuvre().getResume();
        }

        return r;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public String getNomAuteur() {
        String str = "";
        String s = "";
        if (this.getAuteur() != null) {
            ArrayList<Contributeur> liste = this.getAuteur().getListeContributeur();

            for (Contributeur c : liste) {
                str = str + " " + c.getPrenomContributeur() + " " + c.getNomContributeur();
            }

        }

        return str;
    }

    public String getNumeroIsbn() {
        return this.getIsbn().getNumeroIsbn();

    }

    public Isbn getIsbn() {
        return isbn;
    }

    public Traducteur getTraducteur() {
        return traducteur;
    }

    public Prefaceur getPrefaceur() {
        return prefaceur;
    }

    public ListeMotCle getListeMotClef() {
        return listeMotClef;
    }

    public String getStringMotClef() {
        String s = "";
        if (this.getListeMotClef() != null) {
            ArrayList<Tag> liste = this.getListeMotClef().getListeMc();
            for (Tag t : liste) {
                s += t.getNom() + " ";
            }
        }
        return s;
    }

    public Tva getTva() {
        return tva;
    }

    public String getTauxTva() {
        String s = String.valueOf(this.tva.getTauxTva());
        return s;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public Integer getNombrePage() {
        return nombrePage;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public String getStringDateParution() {

        SimpleDateFormat sdp = new SimpleDateFormat("dd/MM/yyyy");
        String s = sdp.format(this.getDateParution());

        return s;
    }

    public Float getPrixVenteHt() {
        return prixVenteHt;
    }

    public Float getPrixAchatHt() {
        return prixAchatHt;
    }

    public Float getPoids() {
        return poids;
    }

    public String getRemarqueEdition() {
        return remarqueEdition;
    }

    public Integer getStock() {
        return stock;
    }

    public String getLangue() {
        return Langue;
    }

    public String getDescription() {
        return description;
    }

    public String getStatutEdition() {
        return statutEdition;
    }

    public String getDimmension() {
        return dimmension;
    }

    public String getFormatEdition() {
        return formatEdition;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public String getPrixTtc() {
        Float f = null;
        if (this.prixVenteHt != null && this.tva.getTauxTva() != null) {
            f = this.prixVenteHt + ((this.prixVenteHt * this.tva.getTauxTva()) / 100);
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String fl = String.valueOf(df.format(f));

        return fl;
    }

    public String getTitre() {
        String t = "";
        if (this.getOeuvre() != null) {
            t = this.getOeuvre().getTitre();
        }
        return t;
    }

    public String getSousTitre() {
        String t = "";
        if (this.getOeuvre() != null) {
            t = this.getOeuvre().getSousTitre();
        }
        return t;
    }

    public String getNomEditeur() {
        String n = "";
        if (this.getEditeur() != null) {
            n = this.getEditeur().getNomEditeur();
        }
        return n;
    }

    public void setPrixVenteHt(Float prixVenteHt) {
        this.prixVenteHt = prixVenteHt;
    }

    public void setPrixAchatHt(Float prixAchatHt) {
        this.prixAchatHt = prixAchatHt;
    }

    public void setPoids(Float poids) {
        this.poids = poids;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public void setLienVignetteAccueil(String lienVignetteAccueil) {
        this.lienVignetteAccueil = lienVignetteAccueil;
    }

    public void setLienVignetteFocus(String lienVignetteFocus) {
        this.lienVignetteFocus = lienVignetteFocus;
    }

    public void setLienVignettePanier(String lienVignettePanier) {
        this.lienVignettePanier = lienVignettePanier;
    }

    public void setListeMotClef(ListeMotCle listeMotClef) {
        this.listeMotClef = listeMotClef;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public void setIsbn(Isbn isbn) {
        this.isbn = isbn;
    }

    public void setRemarqueEdition(String remarqueEdition) {
        this.remarqueEdition = remarqueEdition;
    }

    public void setTraducteur(Traducteur traducteur) {
        this.traducteur = traducteur;
    }

    public void setPrefaceur(Prefaceur prefaceur) {
        this.prefaceur = prefaceur;
    }

    public void setTva(Tva tva) {
        this.tva = tva;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public void setNombrePage(Integer nombrePage) {
        this.nombrePage = nombrePage;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }

    public void setStock(Integer stock) {
        if (stock < 0) {
            stock = 0;
        }

        this.stock = stock;
    }

    public void setLangue(String Langue) {
        this.Langue = Langue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatutEdition(String statutEdition) {
        this.statutEdition = statutEdition;
    }

    public void setDimmension(String dimmension) {
        this.dimmension = dimmension;
    }

    public void setFormatEdition(String formatEdition) {
        this.formatEdition = formatEdition;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public void chargerEdition() {

        String strIsbn = isbn.getNumeroIsbn();

        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();

        //Recuperation des titres, sous-titres et resume
        String query = "SELECT isbn, titre, sousTitre, sumary"
                + " FROM edition as edi JOIN OEUVRE as oeu ON edi.IDOEUVRE=oeu.IDOEUVRE"
                + " WHERE isbn= ? ";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, strIsbn);

            ResultSet rs = ps.executeQuery();

            Oeuvre oeuvre = new Oeuvre();

            while (rs.next()) {
                oeuvre.setTitre(rs.getString("titre"));
                oeuvre.setSousTitre(rs.getString("sousTitre"));
                oeuvre.setResume(rs.getString("sumary"));
                this.setOeuvre(oeuvre);
            }
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
        }

        //recuperation des auteurs.
        auteur = new Auteur();
        ArrayList<Contributeur> maListe = new ArrayList();
        auteur.setListeContributeur(maListe);
        auteur.chargerAuteur(strIsbn);

        //recuperation de l'editeur
        query = "SELECT nomEditeur, ed.idEditeur"
                + " FROM editeur as ed JOIN edition as edi ON ed.idEditeur= edi.idEditeur"
                + " WHERE isbn= ? ";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, strIsbn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Editeur edit = new Editeur();
                edit.setIdEditeur(rs.getInt("idEditeur"));
                edit.setNomEditeur(rs.getString("nomEditeur"));
                this.editeur = edit;
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
        }

        //recuperation des infos de la table Editions
        query = "SELECT *"
                + " FROM edition"
                + " WHERE isbn= ? ";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, strIsbn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                this.setNombrePage(rs.getInt("nbpage"));
                this.setDateParution(rs.getDate("dateparution"));

                this.setPrixAchatHt(rs.getFloat("prixachatht"));
                this.setPrixVenteHt(rs.getFloat("prixventeht"));
                this.setPoids(rs.getFloat("poids"));
                this.setStock(rs.getInt("stock"));
                this.setLangue(rs.getString("langue"));
                this.setDescription(rs.getString("description"));
                this.setStatutEdition(rs.getString("statutedition"));
                this.setDimmension(rs.getString("dimension"));
                this.setFormatEdition(rs.getString("formatedition"));
                this.setRemarqueEdition(rs.getString("remarqueedition"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
        }

        //recuperation de la tva
        query = "SELECT isbn, tv.idTva, tauxTva"
                + " FROM tva as tv JOIN edition as edi ON tv.idtva=edi.idtva "
                + " WHERE isbn= ? ";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, strIsbn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tva tva = new Tva();
                tva.setTauxTva(rs.getFloat("tauxTva"));
                tva.setIdTva(rs.getInt("idTva"));
                this.tva = tva;
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
        }

        //recuperation des mots clef
        query = "SELECT nomTag, ta.idTag, isbn"
                + " FROM tag as ta JOIN affectTag as af ON ta.idTag=af.idTag"
                + " WHERE isbn= ? ";

        ArrayList<Tag> listeMotClefRecup = new ArrayList();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, strIsbn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("idTag"));
                tag.setNom(rs.getString("nomTag"));
                listeMotClefRecup.add(tag);
            }
            rs.close();
            ps.close();

            ListeMotCle lmc = new ListeMotCle();
            lmc.setListeMc(listeMotClefRecup);
            this.setListeMotClef(lmc);

        } catch (SQLException ex) {
            Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
        }

        //recuperation du prefaceur
        query = "SELECT prenomContributeur, nomContributeur, edi.idPrefaceur"
                + " FROM edition as edi JOIN contributeur as con ON edi.idPrefaceur=con.idContributeur"
                + " WHERE isbn= ? ";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, strIsbn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Prefaceur pref = new Prefaceur();
                pref.setIdContributeur(rs.getInt("idPrefaceur"));
                pref.setNomContributeur(rs.getString("nomContributeur"));
                pref.setPrenomContributeur(rs.getString("prenomContributeur"));
                this.prefaceur = pref;
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
        }

        //recuperation du traducteur
        query = "SELECT prenomContributeur, nomContributeur, edi.idTraducteur"
                + " FROM edition as edi JOIN contributeur as con ON edi.idTraducteur=con.idContributeur"
                + " WHERE isbn= ? ";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, strIsbn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Traducteur trad = new Traducteur();
                trad.setIdContributeur(rs.getInt("idTraducteur"));
                trad.setNomContributeur(rs.getString("nomContributeur"));
                trad.setPrenomContributeur(rs.getString("prenomContributeur"));
                this.traducteur = trad;
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
        }

        //recupération des images
        query = "SELECT isbn, vignetteAccueil, vignetteFocus, vignettePanier"
                + " FROM edition as ed join image as im ON ed.idimage=im.idimage"
                + " WHERE isbn= ? ";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, strIsbn);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.setLienVignetteAccueil(rs.getString("vignetteAccueil"));
                this.setLienVignetteFocus(rs.getString("vignetteFocus"));
                this.setLienVignettePanier(rs.getString("vignettePanier"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
        }

        //recupération de la note
        try {
            query = "SELECT dbo.COMMENTAIRE.NOTE, dbo.EDITION.ISBN"
                    + " FROM dbo.EDITION INNER JOIN"
                    + " dbo.LIGNECOMMANDE ON dbo.EDITION.ISBN = dbo.LIGNECOMMANDE.ISBN INNER JOIN"
                    + " dbo.COMMENTAIRE ON dbo.LIGNECOMMANDE.IDLIGNECOMMANDE = dbo.COMMENTAIRE.IDLIGNECOMMANDE "
                    + " WHERE dbo.EDITION.ISBN='" + isbn.getNumeroIsbn() + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                this.setNote(rs.getInt("note"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }

        lbd.decoBdd(con);
    }

    //fonction qui charge les editions du moment dans une arrayList
    public static ArrayList<Edition> chargerLivreMoment() {

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();
        ArrayList<Edition> listeEditionMoment = new ArrayList();
        try {
            String query = "SELECT * FROM AFFECTSOUSCATEGORIE WHERE idsouscategorie = 37";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Edition edition = new Edition();
                Isbn isbn = new Isbn();
                isbn.setNumeroIsbn(rs.getString("isbn"));
                edition.setIsbn(isbn);
                edition.chargerEdition();
                System.out.println(edition);
//                if (edition.getStock() != null) {
//                    if (edition.getStock() > 0) {
                        listeEditionMoment.add(edition);
//                    }
//                }

            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeEditionMoment;

    }

    //fonction qui charge les Editions concernées par une categorie en particulier
    public static ArrayList<Edition> editionParCategorie(Integer id) {
        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();

        ArrayList<Edition> listeEdition = new ArrayList();
        try {

            String query = "SELECT dbo.CATEGORIE.IDCATEGORIE, dbo.SOUSCATEGORIE.IDSOUSCATEGORIE, dbo.EDITION.ISBN"
                    + " FROM dbo.CATEGORIE INNER JOIN"
                    + " dbo.SOUSCATEGORIE ON dbo.CATEGORIE.IDCATEGORIE = dbo.SOUSCATEGORIE.IDCATEGORIE INNER JOIN"
                    + " dbo.AFFECTSOUSCATEGORIE ON dbo.SOUSCATEGORIE.IDSOUSCATEGORIE = dbo.AFFECTSOUSCATEGORIE.IDSOUSCATEGORIE INNER JOIN"
                    + " dbo.EDITION ON dbo.AFFECTSOUSCATEGORIE.ISBN = dbo.EDITION.ISBN "
                    + " where dbo.CATEGORIE.idcategorie= " + id;

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Edition edition = new Edition();
                Isbn isbn = new Isbn();
                isbn.setNumeroIsbn(rs.getString("isbn"));
                edition.setIsbn(isbn);
                edition.chargerEdition();
                if (edition.getStock() != null) {
                    if (edition.getStock() > 0) {
                        listeEdition.add(edition);
                    }
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        bdd.decoBdd(con);

        return listeEdition;
    }

    //Fonction qui récupère en ArrayList les editions d'une sous categorie en particulier
    public static ArrayList<Edition> editionParSousCategorie(Integer idCat, Integer idSsCat) {

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();
        ArrayList<Edition> listeEdition = new ArrayList();
        try {
            String query = "SELECT dbo.CATEGORIE.IDCATEGORIE, dbo.SOUSCATEGORIE.IDSOUSCATEGORIE, dbo.EDITION.ISBN"
                    + " FROM dbo.CATEGORIE INNER JOIN"
                    + " dbo.SOUSCATEGORIE ON dbo.CATEGORIE.IDCATEGORIE = dbo.SOUSCATEGORIE.IDCATEGORIE INNER JOIN"
                    + " dbo.AFFECTSOUSCATEGORIE ON dbo.SOUSCATEGORIE.IDSOUSCATEGORIE = dbo.AFFECTSOUSCATEGORIE.IDSOUSCATEGORIE INNER JOIN"
                    + " dbo.EDITION ON dbo.AFFECTSOUSCATEGORIE.ISBN = dbo.EDITION.ISBN "
                    + " where dbo.CATEGORIE.idcategorie= " + idCat + " AND dbo.SOUSCATEGORIE.IDSOUSCATEGORIE= " + idSsCat;

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Edition edition = new Edition();
                Isbn isbn = new Isbn();
                isbn.setNumeroIsbn(rs.getString("isbn"));
                edition.setIsbn(isbn);
                edition.chargerEdition();
                if (edition.getStock() != null) {
                    if (edition.getStock() > 0) {
                        listeEdition.add(edition);
                    }
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listeEdition;
    }
}
