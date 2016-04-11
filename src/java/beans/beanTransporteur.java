package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class beanTransporteur implements Serializable {

    List<Transporteur> listeTransporteur;

    public beanTransporteur() {
        getTransporteur();
    }

    public boolean isEmpty(){
        return listeTransporteur.isEmpty();
    }
    
    
    
    public List<Transporteur> getTransporteur() {
        listeTransporteur = new ArrayList();

        Bdd bdd = new Bdd();
        Connection con = bdd.connecterBdd();
        try {
            String query = "SELECT * FROM TRANSPORTEUR WHERE STATUTTRANSPORTEUR='A'";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("on entre dans le rs");
            while (rs.next()) {
                Transporteur tr = new Transporteur();
                tr.setIdTransporteur(rs.getInt("IDTRANSPORTEUR"));
                tr.setNomTransporteur(rs.getString("NOMTRANSPORTEUR"));
                tr.setSiret(rs.getString("SIRETTRANSPORTEUR"));
                tr.setAdresseTransporteur(rs.getString("ADRESSETRANSPORTEUR1"));
                tr.setTelTransporteur(rs.getString("TELTRANSPORTEUR"));
                listeTransporteur.add(tr);
                System.out.println("on ajoute au rs");
                System.out.println(tr);
                
                
                rs.close();
                stmt.close();
                System.out.println(listeTransporteur);
            }
            Collections.sort(listeTransporteur, new comparerValeur());
        } catch (SQLException ex) {
            return null;
        }

        return listeTransporteur;
    }

    class comparerValeur implements Comparator<Transporteur>{

        @Override
        public int compare(Transporteur o1, Transporteur o2) {
        String nom1 = o1.getNomTransporteur();
        String nom2 = o2.getNomTransporteur();
        return nom1.compareToIgnoreCase(nom2);
        }
        
    }
    
    public List<Transporteur> getList() {
        return listeTransporteur;
    }    
    
    
}
