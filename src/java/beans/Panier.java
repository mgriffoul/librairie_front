package beans;

import java.io.Serializable;

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

}
