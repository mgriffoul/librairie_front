package beans;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Commande {

    public Commande() {
        prixCommande = "0";
        poidsCommande = 0;

    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public void setLigneCommande(ArrayList<LigneCommande> LigneCommande) {
        this.LigneCommande = LigneCommande;
    }

    String pseudo;
    String numeroCommande;
    String prixCommande;
    Date dateCommande;

    public String getStatutCommande() {
        return statutCommande;
    }

    public ArrayList<LigneCommande> getLigneCommande() {
        return LigneCommande;
    }
    float poidsCommande;
    String adresseipCommande;
    String statutCommande;

    ArrayList<LigneCommande> LigneCommande = new ArrayList();

    public String getPrixCommande() {
        return prixCommande;
    }


    public void setPrixCommande(String prixCommande) {
        this.prixCommande = prixCommande;
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

    public void addLigneCommande(LigneCommande lc) {
        this.LigneCommande.add(lc);
    }

    public void calcPrixCommande(String prix) {
        prix = prix.replaceAll(",",".");
        float p = Float.parseFloat(prix);
        float tp = Float.parseFloat(this.prixCommande);
        tp += p;
        DecimalFormat df = new DecimalFormat("#.00");
        this.prixCommande = String.valueOf(df.format(tp));
    }
    
}
