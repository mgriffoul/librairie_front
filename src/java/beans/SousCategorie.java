

package beans;

import java.io.Serializable;



public class SousCategorie implements Serializable{
    
      private String nomSousCategorie;
   private Integer idSousCategorie;
   
    public SousCategorie() {
    }

    public SousCategorie(String nomSousCategorie, String nomCategorie) {
        this.nomSousCategorie = nomSousCategorie;
    }


    public String getNomSousCategorie() {
        return nomSousCategorie;
    }

    public void setNomSousCategorie(String nomSousCategorie) {
        this.nomSousCategorie = nomSousCategorie;
    }

    public Integer getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(Integer idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

   
    
    
}
