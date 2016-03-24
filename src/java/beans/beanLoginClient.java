/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import beans.Bdd;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi211
 */
public class beanLoginClient {

    public boolean check(String login, String password) {

        if (login == null) {
            return false;
        }
        if (password == null) {
            return false;
        }
        if (login.trim().isEmpty()) {
            return false;
        }
        if (password.trim().isEmpty()) {
            return false;
        }
        if (checkCo(login, password)) {
            return true;
        }

        return false;

    }

    public boolean checkCo(String login, String password) {
        ResultSet rst;
        Statement st;

        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();

        try {
            String req = "SELECT * FROM utilisateur WHERE loginutilisateur = '" + login + "' AND passwordutilisateur = '" + password + "'";
//                    + "HASHBYTES('md5',('" + password + "'))";

            st = con.createStatement();
            rst = st.executeQuery(req);
            
            
            if(rst.next()){
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.getErrorCode();
        }

        return false;
    }

}
