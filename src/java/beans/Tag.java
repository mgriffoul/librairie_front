package beans;

import beans.Bdd;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Vector;

public class Tag implements Serializable{

    private String nom;
    private Integer id;

    public Tag() {
    }

    public Tag(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector creerVecteur() {
        Vector vv = new Vector();
        vv.add(this);
        return vv;
    }

    public Boolean saveTag() {
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        String query = "INSERT INTO TAG(NOMTAG) VALUES (?)";
        try {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, this.nom);
                int result = stmt.executeUpdate();
                if (result == 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
        }
        lbd.decoBdd(con);
        return true;
    }

    public static void affectTag(Edition oe, Tag mc) {

        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        try {
            PreparedStatement st = con.prepareStatement(mc.getNom());
            int id = mc.getId();
            String isbn = oe.getIsbn().getNumeroIsbn();

            st.setString(1, isbn);
            st.setInt(2, id);
            int result = st.executeUpdate();

            st.close();

        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
        }
        lbd.decoBdd(con);
    }

    public void getIdTag() {
        
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        String query = "SELECT IDTAG FROM TAG WHERE NOMTAG = ?";

        try {
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, this.nom);
            
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.setId(rs.getInt("IDTAG")); 
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());

        }
    }

    public static void retirerMc(String chaine) {
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        int res;

        try {
            Statement st = con.createStatement();
            res = st.executeUpdate(chaine);
        } catch (SQLException ex) {
            System.err.println("SQLException:" + ex.getErrorCode() + "/" + ex.getMessage());
        }

        lbd.decoBdd(con);
    }

    public Boolean checkTag(String tag) {

        Boolean ok = false;
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();

        try {
            Statement stmt = con.createStatement();
            String query = "SELECT nomtag FROM tag WHERE nomtag='" + tag.replaceAll("'", "''") + "' ";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                ok = false;
            } else {
                ok = true;
            }

        } catch (SQLException ex) {
            System.err.println("Probleme acces bdd depuis jdiaEntrerCategorie");
        }
        lbd.decoBdd(con);

        return ok;
    }

    public Boolean updateTag() {
        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();
        
        String query = "UPDATE tag SET nomtag = ? WHERE idtag = ? ";
        try {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, this.nom);
                ps.setInt(2, this.id);
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException ex) {

            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tag other = (Tag) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    
    
    
}
