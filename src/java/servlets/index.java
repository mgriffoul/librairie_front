package servlets;

import beans.Adresse;
import beans.Bdd;
import beans.Categorie;
import beans.Commande;
import beans.Commentaire;
import beans.ConnexionForm;
import beans.Edition;
import beans.Isbn;
import beans.LigneCommande;
import beans.Panier;
import beans.SousCategorie;
import beans.Utilisateur;
import beans.beanAdresse;
import beans.beanClient;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "index", urlPatterns = {"/index"})
public class index extends HttpServlet {

    private Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String url = "/WEB-INF/index.jsp";
        HttpSession session = request.getSession();

        String ATT_USER = "utilisateur";
        String ATT_FORM = "form";
        String ATT_SESSION_USER = "sessionUtilisateur";

        String section = "";
        String ss1 = "/WEB-INF/view/sidebarCategorie.jsp";
        String ss2 = "/WEB-INF/view/carousel.jsp";
        String ss3 = "/WEB-INF/view/smallBook.jsp";
        String ss4 = "/WEB-INF/view/focus.jsp";
        String ss5 = "/WEB-INF/view/catalogueCategorie.jsp";
        String ss6 = "/WEB-INF/view/recherche.jsp";
        String ss7 = "/WEB-INF/view/details.jsp";
        String ss8 = "/WEB-INF/view/login.jsp";
        String ss9 = "/WEB-INF/view/logout.jsp";
        String ss10 = "/WEB-INF/view/register.jsp";
        String ss11 = "/WEB-INF/view/panier.jsp";

//SECTION NULL ----> INDEX        
        if (request.getParameter("section") == null) {
            System.out.println("entree index");
            section = "/WEB-INF/S2.jsp";
            //recuperation de la liste des categorie
            ArrayList<Categorie> listeCategorie = Categorie.initSidebar();

            //récupération des livres du momment
            ArrayList<Edition> listeEditionMoment = Edition.chargerLivreMoment();

            request.setAttribute("ss1", ss1);
            request.setAttribute("ss2", ss2);
            request.setAttribute("ss3", ss3);

            //envoie requete
            request.setAttribute("Edition", listeEditionMoment);
            request.setAttribute("Categorie", listeCategorie);

        }
//FIN DE LA SECTION=NULL (INDEX)

//SECTION = FOCUS (zoom du livre)        
        if ("focus".equals(request.getParameter("section"))) {

            section = "/WEB-INF/S3.jsp";
            Boolean presComent = false;
            Boolean presAchat = false;

            Utilisateur util = (Utilisateur) session.getAttribute("sessionUtilisateur");

            Edition edit = new Edition();
            Isbn isb = new Isbn();
            isb.setNumeroIsbn(request.getParameter("value"));
            edit.setIsbn(isb);
            edit.chargerEdition();

            ArrayList<Categorie> listeCategorie = Categorie.initSidebar();

            //Vérification de la présence d'un commentaire déjà laissé par l'utilisateur sur cette edition
            if (util != null) {
                presComent = util.verifPrevComent(isb.getNumeroIsbn());
            }

            //Vérification de la presence d'un achet du livre par l'utilisateur
            if (util != null) {
                presAchat = util.verifierPrevAchat(isb.getNumeroIsbn());
            }

            //récuperation de la liste des commentaires associés à l'edition en focus
            ArrayList<Commentaire> listeCommentaire = Commentaire.recupererCommentaire(isb.getNumeroIsbn());

            //si un commentaire vient d'être laissé
            if (util != null) {
                if ("Valider".equals(request.getParameter("go"))) {
                    System.out.println("entrée dans le test request");
                    Boolean ok = false;

                    if (request.getParameter("note") != null && request.getParameter("coment") != null) {

                        if (!request.getParameter("note").isEmpty() && request.getParameter("coment").length() > 1) {

                            System.out.println("NO erreur detectée");
                            String s = request.getParameter("coment");
                            System.out.println("SIZE REQU" + s.length());
                            ok = true;

                            Commentaire com = new Commentaire();
                            com.setCommentaire(request.getParameter("coment"));
                            com.setNote(Integer.valueOf(request.getParameter("note")));
                            com.setPseudo(util.getPseudo());

                            java.util.Date date = new java.util.Date();
                            com.setDate(new Timestamp(date.getTime()));

                            System.out.println("envoie à soumission bdd");
                            com.soumettreCom(isb.getNumeroIsbn());
                            request.setAttribute("fait", "done");
                        } else {
                            System.out.println("erreur detectée");
                            request.setAttribute("Erreur", "La note ou le commentaire ne peuvent pas etre vide.");
                            request.setAttribute("com", "let");
                        }
                    }

                }
            }

            if ("ok".equals(request.getParameter("det"))) {
                request.setAttribute("ss7", ss7);
            }

            if (presAchat) {
                request.setAttribute("presAchat", "vrai");
            }

            if (presComent) {
                request.setAttribute("presComent", "vrai");
            }
            if (request.getParameter("com") != null) {
                request.setAttribute("com", "let");
            }
            request.setAttribute("sessionUtilisateur", util);
            request.setAttribute("ss1", ss1);
            request.setAttribute("ss4", ss2);
            request.setAttribute("Categorie", listeCategorie);
            request.setAttribute("Edition", edit);
            request.setAttribute("Commentaire", listeCommentaire);

        }
//SECTION = FOCUS (zoom du livre)        
        if ("focus".equals(request.getParameter("section"))) {

            section = "/WEB-INF/S3.jsp";
            Boolean presComent = false;
            Boolean presAchat = false;

            Utilisateur util = (Utilisateur) session.getAttribute("sessionUtilisateur");

            Edition edit = new Edition();
            Isbn isb = new Isbn();
            isb.setNumeroIsbn(request.getParameter("value"));
            edit.setIsbn(isb);
            edit.chargerEdition();


            ArrayList<Categorie> listeCategorie = Categorie.initSidebar();

            //Vérification de la présence d'un commentaire déjà laissé par l'utilisateur sur cette edition
            if (util != null) {
                presComent = util.verifPrevComent(isb.getNumeroIsbn());
            }
            System.out.println("presComent :::::" + presComent);
            //Vérification de la presence d'un achat du livre par l'utilisateur dans la base
            if (util != null) {
                presAchat = util.verifierPrevAchat(isb.getNumeroIsbn());
            }
            System.out.println("pres achat :::::" + presAchat);
            //récuperation de la liste des commentaires associés à l'edition en focus
            ArrayList<Commentaire> listeCommentaire = Commentaire.recupererCommentaire(isb.getNumeroIsbn());

            //si un commentaire vient d'être laissé
            if (util != null && !presComent) {
                if ("Valider".equals(request.getParameter("go"))) {
                    
                    Boolean ok = false;

                    if (request.getParameter("note") != null && request.getParameter("coment") != null) {

                        if (!request.getParameter("note").isEmpty() && request.getParameter("coment").length() > 1) {

                            
                            String s = request.getParameter("coment");
                          
                            ok = true;

                            Commentaire com = new Commentaire();
                            com.setCommentaire(request.getParameter("coment"));
                            com.setNote(Integer.valueOf(request.getParameter("note")));
                            com.setPseudo(util.getPseudo());

                            java.util.Date date = new java.util.Date();
                            com.setDate(new Timestamp(date.getTime()));

                            System.out.println("envoie à soumission bdd");
                            com.soumettreCom(isb.getNumeroIsbn());

                        } else {
                            
                            request.setAttribute("Erreur", "La note ou le commentaire ne peuvent pas etre vide.");
                            request.setAttribute("com", "let");
                        }
                    }
                }
            }

            if ("ok".equals(request.getParameter("det"))) {
                request.setAttribute("ss7", ss7);
            }

            if (presAchat) {
                request.setAttribute("presAchat", "vrai");
            }

            if (presComent) {
                request.setAttribute("presComent", "vrai");
            }
            if (request.getParameter("com") != null) {
                request.setAttribute("com", "let");
            }
            request.setAttribute("sessionUtilisateur", util);
            request.setAttribute("ss1", ss1);
            request.setAttribute("ss4", ss2);
            request.setAttribute("Categorie", listeCategorie);
            request.setAttribute("Edition", edit);
            request.setAttribute("Commentaire", listeCommentaire);

        }
//FIN SECTION = FOCUS

//SECTION CATALOGUE PAR CATEGORIE        
        if ("cat".equals(request.getParameter("section"))) {

            section = "/WEB-INF/S4.jsp";

            //recuperation de l'id de la categorie choisie par l'utilisateur
            Integer id = Integer.valueOf(request.getParameter("value"));
            //Idem pour la sous categorie eventuelle
            Integer idSsCate = null;
            if (request.getParameter("ssCat") != null) {
                idSsCate = Integer.valueOf(request.getParameter("ssCat"));
            }

            //Listes à envoyer dans la reponse
            ArrayList<SousCategorie> listeSousCate = new ArrayList();
            ArrayList<Categorie> listeCategorie = Categorie.initSidebar();
            ArrayList<Edition> listeEdition = new ArrayList();

            //chargement de la categorie choisie par l'utilisateur
            Categorie cate = new Categorie(id);
            cate.chargerCategorie();

            //idem pour la sous-Categorie
            SousCategorie sousCateChoisie = new SousCategorie();
            if (idSsCate != null) {
                sousCateChoisie.setIdSousCategorie(idSsCate);
                sousCateChoisie.chargerSousCategorie();
            }

            //Liste sous categories concernees
            listeSousCate = SousCategorie.recupererSousCategorie(id);

            //Liste des editions concernées
            if (idSsCate == null) {

                listeEdition = Edition.editionParCategorie(id);

            } else {

                listeEdition = Edition.editionParSousCategorie(id, idSsCate);
            }
            System.out.println("id = " + id);
            request.setAttribute("idCat", id);
            request.setAttribute("ss1", ss1);
            request.setAttribute("ss5", ss2);
            request.setAttribute("Edition", listeEdition);
            request.setAttribute("Categorie", listeCategorie);
            request.setAttribute("ListeSousCate", listeSousCate);
            request.setAttribute("CateChoisie", cate);
            request.setAttribute("SousCateChoisie", sousCateChoisie);

        }
//FIN SECTION CATALOGUE PAR CATEGORIE       

// DEBUT AFFICHAGE COMPTE CLIENT 
        // affichage nom, prenom...
        Utilisateur util = (Utilisateur) session.getAttribute("sessionUtilisateur");

