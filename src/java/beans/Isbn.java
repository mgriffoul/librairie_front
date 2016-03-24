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
public class Isbn implements Serializable{

    private String numeroIsbn;

    public Isbn() {
    }

    public String getNumeroIsbn() {
        return numeroIsbn;
    }

    public void setNumeroIsbn(String numeroIsbn) {
        this.numeroIsbn = numeroIsbn;
    }

   
 

    
    
}
