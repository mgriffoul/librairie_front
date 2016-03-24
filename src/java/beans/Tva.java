/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author GriffMacBook
 */
public class Tva implements Serializable{
    
    private Integer idTva;
    private Float tauxTva;

    public Tva() {
    }

    public Integer getIdTva() {
        return idTva;
    }

    public Float getTauxTva() {
        return tauxTva;
    }

    public void setIdTva(Integer idTva) {
        this.idTva = idTva;
    }

    public void setTauxTva(Float tauxTva) {
        this.tauxTva = tauxTva;
    }

    @Override
    public String toString() {
        String s = String.valueOf(tauxTva);
        return s;
    }
    
    
    
    
    
}
