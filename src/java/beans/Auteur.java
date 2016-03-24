


//comment pour les bro

package beans;

import beans.Bdd;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


public class Auteur implements Serializable{

   private ArrayList<Contributeur>  listeContributeur ;

    public Auteur() {
        listeContributeur = new ArrayList<Contributeur>();
    }

    public ArrayList getListeContributeur() {
        
        return listeContributeur;
    }

    public void setListeContributeur(ArrayList listeContributeur) {
        this.listeContributeur = listeContributeur;
    }

    public void chargerAuteur(String isbn){
        
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        
       
        
        String query = "SELECT isbn, titre, coneur.idContributeur, nomContributeur, prenomContributeur"
              + " FROM edition as edi JOIN oeuvre as oeu ON edi.idOeuvre=oeu.idOeuvre JOIN contribution as conon ON oeu.idOeuvre=conon.IdOeuvre"
              + " JOIN CONTRIBUTEUR as coneur ON conon.IDCONTRIBUTEUR=coneur.IDCONTRIBUTEUR WHERE isbn= ? ";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, isbn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Contributeur cont = new Contributeur();
                cont.setIdContributeur(rs.getInt("idContributeur"));
                cont.setNomContributeur(rs.getString("nomContributeur"));
                cont.setPrenomContributeur(rs.getString("prenomContributeur"));
                listeContributeur.add(cont);
            }
           ps.close();
            
          
        } catch (SQLException ex) {
            System.err.println(ex.getErrorCode());;
        }
        
    }
    
     public void recupererAuteurDepuisTitre(String titre){
        
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        
       
        try {
             String query = "SELECT isbn, titre, coneur.idContributeur, nomContributeur, prenomContributeur"
              + " FROM edition as edi JOIN oeuvre as oeu ON edi.idOeuvre=oeu.idOeuvre JOIN contribution as conon ON oeu.idOeuvre=conon.IdOeuvre"
              + " JOIN CONTRIBUTEUR as coneur ON conon.IDCONTRIBUTEUR=coneur.IDCONTRIBUTEUR WHERE titre= ? ";

            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, titre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Contributeur cont = new Contributeur();
                cont.setIdContributeur(rs.getInt("idContributeur"));
                cont.setNomContributeur(rs.getString("nomContributeur"));
                cont.setPrenomContributeur(rs.getString("prenomContributeur"));
                listeContributeur.add(cont);
            }
           ps.close();
            
          
        } catch (SQLException ex) {
            System.err.println(ex.getErrorCode());;
        }
        
    }
   
    
     public Boolean enleverAuteur(Contributeur contributeur){
        Boolean ok = false;
        
         ArrayList<Contributeur> listeC= this.getListeContributeur();
         Iterator <Contributeur> it = listeC.iterator();
        
        while (it.hasNext()){
            Contributeur ContributeurEnCours = it.next();
            System.out.println("ContributeurEncours ="+ContributeurEnCours.getNomContributeur());
            if(contributeur==ContributeurEnCours){
                it.remove();
                ok=true;
            }
        }
        
        return ok;
    }
    
   
}
