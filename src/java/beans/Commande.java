
package beans;

import java.sql.Date;
import java.util.Collection;


public class Commande {

    public Commande() {
    
    }
    
    String pseudo;
    String numeroCommande;
    String prixCommande;
    Date dateCommande;
    float poidsCommande;
    String adresseipCommande;
    
    Collection LigneCommande;

    public String getPrixCommande() {
        return prixCommande;
    }

    public Collection getLigneCommande() {
        return LigneCommande;
    }

    public void setPrixCommande(String prixCommande) {
        this.prixCommande = prixCommande;
    }

    public void setLigneCommande(Collection LigneCommande) {
        this.LigneCommande = LigneCommande;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getNumeroCommande() {
        return numeroCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public float getPoidsCommande() {
        return poidsCommande;
    }

    public String getAdresseipCommande() {
        return adresseipCommande;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNumeroCommande(String numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public void setPoidsCommande(float poidsCommande) {
        this.poidsCommande = poidsCommande;
    }

    public void setAdresseipCommande(String adresseipCommande) {
        this.adresseipCommande = adresseipCommande;
    }

    public void addLigneCommande(LigneCommande lc){
        this.LigneCommande.add(lc); 
    }
    
    
    
    
    
    
    
    
}
