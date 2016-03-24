package servlets;

import beans.Bdd;
import beans.Categorie;
import beans.Edition;
import beans.Isbn;
import beans.SousCategorie;
import beans.beanLogin;
import beans.beanPanier;
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

//SECTION NULL ----> INDEX        
        if (request.getParameter("section") == null) {

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
                            + " where dbo.CATEGORIE.idcategorie= " +id;
                    
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

        if ("reg".equals(request.getParameter("section"))) {
            url = "/WEB-INF/index.jsp?section=user&action=reg";
        }

//        if ("vuepanier".equals(request.getParameter("section"))) {
//            url = "/WEB-INF/jspPanier.jsp";
//            beanPanier bPanier = (beanPanier) session.getAttribute("panier");
//            if (bPanier == null) {
//                bPanier = new beanPanier();
//                session.setAttribute("panier", bPanier);
//            }
//            request.setAttribute("estVide", bPanier.isEmpty());
//            request.setAttribute("list", bPanier.getList());
//        }
//        if ("panier".equals(request.getParameter("section"))) {
//            beanPanier bPanier = (beanPanier) session.getAttribute("panier");
//            if (bPanier == null) {
//                bPanier = new beanPanier();
//                session.setAttribute("panier", bPanier);
//            }
//            if (request.getParameter("add") != null) {
//                bPanier.add(request.getParameter("add"));
//            }
//            if (request.getParameter("dec") != null) {
//                bPanier.dec(request.getParameter("dec"));
//            }
//            if (request.getParameter("del") != null) {
//                bPanier.del(request.getParameter("del"));
//            }
//            if (request.getParameter("clear") != null) {
//                bPanier.clear();
//            }
//        }
        if ("login".equals(request.getParameter("section"))) {

            if (request.getParameter("doIt") != null) {
                beanLogin bLogin = (beanLogin) request.getAttribute("beanLogin");
                if (bLogin == null) {
                    bLogin = new beanLogin();
                    request.setAttribute("beanLogin", bLogin);
                }
                if (bLogin.check(request.getParameter("login"),
                        request.getParameter("password"))) {
                    url = "/WEB-INF/jspWelcome.jsp";
                    request.setAttribute("welcome", request.getParameter("login"));
                    Cookie c = new Cookie("user", request.getParameter("login"));
                    response.addCookie(c);
                } else {
                    request.setAttribute("msg", "Utilisateur/Mot de passe invalide !!!");
                    request.setAttribute("user", request.getParameter("login"));
                    Cookie essai = getCookie(request.getCookies(), "essai");
                    if (essai == null) {
                        essai = new Cookie("essai", "*");
                    } else {
                        essai.setValue(essai.getValue() + "*");
                    }
                    essai.setMaxAge(90);
                    response.addCookie(essai);
                    if (essai.getValue().length() >= 3) {
                        url = "/WEB-INF/jspFatalError.jsp";
                        request.setAttribute("fatalerror", "Trop de tentatives !!!");
                    }
                }
            }
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
        }
        System.out.println(">>>>>>>>>" + url);

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
