package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ListeMotCle implements Serializable{
    private ArrayList<Tag> listeMc;
    
    public ListeMotCle(){
    }

    public ArrayList<Tag> getListeMc() {
        return listeMc;
    }

    public void setListeMc(ArrayList<Tag> listeMc) {
        this.listeMc = listeMc;
    }
    
    //Fonction qui enlèv un mot clef d'un objet ListeMotClef
    public Boolean enleverMotClef(Tag tag){
        Boolean ok = false;
        
         ArrayList<Tag> listeMc= this.getListeMc();
         Iterator <Tag> it = listeMc.iterator();
        
        while (it.hasNext()){
            Tag tagEnCours = it.next();
            System.out.println("tagEncours ="+tagEnCours.getNom());
            if(tag==tagEnCours){
                it.remove();
                ok=true;
            }
        }
        
        return ok;
    }
    
    
    
}
