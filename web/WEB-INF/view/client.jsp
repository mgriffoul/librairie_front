<%-- 
    Document   : client
    Created on : 23 mars 2016, 15:30:53
    Author     : cdi206
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Iterator"%>
<%@page import="beans.Commande"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.beanClient"%>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Compte client</title>
        <%-- <link type="text/css" rel="stylesheet" href="inc/style.css" />--%>
    </head>

    <body>

        <jsp:useBean id="beanPage" scope="page" class="beans.beanClient" />
        <div class="col-md-6">
            <div>
                <table width="400" border="1">
                    <caption>Informations Personnelles</caption>
                    <tr height="20">
                        <td style="text-align:center; width:200px; height:20px;" bgcolor="lightgrey">Pseudo : </td>
                        <td width="200" height="20" align="center"><FONT SIZE="4"><strong><c:out value="${pseudo}" /></strong></FONT></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Civilit�</td>
                        <td><c:out value="${civilite}" /></td>
                    </tr>

                    <tr height="20">
                        <td bgcolor="lightgrey">Nom</td>
                        <td><c:out value="${nomclient}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Pr�nom</td>
                        <td><c:out value="${prenomclient}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Email</td>
                        <td><c:out value="${mailclient}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Date de naissance</td>
                        <td><c:out value="${datenaissance}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Num�ro de t�l�phone</td>
                        <td><c:out value="${telClient}" /></td>
                    </tr>
                </table>
            </div>  

            <div> 

                <form method="get" action="">
                    <input class= "button" type="submit" value="Modifier votre Email"  />
                </form>
            </div> 

            <div>
                <form method="get" action="">
                    <input class= "button" type="submit" value="Modifier votre Mot de Passe"  />
                </form>


            </div>
        </div>
        <div class="col-md-6">
            <div>
                <table width="400" border="1">
                    <caption>Adresse de Facturation Active</caption>
                    <tr>
                        <td width="150" align="left" bgcolor="lightgrey">Adresse : </td>
                        <td width="250" align="center"><c:out value="${adresseFac}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Complement : </td>
                        <td><c:out value="${complementFac}" /></td> 
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Code Postal : </td>
                        <td><c:out value="${codePostalFac}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Ville : </td>
                        <td><c:out value="${villeFac}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Pays : </td>
                        <td><c:out value="${paysFac}" /></td>
                    </tr>
                </table>

            </div>

            <div>
                <table width="400" border="1">
                    <caption>Adresse de Livraison Active</caption>
                    <tr>
                        <td width="150" align="left" bgcolor="lightgrey">Adresse : </td>
                        <td width="250" align="center"><c:out value="${adresseLiv}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Complement : </td>
                        <td><c:out value="${complementLiv}" /></td> 
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Code Postal : </td>
                        <td><c:out value="${codePostalLiv}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Ville : </td>
                        <td><c:out value="${villeLiv}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Pays : </td>
                        <td><c:out value="${paysLiv}" /></td>
                    </tr>
                </table>

            </div>
        </div>  

    </body>
</html>
