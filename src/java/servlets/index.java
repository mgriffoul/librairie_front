package servlets;

import beans.Bdd;
import beans.Categorie;
import beans.ConnexionForm;
import beans.Edition;
import beans.Isbn;
import beans.SousCategorie;
<<<<<<< HEAD
import beans.Utilisateur;
=======
import beans.beanClient;
import beans.beanLogin;
import beans.beanPanier;
>>>>>>> cd7f979f4b6f510c049f15ca3f8f3f45196d5905
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        
        
//SECTION NULL ----> INDEX        
        if (request.getParameter("section") == null) {
            section = "/WEB-INF/S2.jsp";
            //recuperation de la liste des categorie
            ArrayList<Categorie> listeCategorie = Categorie.initSidebar();

            //récupération des livres du momment
            Bdd bdd = new Bdd();
            Connection con = bdd.connecterBdd();
            ArrayList<Edition> listeEditionMoment = new ArrayList();
            try {
                String query = "SELECT * FROM AFFECTSOUSCATEGORIE WHERE idsouscategorie = 37";
                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Edition edition = new Edition();
                    Isbn isbn = new Isbn();
                    isbn.setNumeroIsbn(rs.getString("isbn"));
                    edition.setIsbn(isbn);
                    edition.chargerEdition();
                    System.out.println(edition);
                    listeEditionMoment.add(edition);
                }
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            url = "/WEB-INF/index.jsp?section=focus";
            Edition edit = new Edition();
            Isbn isb = new Isbn();
            isb.setNumeroIsbn(request.getParameter("value"));
            edit.setIsbn(isb);
            edit.chargerEdition();

            ArrayList<Categorie> listeCategorie = Categorie.initSidebar();

            
            request.setAttribute("Categorie", listeCategorie);
            request.setAttribute("Edition", edit);

        }
//FIN SECTION = FOCUS

