package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class Panier implements Serializable {

    //HashMap<String, LigneCommande> map;
    Commande commande = null;

    public Panier() {
        this.commande = new Commande();
    }

    public void add(String isbn) {
        LigneCommande ligne = new LigneCommande();
        Edition edition = new Edition();
        Isbn is = new Isbn();
        is.setNumeroIsbn(isbn);
        edition.setIsbn(is);
        edition.chargerEdition();
        add(isbn, edition, ligne, 1);
 

    }

    public void add(String isbn, Edition edition, LigneCommande ligne, int qte) {
            float reduc;
            reduc = ligne.getReduc();
            ligne = new LigneCommande(edition, qte, reduc); 
            edition.chargerEdition();
            String str = edition.getTitre();
            ligne.setTitreLivre(str);
            ligne.setIsbn(isbn);
            ligne.setPrixTTC(edition.getPrixTtc());
            ligne.setNomAuteur(edition.getNomAuteur());     
            this.commande.addLigneCommande(ligne);
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
//    public void del(String isbn) {
//        this.map.remove(isbn);
//    }
//
//    public void clear() {
//        map.clear();
//    }
//
//    public boolean isEmpty() {
//        return map.isEmpty();
//    }
//
//    public Collection getList() {
//        return map.values();
//    }
//
//    public float getTotal() {
//        return -1;
//    }
//
//    public int getQty() {
//        return map.size();
//    }

}