        if ("acc".equals(request.getParameter("section"))) {
            url = "/WEB-INF/index.jsp?section=acc";
            section = "/WEB-INF/view/client.jsp";
            Bdd bdd = new Bdd();
            Connection con = bdd.connecterBdd();
            String str = util.getPseudo();
            System.out.println("valeur str : " + str);
            String strNumCom = "";
            try {
                String query = "SELECT * FROM client WHERE pseudo = '" + str + "'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                beanClient client = new beanClient();
                String pseudo = "";
                String nomclient = "";
                String prenomclient = "";
                String mailclient = "";
                String civilite = "";
                String datenaissance = "";
                String telClient = "";
                String mdpClient = "";

                while (rs.next()) {
                    client.setCiviliteclient(rs.getString("civilite"));
                    client.setPseudoClient(rs.getString("pseudo"));
                    client.setNomClient(rs.getString("nomclient"));
                    client.setPrenomClient(rs.getString("prenomclient"));
                    client.setEmailClient(rs.getString("mailclient"));
                    client.setDatenaissance(rs.getString("datenaissance"));
                    client.setTelephoneClient(rs.getString("telephoneclient"));
                    client.setMdpClient(rs.getString("motdepasseclient"));

                    civilite = client.getCiviliteclient();
                    pseudo = client.getPseudoClient();
                    nomclient = client.getNomClient();
                    prenomclient = client.getPrenomClient();
                    mailclient = client.getEmailClient();
                    datenaissance = client.getDatenaissance();
                    telClient = client.getTelephoneClient();
                    mdpClient = client.getMdpClient();

                    if (civilite.equals("h")) {
                        civilite = "Homme";
                    }
                    if (civilite.equals("f")) {
                        civilite = "Femme";
                    }
                }

                request.setAttribute("civilite", civilite);
                request.setAttribute("pseudo", pseudo);
                request.setAttribute("nomclient", nomclient);
                request.setAttribute("prenomclient", prenomclient);
                request.setAttribute("mailclient", mailclient);
                request.setAttribute("datenaissance", datenaissance);
                request.setAttribute("telClient", telClient);
                request.setAttribute("mdpClient", mdpClient);

                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }

            // AFFICHAGE ADRESSE FACTURATION
            try {
                String query = "SELECT * FROM adresse WHERE pseudo = '" + str + "' AND natureadresse='f' AND statutadresse='a'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                beanClient client = new beanClient();
                String adresseFac = "";
                String complementFac = "";
                String codePostalFac = "";
                String villeFac = "";
                String paysFac = "";

                while (rs.next()) {
                    client.setAdresseFac(rs.getString("adresseclient"));
                    client.setComplementFac(rs.getString("adressecomplement"));
                    client.setCodePostalFac(rs.getString("codepostal"));
                    client.setVilleFac(rs.getString("ville"));
                    client.setPaysFac(rs.getString("pays"));

                    adresseFac = client.getAdresseFac();
                    complementFac = client.getComplementFac();
                    codePostalFac = client.getCodePostalFac();
                    villeFac = client.getVilleFac();
                    paysFac = client.getPaysFac();

                }

                request.setAttribute("adresseFac", adresseFac);
                request.setAttribute("complementFac", complementFac);
                request.setAttribute("codePostalFac", codePostalFac);
                request.setAttribute("villeFac", villeFac);
                request.setAttribute("paysFac", paysFac);

                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }
            // AFFICHAGE ADRESSE LIVRAISON
            try {
                String query = "SELECT * FROM adresse WHERE pseudo = '" + str + "' AND natureadresse='l' AND statutadresse='a'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                beanClient client = new beanClient();
                String adresseLiv = "";
                String complementLiv = "";
                String codePostalLiv = "";
                String villeLiv = "";
                String paysLiv = "";

                while (rs.next()) {
                    client.setAdresseLiv(rs.getString("adresseclient"));
                    client.setComplementLiv(rs.getString("adressecomplement"));
                    client.setCodePostalLiv(rs.getString("codepostal"));
                    client.setVilleLiv(rs.getString("ville"));
                    client.setPaysLiv(rs.getString("pays"));

                    adresseLiv = client.getAdresseLiv();
                    complementLiv = client.getComplementLiv();
                    codePostalLiv = client.getCodePostalLiv();
                    villeLiv = client.getVilleLiv();
                    paysLiv = client.getPaysLiv();

                }

                request.setAttribute("adresseLiv", adresseLiv);
                request.setAttribute("complementLiv", complementLiv);
                request.setAttribute("codePostalLiv", codePostalLiv);
                request.setAttribute("villeLiv", villeLiv);
                request.setAttribute("paysLiv", paysLiv);

                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
// AFFICHAGE DES COMMANDES DU CLIENT
        float totalCommande = 0f;
        float totalCommandeInter = 0f;

        if ("com".equals(request.getParameter("section"))) {
            url = "/WEB-INF/index.jsp?section=com";
            section = "/WEB-INF/view/ligneCommande.jsp";
            Bdd bdd = new Bdd();
            Connection con = bdd.connecterBdd();
            String str = util.getPseudo();
            System.out.println("valeur str : " + str);
            String strNumCom = "";
            try {
                String query = "SELECT numerocommande, datecommande, statutcommande FROM commande "
                        + "WHERE pseudo='" + str + "' ORDER BY datecommande";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                ArrayList<Commande> listeCom = new ArrayList();
                ArrayList<LigneCommande> llcom = new ArrayList();

                while (rs.next()) {

                    Commande commande = new Commande();
                    commande.setNumeroCommande(rs.getString("numerocommande"));
                    commande.setDateCommande(rs.getDate("datecommande"));
                    commande.setStatutCommande(rs.getString("statutcommande"));
                    strNumCom = commande.getNumeroCommande();
                    commande.setTotalCom(totalCommande);
                    listeCom.add(commande);
                    System.out.println("2e SOUT : " + listeCom);
                }

                request.setAttribute("listeCom", listeCom);
                System.out.println("3e SOUT : " + listeCom);

                rs.close();
                stmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }

            String select = request.getParameter("selection"); // toujours null ???
            System.out.println("test 1 : " + request.getParameter("selection"));

            if (request.getParameter("selection") != null) {
                url = "/WEB-INF/index.jsp?section=com";

                section = "/WEB-INF/view/ligneCommande.jsp";

                try {
                    ArrayList<LigneCommande> llcom = new ArrayList();

                    // LIGNES DE COMMANDE
                    String query2 = "SELECT  titre, quantitecommande, prixventeht, tvaappliquee, reduction "
                            + "FROM OEUVRE AS oe "
                            + "JOIN EDITION AS ed "
                            + "ON oe.IDOEUVRE=ed.IDOEUVRE "
                            + "JOIN LIGNECOMMANDE AS li "
                            + "ON ed.ISBN=li.ISBN "
                            + "JOIN COMMANDE AS co "
                            + "ON li.IDCOMMANDE=co.IDCOMMANDE "
                            + "WHERE co.NUMEROCOMMANDE='" + select + "'";
                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(query2);

                    System.out.println("TEST RECUP VALEUR COMBOBOX JSP CLIENT : " + select);

                    while (rs2.next()) {
                        LigneCommande llCom2 = new LigneCommande();

                        llCom2.setTitreLivre(rs2.getString("titre"));
                        llCom2.setQte(rs2.getInt("quantitecommande"));
                        llCom2.setPrixUHT(rs2.getFloat("prixventeht"));
                        llCom2.setTvaAppli(rs2.getFloat("tvaappliquee"));
                        llCom2.setReduc(rs2.getFloat("reduction"));

                        totalCommandeInter = (((llCom2.getQte() * llCom2.getPrixUHT()) * (1 + (llCom2.getTvaAppli() / 100))) * (1 - (llCom2.getReduc() / 100)));

                        llcom.add(llCom2);
                        totalCommande = totalCommande + totalCommandeInter;
                    }
                    DecimalFormat df = new DecimalFormat("#.00");
                    String strTotal = df.format(totalCommande);

                    request.setAttribute("select", select);
                    request.setAttribute("totalCommande", strTotal);
                    request.setAttribute("llcom", llcom);
                } catch (SQLException ex) {
                    Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
// FIN AFFICHAGE COMPTE CLIENT

//SECTION RECHERCHE
        if (request.getParameter("rec") != null) {

            ArrayList<Edition> listeEdition = new ArrayList();
            String rech = "%" + request.getParameter("rec") + "%";

            section = "/WEB-INF/S5.jsp";

            Bdd bdd = new Bdd();
            Connection con = bdd.connecterBdd();

            try {
                String query = "SELECT dbo.EDITION.ISBN, dbo.OEUVRE.TITRE, dbo.OEUVRE.SOUSTITRE, dbo.EDITEUR.NOMEDITEUR, dbo.CONTRIBUTEUR.NOMCONTRIBUTEUR"
                        + " FROM dbo.EDITION LEFT JOIN\n"
                        + " dbo.CONTRIBUTEUR ON dbo.EDITION.IDTRADUCTEUR = dbo.CONTRIBUTEUR.IDCONTRIBUTEUR AND \n"
                        + " dbo.EDITION.IDPREFACEUR = dbo.CONTRIBUTEUR.IDCONTRIBUTEUR LEFT JOIN"
                        + " dbo.OEUVRE ON dbo.EDITION.IDOEUVRE = dbo.OEUVRE.IDOEUVRE LEFT JOIN"
                        + " dbo.EDITEUR ON dbo.EDITION.IDEDITEUR = dbo.EDITEUR.IDEDITEUR "
                        + " WHERE titre LIKE '" + rech + "' OR SOUSTITRE LIKE '" + rech + "' "
                        + " OR NOMEDITEUR LIKE '" + rech + "' OR NOMCONTRIBUTEUR LIKE '" + rech + "' ";

                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Edition edition = new Edition();
                    Isbn isbn = new Isbn();
                    isbn.setNumeroIsbn(rs.getString("isbn"));
                    edition.setIsbn(isbn);
                    edition.chargerEdition();
                    if (edition.getStock() != null) {
                        if (edition.getStock() > 0) {
                            listeEdition.add(edition);
                        }
                    }
                }
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("ss1", ss1);
            request.setAttribute("ss6", ss2);
            request.setAttribute("Recherche", request.getParameter("rec"));
            request.setAttribute("Edition", listeEdition);

        }
//FIN SECTION RECHERCHE

        if ("reg".equals(request.getParameter("section"))) {
            section = "/WEB-INF/S1.jsp";
            request.setAttribute("ss", ss10);
        }

        if ("loggout".equals(request.getParameter("section"))) {
            session.invalidate();
            section = "/WEB-INF/S1.jsp";
            request.setAttribute("ss", ss9);

        }

        //DEBUT SECTION LOG
        if ("log".equals(request.getParameter("section"))) {
            section = "/WEB-INF/S1.jsp";
            if ("submit".equals(request.getParameter("action"))) {
                /* Préparation de l'objet formulaire */
                ConnexionForm form = new ConnexionForm();

                /* Traitement de la requête et récupération du bean en résultant */
                Utilisateur utilisateur = form.connecterUtilisateur(request);
                /**
                 * Si aucune erreur de validation n'a eu lieu, alors ajout du
                 * bean Utilisateur à la session, sinon suppression du bean de
                 * la session.
                 */
                if (form.getErreurs().isEmpty()) {
                    session.setAttribute(ATT_SESSION_USER, utilisateur);
                } else {
                    session.setAttribute(ATT_SESSION_USER, null);
                }

                /* Stockage du formulaire et du bean dans l'objet request */
                request.setAttribute(ATT_FORM, form);
                request.setAttribute(ATT_USER, utilisateur);

            }

            request.setAttribute("ss", ss8);
        }
// SECTION PANIER
        if ("pan".equals(request.getParameter("action"))) {

            Panier bPanier = (Panier) session.getAttribute("panier");

            if (bPanier == null) {
                bPanier = new Panier();
                session.setAttribute("panier", bPanier);
            }
            if (request.getParameter("add") != null) {
                bPanier.add(request.getParameter("add"));
            }
            if (request.getParameter("del") != null) {
                bPanier.del(request.getParameter("del"));
            }
            if (request.getParameter("clear") != null) {
                bPanier.clear();
            }
            if (request.getParameter("qte") != null) {
                bPanier.qtyChange(request.getParameter("isbn"), Integer.parseInt(request.getParameter("qte")));
            }
            Commande commande = bPanier.getCommande();
            session.setAttribute("list", commande.getLigneCommande());

        }
        if ("pan".equals(request.getParameter("section"))) {
            section = "/WEB-INF/S1.jsp";
            Panier bPanier = (Panier) session.getAttribute("panier");

            if (bPanier == null) {
                bPanier = new Panier();
                session.setAttribute("panier", bPanier);
            }

            Commande commande = bPanier.getCommande();

            request.setAttribute("commande", commande);
            request.setAttribute("ss", ss11);
            request.setAttribute("list", commande.getLigneCommande());
        }
//FIN SECTION PANIER

//SECTION VALIFATION DU PANIER 
        //VALIDATION ADRESSE

        if ("choixadresse".equals(request.getParameter("section"))){
        Utilisateur ut = (Utilisateur) session.getAttribute("sessionUtilisateur");    
        if (request.getParameter("doIt") != null){
        if (ut != null){              
            url = "./WEB-INF/view/adresse.jsp";
                beanAdresse adFacturation = new beanAdresse(ut.getPseudo(), "F");
                session.setAttribute("adressefacturation", adFacturation.getList());
                request.setAttribute("adressefacturation", adFacturation.getList());
//=======
//        if ("choixAdresse".equals(request.getParameter("section"))) {
//            if (request.getParameter("doIt") != null) {
//                url = "./WEB-INF/view/adresse.jsp";
//
//                beanAdresse adrBeanFacturation = (beanAdresse) session.getAttribute("adresselivraison");
//                if (adrBeanFacturation == null) {
//                    adrBeanFacturation = new beanAdresse(session.getAttribute("login").toString(), "F");
//                    session.setAttribute("adressefacturation", adrBeanFacturation.getList());
//                }
//                adrBeanFacturation.getAdresse(session.getAttribute("login").toString(), "F");
//                request.setAttribute("adressefacturation", adrBeanFacturation.getList());
//>>>>>>> 5809e460c1f37800edbd7e45199ba935f96571ec

                beanAdresse adLivraison = new beanAdresse(ut.getPseudo(), "L");
                session.setAttribute("adresselivraison", adLivraison.getList());
                request.setAttribute("adresselivraison", adLivraison.getList());

            } else {
                url = "./WEB-INF/view/panier.jsp";
            }


        }
        // FIN VALIDATION ADRESSE  
        // AJOUT ADRESSE
        if ("nouvelleadresse".equals(request.getParameter("section"))) {
            url = "/WEB-INF/view/jspNewAdresse.jsp";
        }

        
        if ("sauvegadresse".equals(request.getParameter("section"))){

            Adresse ad = new Adresse();
            System.out.println("on va ajouter une adresse");
            ut = (Utilisateur) session.getAttribute("sessionUtilisateur");

            ad.sauvegarderAdresse(ut.getPseudo(), request.getParameter("adresseClient"),request.getParameter("complementAdresse"),request.getParameter("codePostal"),request.getParameter("ville"),request.getParameter("pays"),request.getParameter("natureAdresse"));
            
             if (request.getParameter("doIt") != null) {
                url = "/WEB-INF/view/adresse.jsp";
                beanAdresse adFacturation = new beanAdresse(ut.getPseudo(), "F");


                session.setAttribute("adressefacturation", adFacturation.getList());
                request.setAttribute("adressefacturation", adFacturation.getList());

                beanAdresse adLivraison = new beanAdresse(ut.getPseudo(), "L");
                session.setAttribute("adresselivraison", adLivraison.getList());
                request.setAttribute("adresselivraison", adLivraison.getList());

            } else {
                url = "./WEB-INF/view/panier.jsp";
            }
        }
        //FIN AJOUT ADRESSE

        
        // VALIDATION PANIER
        if ("validCommande".equals(request.getParameter("section"))){
            
            if (request.getParameter("doIt") != null){
                
                Panier p = (Panier)session.getAttribute("panier");
                System.out.println("panier="+p);
                ut = (Utilisateur) session.getAttribute("sessionUtilisateur");
                String ip = InetAddress.getLoopbackAddress().getHostAddress();
                Calendar c = Calendar.getInstance();
                Date date = c.getTime();
                SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
                String d01 = sdt.format(date);
                System.out.println("pseudo="+ut.getPseudo()+d01+ip);
                p.sauvegarderCommande(ut.getPseudo(),d01,ip);
                int num = p.getCommande().getIdCommande();
                System.out.println("id = "+p.getCommande().getIdCommande());
                Commande commande = p.getCommande();
                System.out.println("commande"+commande);
                ArrayList<LigneCommande> lc = p.getCommande().getLigneCommande();
                System.out.println("liste="+lc);
                p.sauvegarderLigneCommande(lc, num);
                
                
                
                url = "/WEB-INF/view/validCommande.jsp";
            } else {
                url = "./WEB-INF/view/adresse.jsp";
                   }
        }


        // VALIDATION TRANSPORTEUR

        request.setAttribute("section", section);

        request.getRequestDispatcher(url).include(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    }
