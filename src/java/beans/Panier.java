package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Panier implements Serializable {

    Commande commande = null;

    public Panier() {
        this.commande = new Commande();
    }

    public void add(String isbn) {
        float reduc;
        if (commande == null) {
            this.commande = new Commande();
        }
        LigneCommande ligne = new LigneCommande();
        Edition edition = new Edition();
        Isbn is = new Isbn();
        is.setNumeroIsbn(isbn);
        edition.setIsbn(is);
        edition.chargerEdition();
        reduc = ligne.getReduc();
        ligne = new LigneCommande(edition, 1, reduc);
        edition.chargerEdition();
        ligne.setTitreLivre(edition.getTitre());
        ligne.setIsbn(isbn);
        ligne.setPrixTTC(edition.getPrixTtc());
        ligne.setNomAuteur(edition.getNomAuteur());
        ligne.setNomEditeur(edition.getNomEditeur());
        this.commande.addLigneCommande(ligne);
        this.commande.calcPrixCommande();
    }


//    public void dec(String isbn) {
//        LigneCommande ligne = new LigneCommande();
//        Edition edition = new Edition();
//        Isbn is = new Isbn();
//        is.setNumeroIsbn(isbn);
//        edition.setIsbn(is);
//
//        dec(isbn, edition, ligne, 1);
//    }
//    public void dec(String isbn, Edition edition, LigneCommande ligne, int qte) {
//
//        //System.out.println("isbn = "+edition.getIsbn().getNumeroIsbn());
//        //edition.chargerEdition();
//        if (map.containsKey(isbn)) {
//            ligne = map.get(isbn);
////            i.setQty( i.getQty()+1);
//            ligne.setQte(ligne.delta(-qte));
//            if (ligne.getQte() < 1) {
//                del(isbn);
//            }
//        }
//    }
//    public void dec( String isbn) {
//        dec( isbn, 1);
//    }
//    public void dec( String isbn, int qte) {
//        Edition edition = new Edition();
//        edition.getIsbn().setNumeroIsbn(isbn);
//        edition.chargerEdition();
//        if( this.map.containsKey(isbn)) {
//            LigneCommande i= this.map.get(isbn);
//            i.delta(-qte);
//            if(i.getQte()<1) {
//                del( isbn);
//            }
//        }
//    }
//  
//    
    public void del(String isbn) {
        this.commande.delLigneCommande(isbn);
        this.commande.calcPrixCommande();
    }
//

    public void clear() {
        this.commande.clear();
        this.commande.calcPrixCommande();
    }

    public void qtyChange(String isbn,int qte){
        this.commande.qtyChangeLigne(isbn,qte);
        this.commande.calcPrixCommande();
    }

    public Commande getCommande() {
        return this.commande;
    }
//
//    public float getTotal() {
//        return -1;
//    }
//
//    public int getQty() {
//        return map.size();
//    }
 public void sauvegarderCommande(String pseudo, String date, String ip){
     Bdd bdd = new Bdd();
     Connection con = bdd.connecterBdd();

     
     try{
         String query = "INSERT INTO COMMANDE (PSEUDO, DATECOMMANDE, IPCOMMANDE, NUMEROCOMMANDE) VALUES (?,?,?,?)";
         PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, pseudo);
            stmt.setString(2, date);
            stmt.setString(3, ip);
            stmt.setString(4,"");
            
            stmt.executeUpdate();
            stmt.close();
     } catch (SQLException ex){
         System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
     }
   }  
   public void sauvegarderLigneCommande(ArrayList<LigneCommande> lc,int id){
       Bdd bdd = new Bdd();
     Connection con = bdd.connecterBdd();
    
     for (LigneCommande c : lc){
     try{
         
         String query = "INSERT INTO LIGNECOMMANDE (ISBN,IDCOMMANDE,QUANTITECOMMANDE,REDUCTION,TVAAPPLIQUEE,MONTANTLIGNECOMMANDE) VALUES (?,?,?,?,?,?)";
         PreparedStatement stmt = con.prepareStatement(query);
         String s = c.getPrixTTC().replaceAll(",", ".");
         Float num = Float.parseFloat(s);
            stmt.setString(1, c.getIsbn());
            stmt.setInt(2, id);
            stmt.setInt(3, c.getQte());
            stmt.setFloat(4, c.getReduc());
            stmt.setFloat(5, c.getTvaAppli());
            stmt.setFloat(6, num);
            
            stmt.executeUpdate();
            stmt.close();
     } catch (SQLException ex){
         System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
     }
     }
   }
     
 
}
