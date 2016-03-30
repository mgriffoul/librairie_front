
package beans;

import java.util.Vector;

public class LigneCommande {
    
    private int idLigneCommande;
    private String nomAuteur;
    private Edition edition;
    private Oeuvre oeuvre;
    private int qte;
    private float reduc;
    private float tvaAppli;
    private String prixTTC;

    public void setNomEditeur(String nomEditeur) {
        this.nomEditeur = nomEditeur;
    }
    private String titreLivre;
    private String isbn;

    public String getNomEditeur() {
        return nomEditeur;
    }
    private String nomEditeur;

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public LigneCommande() {
    }

    public LigneCommande(Edition edition, int qte, float reduc) {
        this.edition = edition;
        this.qte = qte;
        this.reduc = reduc;

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitreLivre() {
        return titreLivre;
    }

    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    public int getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(int idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public float getTvaAppli() {
        return tvaAppli;
    }

    public void setTvaAppli(float tvaAppli) {
        this.tvaAppli = tvaAppli;
    }

    public String getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(String prixTTC) {
        this.prixTTC = prixTTC;
    }
    
    
    
    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }



    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getReduc() {
        return reduc;
    }

    public void setReduc(float reduc) {
        this.reduc = reduc;
    }
    
    
    public Vector getVector() {
        Vector vv = new Vector();
        vv.add(this.idLigneCommande);
        vv.add(this);
        vv.add(this.qte);
        vv.add(this.getEdition().getPrixVenteHt());
        vv.add(this.reduc);
        vv.add(this.tvaAppli);
        vv.add(this.prixTTC);
        return vv;
    }
    
    @Override
    public String toString() {
        return this.oeuvre.getTitre();
    }
        public int delta( int i) {
        this.qte += i;
        return qte;
    }
}
