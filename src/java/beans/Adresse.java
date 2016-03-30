package beans;

import java.io.Serializable;



public class Adresse implements Serializable{
    
    private beanClient bClient;
    private String nomClient;
    private String prenomClient;
    private int idAdresse;
    private String adresseClient;
    private String adresseComplement;
    private String codePostal;
    private String ville;
    private String pays;
    private String natureAdresse;

    public Adresse() {
    }

    public Adresse(beanClient bClient, String nomClient, String prenomClient, int idAdresse, String adresseClient, String adresseComplement, String codePostal, String ville, String pays, String natureAdresse) {
        this.bClient = bClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.idAdresse = idAdresse;
        this.adresseClient = adresseClient;
        this.adresseComplement = adresseComplement;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.natureAdresse = natureAdresse;
    }

    public beanClient getbClient() {
        return bClient;
    }

    public void setbClient(beanClient bClient) {
        this.bClient = bClient;
    }

    public int getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getAdresseComplement() {
        return adresseComplement;
    }

    public void setAdresseComplement(String adresseComplement) {
        this.adresseComplement = adresseComplement;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getNatureAdresse() {
        return natureAdresse;
    }

    public void setNatureAdresse(String natureAdresse) {
        this.natureAdresse = natureAdresse;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }
    
    
    
    
}