//SECTION CATALOGUE PAR CATEGORIE        
        if ("cat".equals(request.getParameter("section"))) {
            url = "/WEB-INF/index.jsp?section=cat&value=" + request.getParameter("value");

            int id = Integer.valueOf(request.getParameter("value"));
            ArrayList<SousCategorie> listeSousCate = Categorie.recupererSousCategorie(id);

            ArrayList<Categorie> listeCategorie = Categorie.initSidebar();
            ArrayList<Edition> listeEdition = new ArrayList();

            Categorie cate = new Categorie(id);
            cate.chargerCategorie();

            if (request.getParameter("ssCat") == null) {

                System.out.println(">>>>>>>>>>  ssCat null");

                Bdd bdd = new Bdd();
                Connection con = bdd.connecterBdd();

                try {
                    String query = "SELECT dbo.CATEGORIE.IDCATEGORIE, dbo.SOUSCATEGORIE.IDSOUSCATEGORIE, dbo.EDITION.ISBN"
                            + " FROM dbo.CATEGORIE INNER JOIN"
                            + " dbo.SOUSCATEGORIE ON dbo.CATEGORIE.IDCATEGORIE = dbo.SOUSCATEGORIE.IDCATEGORIE INNER JOIN"
                            + " dbo.AFFECTSOUSCATEGORIE ON dbo.SOUSCATEGORIE.IDSOUSCATEGORIE = dbo.AFFECTSOUSCATEGORIE.IDSOUSCATEGORIE INNER JOIN"
                            + " dbo.EDITION ON dbo.AFFECTSOUSCATEGORIE.ISBN = dbo.EDITION.ISBN "
                            + " where dbo.CATEGORIE.idcategorie= " + id;

                    Statement stmt = con.createStatement();

                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        Edition edition = new Edition();
                        Isbn isbn = new Isbn();
                        isbn.setNumeroIsbn(rs.getString("isbn"));
                        edition.setIsbn(isbn);
                        edition.chargerEdition();
                        listeEdition.add(edition);
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            request.setAttribute("Edition", listeEdition);
            request.setAttribute("Categorie", listeCategorie);
            request.setAttribute("ListeSousCate", listeSousCate);
            request.setAttribute("CateChoisie", cate.getNomCategorie());

        }
//FIN SECTION CATALOGUE PAR CATEGORIE       
        
        // debut client
           // affichage nom, prenom...
        if ("acc".equals(request.getParameter("section"))) {
            url = "/WEB-INF/index.jsp?section=acc";

<<<<<<< HEAD
       
=======
            Bdd bdd = new Bdd();
            Connection con = bdd.connecterBdd();
            String str = "bruce28";

            try {
                String query = "SELECT * FROM client WHERE pseudo = '"+str+"'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                beanClient client = new beanClient();
                String pseudo="";
                String nomclient="";
                String prenomclient="";
                String mailclient="";
                String civilite="";
                String datenaissance="";
                String telClient="";
                String mdpClient="";
                
                while (rs.next()) {
                    client.setCiviliteclient(rs.getString("civilite"));
                    client.setPseudoClient(rs.getString("pseudo"));
                    client.setNomClient(rs.getString("nomclient"));
                    client.setPrenomClient(rs.getString("prenomclient"));
                    client.setEmailClient(rs.getString("mailclient"));
                    client.setDatenaissance(rs.getString("datenaissance"));
                    client.setTelephoneClient(rs.getString("telephoneclient"));
                    client.setMdpClient(rs.getString("motdepasseclient"));

                    civilite=client.getCiviliteclient();
                    pseudo=client.getPseudoClient();
                    nomclient=client.getNomClient();
                    prenomclient=client.getPrenomClient();
                    mailclient=client.getEmailClient();
                    datenaissance=client.getDatenaissance();
                    telClient=client.getTelephoneClient();
                    mdpClient=client.getMdpClient();
                    
                    if (civilite.equals("h")){
                        civilite="Homme";
                    }
                    if (civilite.equals("f")){
                        civilite="Femme";
                    }
                }

                request.setAttribute("civilite",civilite);
                request.setAttribute("pseudo", pseudo);
                request.setAttribute("nomclient", nomclient);
                request.setAttribute("prenomclient", prenomclient);
                request.setAttribute("mailclient", mailclient);
                request.setAttribute("datenaissance",datenaissance);
                request.setAttribute("telClient", telClient);
                request.setAttribute("mdpClient",mdpClient);
                
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }

            // AFFICHAGE ADRESSE FACTURATION
            try {
                String query = "SELECT * FROM adresse WHERE pseudo = '"+str+"' AND natureadresse='f' AND statutadresse='a'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                beanClient client = new beanClient();
                String adresseFac="";
                String complementFac="";
                String codePostalFac="";
                String villeFac="";
                String paysFac="";
                
                
                while (rs.next()) {
                    client.setAdresseFac(rs.getString("adresseclient"));
                    client.setComplementFac(rs.getString("adressecomplement"));
                    client.setCodePostalFac(rs.getString("codepostal"));
                    client.setVilleFac(rs.getString("ville"));
                    client.setPaysFac(rs.getString("pays"));
                    
                    adresseFac=client.getAdresseFac();
                    complementFac=client.getComplementFac();
                    codePostalFac=client.getCodePostalFac();
                    villeFac=client.getVilleFac();
                    paysFac=client.getPaysFac();
                    
                    if (complementFac == null){
                    complementFac=".";
                    }
                }

                request.setAttribute("adresseFac",adresseFac);
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
                String query = "SELECT * FROM adresse WHERE pseudo = '"+str+"' AND natureadresse='l' AND statutadresse='a'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                beanClient client = new beanClient();
                String adresseLiv="";
                String complementLiv="";
                String codePostalLiv="";
                String villeLiv="";
                String paysLiv="";
                
                
                while (rs.next()) {
                    client.setAdresseLiv(rs.getString("adresseclient"));
                    client.setComplementLiv(rs.getString("adressecomplement"));
                    client.setCodePostalLiv(rs.getString("codepostal"));
                    client.setVilleLiv(rs.getString("ville"));
                    client.setPaysLiv(rs.getString("pays"));
                    
                    adresseLiv=client.getAdresseLiv();
                    complementLiv=client.getComplementLiv();
                    codePostalLiv=client.getCodePostalLiv();
                    villeLiv=client.getVilleLiv();
                    paysLiv=client.getPaysLiv();
                    
                    if (complementLiv == null){
                    complementLiv=".";
                    }
                }

                request.setAttribute("adresseLiv",adresseLiv);
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
// fin client

        
        
//SECTION RECHERCHE

//FIN SECTION RECHERCHE
        
        
        if (request.getParameter("rec")!=null){
            
            ArrayList<Edition> listeEdition = new ArrayList();
            String rech = "%"+request.getParameter("rec")+"%";
            
             url = "/WEB-INF/index.jsp?section=rech&value=" + request.getParameter("rec");
            
             Bdd bdd = new Bdd();
                Connection con = bdd.connecterBdd();
                
                try {
                    String query = "SELECT dbo.EDITION.ISBN, dbo.OEUVRE.TITRE, dbo.OEUVRE.SOUSTITRE, dbo.EDITEUR.NOMEDITEUR, dbo.CONTRIBUTEUR.NOMCONTRIBUTEUR" +
                                   " FROM dbo.EDITION LEFT JOIN\n" +
                                   " dbo.CONTRIBUTEUR ON dbo.EDITION.IDTRADUCTEUR = dbo.CONTRIBUTEUR.IDCONTRIBUTEUR AND \n" +
                                   " dbo.EDITION.IDPREFACEUR = dbo.CONTRIBUTEUR.IDCONTRIBUTEUR LEFT JOIN" +
                                   " dbo.OEUVRE ON dbo.EDITION.IDOEUVRE = dbo.OEUVRE.IDOEUVRE LEFT JOIN" +
                                   " dbo.EDITEUR ON dbo.EDITION.IDEDITEUR = dbo.EDITEUR.IDEDITEUR " +
                                   " WHERE titre LIKE '"+rech+"' OR SOUSTITRE LIKE '"+rech+"' " +
                                   " OR NOMEDITEUR LIKE '"+rech+"' OR NOMCONTRIBUTEUR LIKE '"+rech+"' ";
                    
                    Statement stmt = con.createStatement();

                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        Edition edition = new Edition();
                        Isbn isbn = new Isbn();
                        isbn.setNumeroIsbn(rs.getString("isbn"));
                        edition.setIsbn(isbn);
                        edition.chargerEdition();
                        listeEdition.add(edition);
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                request.setAttribute("Recherche", request.getParameter("rec"));
                request.setAttribute("Edition", listeEdition);
                
        }

        
        
        
>>>>>>> cd7f979f4b6f510c049f15ca3f8f3f45196d5905
        if ("reg".equals(request.getParameter("section"))) {
            url = "/WEB-INF/index.jsp?section=user&action=reg";
        }
        
         if ("loggout".equals(request.getParameter("section"))) {
            session.invalidate();
            url= "/WEB-INF/index.jsp";
        }
        
      
        if ("log".equals(request.getParameter("section"))) {
            section = "login";
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
<<<<<<< HEAD
=======
// SECTION PANIER
            if ("vuepanier".equals(request.getParameter("section"))) {
            url = "./WEB-INF/view/jspPanier.jsp";
            beanPanier bPanier = (beanPanier) session.getAttribute("panier");
            
            if (bPanier == null) {
                bPanier = new beanPanier();
                session.setAttribute("panier", bPanier);
            }
            request.setAttribute("estVide", bPanier.isEmpty());
            request.setAttribute("list", bPanier.getList());
        }
        if ("panier".equals(request.getParameter("section"))) {
            beanPanier bPanier = (beanPanier) session.getAttribute("panier");

            if (bPanier == null) {
                bPanier = new beanPanier();
                session.setAttribute("panier", bPanier);
            }
            if (request.getParameter("add") != null) {
                bPanier.add(request.getParameter("add"));
            }
            if (request.getParameter("dec") != null){
                bPanier.dec(request.getParameter("dec"));
            }
            if (request.getParameter("del") != null){
                bPanier.del(request.getParameter("del"));
            }
            if (request.getParameter("clear") != null) {
                bPanier.clear();
            }
        }
//FIN SECTION PANIER
            
            
//
//            Cookie c = getCookie(request.getCookies(), "user");
//            if (c != null) {
//                url = "/WEB-INF/jspWelcome.jsp";
//                request.setAttribute("welcome", c.getValue());
//            }
//            if (request.getParameter("deconnect") != null) {
//                Cookie cc = new Cookie("user", "");
//                cc.setMaxAge(0);
//                response.addCookie(cc);
//                url = "/WEB-INF/jspLogin.jsp";
//            }
//
//            Cookie ccc = getCookie(request.getCookies(), "essai");
//            if (ccc != null) {
//                if (ccc.getValue().length() >= 3) {
//                    url = "/WEB-INF/jspFatalError.jsp";
//                    request.setAttribute("fatalerror", "Beaucoup trop de tentatives !!!");
//                }
//            }
>>>>>>> cd7f979f4b6f510c049f15ca3f8f3f45196d5905
        }
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
     */
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
