package beans;

public class Transporteur {
    private String nomTransporteur;
    private String adresseTransporteur;
    private String telTransporteur;
    private String siret;
    private Integer idTransporteur;

    public Transporteur() {
    }

    public Transporteur(String nomTransporteur, String adresseTransporteur, String telTransporteur, String siret, Integer idTransporteur) {
        this.nomTransporteur = nomTransporteur;
        this.adresseTransporteur = adresseTransporteur;
        this.telTransporteur = telTransporteur;
        this.siret = siret;
        this.idTransporteur = idTransporteur;
    }

    public String getNomTransporteur() {
        return nomTransporteur;
    }

    public void setNomTransporteur(String nomTransporteur) {
        this.nomTransporteur = nomTransporteur;
    }

    public String getAdresseTransporteur() {
        return adresseTransporteur;
    }

    public void setAdresseTransporteur(String adresseTransporteur) {
        this.adresseTransporteur = adresseTransporteur;
    }

    public String getTelTransporteur() {
        return telTransporteur;
    }

    public void setTelTransporteur(String telTransporteur) {
        this.telTransporteur = telTransporteur;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public Integer getIdTransporteur() {
        return idTransporteur;
    }

    public void setIdTransporteur(Integer idTransporteur) {
        this.idTransporteur = idTransporteur;
    }

      @Override
    public String toString() {
        return nomTransporteur;
    }  
    
    
}
