package beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    float totalCom;


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


    public float getTotalCom() {
        return totalCom;
    }

    public void setTotalCom(float totalCom) {
        this.totalCom = totalCom;
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
    
    public void qtyChangeLigne(String isbn, int qte) {
        for (LigneCommande lc : LigneCommande) {            
            if (lc.getIsbn().equals(isbn)) {
                lc.setQte(qte);
                return;
            }
        }
        
    }
    
    public void calcPrixCommande() {
        float tp = 0;
        for (LigneCommande lc : LigneCommande) {            
            float p = Float.parseFloat(lc.getPrixTTC().replaceAll(",", "."));
            p = p * lc.getQte();
            tp += p;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        this.prixCommande = String.valueOf(df.format(tp));
    }
    
    public void delLigneCommande(String isbn) {        
        for (LigneCommande lc : LigneCommande) {            
            if (lc.getIsbn().equals(isbn)) {
                this.LigneCommande.remove(lc);
                return;
            }
        }
    }
    
    public void clear() {
        this.LigneCommande.clear();
    }
    @Override
    public String toString (){
    return this.numeroCommande+" "+this.dateCommande+" "+this.statutCommande;
    }

    public int getIdCommande() {
        int idCommande=0;
        
        String query = "Select max(IDCOMMANDE)as IDCOMMANDE from commande";
        
        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();
        
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            rs.next();
            idCommande = rs.getInt("IDCOMMANDE");
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            //System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
        }
        bdd.decoBdd(con);
        return idCommande;

    }
}
