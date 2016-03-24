/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author cdi206
 */
public class beanClient implements Serializable{
    
    private String pseudoClient;
    private String nomClient;
    private String prenomClient;
    private String emailClient;
    private String telephoneClient;
    private String datenaissance;
    private String civiliteclient;
    private String mdpClient;
    private String adresseFac;
    private String complementFac;
    private String codePostalFac;
    private String villeFac;
    private String paysFac;
    private String adresseLiv;
    private String complementLiv;
    private String codePostalLiv;
    private String villeLiv;
    private String paysLiv;
    
    
    //constructeurs

    public beanClient() {
    }

    public beanClient(String pseudoClient, String nomClient, String prenomClient, String emailClient, 
            String telephoneClient, String datenaissance, String civiliteclient, String mdpClient,String adresseFac,
            String complementFac, String codePostalFac, String villeFac, String paysFac, String adresseLiv,
            String complementLiv, String codePostalLiv,String villeLiv,String paysLiv) {
        this.pseudoClient = pseudoClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.emailClient = emailClient;
        this.telephoneClient = telephoneClient;
        this.datenaissance = datenaissance;
        this.civiliteclient = civiliteclient;
        this.mdpClient = mdpClient;
        this.adresseFac=adresseFac;
        this.complementFac=complementFac;
        this.codePostalFac=codePostalFac;
        this.villeFac=villeFac;
        this.paysFac=paysFac;
        this.adresseLiv=adresseLiv;
        this.complementLiv=complementLiv;
        this.codePostalLiv=codePostalLiv;
        this.villeLiv=villeLiv;
        this.paysLiv=paysLiv;
        
    }
    
    //getters et setters

    public String getPseudoClient() {
        return pseudoClient;
    }

    public void setPseudoClient(String pseudoClient) {
        this.pseudoClient = pseudoClient;
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

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getCiviliteclient() {
        return civiliteclient;
    }

    public void setCiviliteclient(String civiliteclient) {
        this.civiliteclient = civiliteclient;
    }

    public String getMdpClient() {
        return mdpClient;
    }

    public void setMdpClient(String mdpClient) {
        this.mdpClient = mdpClient;
    }

    public String getAdresseFac() {
        return adresseFac;
    }

    public void setAdresseFac(String adresseFac) {
        this.adresseFac = adresseFac;
    }

    public String getComplementFac() {
        return complementFac;
    }

    public void setComplementFac(String complementFac) {
        this.complementFac = complementFac;
    }

    public String getCodePostalFac() {
        return codePostalFac;
    }

    public void setCodePostalFac(String codePostalFac) {
        this.codePostalFac = codePostalFac;
    }

    public String getVilleFac() {
        return villeFac;
    }

    public void setVilleFac(String villeFac) {
        this.villeFac = villeFac;
    }

    public String getPaysFac() {
        return paysFac;
    }

    public void setPaysFac(String paysFac) {
        this.paysFac = paysFac;
    }

    public String getAdresseLiv() {
        return adresseLiv;
    }

    public void setAdresseLiv(String adresseLiv) {
        this.adresseLiv = adresseLiv;
    }

    public String getComplementLiv() {
        return complementLiv;
    }

    public void setComplementLiv(String complementLiv) {
        this.complementLiv = complementLiv;
    }

    public String getCodePostalLiv() {
        return codePostalLiv;
    }

    public void setCodePostalLiv(String codePostalLiv) {
        this.codePostalLiv = codePostalLiv;
    }

    public String getVilleLiv() {
        return villeLiv;
    }

    public void setVilleLiv(String villeLiv) {
        this.villeLiv = villeLiv;
    }

    public String getPaysLiv() {
        return paysLiv;
    }

    public void setPaysLiv(String paysLiv) {
        this.paysLiv = paysLiv;
    }
    
}
