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

    public void del(String isbn) {
        this.commande.delLigneCommande(isbn);
        this.commande.calcPrixCommande();
    }

    public void clear() {
        this.commande.clear();
        this.commande.calcPrixCommande();
    }

    public void qtyChange(String isbn, int qte) {
        this.commande.qtyChangeLigne(isbn, qte);
        this.commande.calcPrixCommande();
    }

    public Commande getCommande() {
        return this.commande;
    }

}
