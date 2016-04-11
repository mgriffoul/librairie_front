package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class beanAdresse implements Serializable{
    
    List<Adresse> listeAdresse;
    

    public beanAdresse(String pseudo, String natureAdresse) {
        getAdresse(pseudo, natureAdresse);
    }

    public boolean isEmpty(){
        return listeAdresse.isEmpty();
    }

    
    public List<Adresse> getAdresse(String pseudo, String natureAdresse){
        listeAdresse = new ArrayList();
        
        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();
        try{
            String query = "SELECT * FROM ADRESSE WHERE pseudo='"+pseudo+"' AND NATUREADRESSE ='"+natureAdresse+"'";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
        
        while (rs.next()){
            Adresse ad = new Adresse();
            ad.setIdAdresse(rs.getInt("IDADRESSE"));
            //ad.getbClient().setPseudoClient(rs.getString("PSEUDO"));
            //ad.setNomClient(rs.getString("NOMCLIENT"));
            //ad.setPrenomClient(rs.getString("PRENOMCLIENT"));
            ad.setAdresseClient(rs.getString("ADRESSECLIENT"));
            ad.setAdresseComplement(rs.getString("ADRESSECOMPLEMENT"));
            ad.setCodePostal(rs.getString("CODEPOSTAL"));
            ad.setVille(rs.getString("VILLE"));
            ad.setPays(rs.getString("PAYS"));
            ad.setNatureAdresse(rs.getString("NATUREADRESSE"));
            listeAdresse.add(ad);
            
            Collections.sort(listeAdresse, new ComparerValeur());
            rs.close();
            stmt.close();
            
        } 
        
        
        }catch (SQLException ex){
                return null;
                }
        bdd.decoBdd(con);
        return listeAdresse;
    }
    
    public Collection getList() {
        return listeAdresse;
    }
       
}

class ComparerValeur implements Comparator<Adresse>{
    
    @Override
    public int compare(Adresse o1, Adresse o2) {
        String nom1 = o1.getAdresseClient();
        String nom2 = o2.getAdresseClient();
        return nom1.compareToIgnoreCase(nom2);
    }    
     
}
