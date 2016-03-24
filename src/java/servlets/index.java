package servlets;

import beans.Bdd;
import beans.Categorie;
import beans.ConnexionForm;
import beans.Edition;
import beans.Isbn;
import beans.SousCategorie;
import beans.Utilisateur;
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
