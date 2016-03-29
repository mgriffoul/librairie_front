package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class ConnexionForm {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_UTIL = "utilisateur";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur(HttpServletRequest request) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);

        Utilisateur utilisateur = null;

        ResultSet rst;
        Statement st;

        Bdd lbd = new Bdd();
        Connection con = lbd.connecterBdd();

        try {
            String query = "SELECT * FROM CLIENT WHERE MAILCLIENT='" + email + "'";
            st = con.createStatement();
            rst = st.executeQuery(query);

            if (rst.next()) {
                String mdp = rst.getString("MOTDEPASSECLIENT");

                String req = "SELECT * FROM CLIENT WHERE MAILCLIENT = '" + email + "' AND MOTDEPASSECLIENT = HASHBYTES('md5',('" + motDePasse + "'))";
                st = con.createStatement();
                rst = st.executeQuery(req);

                if (rst.next()) {
                    utilisateur = new Utilisateur();

                    /* Validation du champ email. */
                    try {
                        validationEmail(email);
                    } catch (Exception e) {
                        setErreur(CHAMP_EMAIL, e.getMessage());
                    }
                    utilisateur.setEmail(email);

                    /* Validation du champ mot de passe. */
                    try {
                       validationMotDePasse(motDePasse);
                    } catch (Exception e) {
                        setErreur(CHAMP_PASS, e.getMessage());
                    }
                    utilisateur.setMotDePasse(motDePasse);
                    
                }
                lbd.decoBdd(con);
            }else{
                 setErreur(CHAMP_UTIL, "Cette adresse e-mail n'existe pas!");
            }

        } catch (SQLException ex) {
           
        }
        /* Initialisation du résultat global de la validation. */
        if (erreurs.isEmpty()) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }
        return utilisateur;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail(String email) throws Exception {
        if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception("Merci de saisir une adresse mail valide.");
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse(String motDePasse) throws Exception {
        if (motDePasse != null) {
            if (motDePasse.length() < 3) {
                throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir votre mot de passe.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }
}
