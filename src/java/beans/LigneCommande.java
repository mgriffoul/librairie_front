/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.DecimalFormat;
import java.util.Vector;

/**
 *
 * @author cdi206
 */
public class LigneCommande {
    
    private int idLigneCommande;
    private Edition edition;
    private Oeuvre oeuvre;
    private int qte;
    private float reduc;
    private float tvaAppli;
    private float prixTTC;
    private String titreLivre;
    private String isbn;
    
    
    
    // constructeurs

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

    public float getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(float prixTTC) {
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
