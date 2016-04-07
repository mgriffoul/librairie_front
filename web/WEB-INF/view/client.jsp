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
        <div class="col-md-5">
            <div>
                <table width="400" border="1">
                    <caption>Informations Personnelles</caption>
                    <tr height="20">
                        <td style="text-align:center; width:200px; height:20px;" bgcolor="lightgrey">Pseudo : </td>
                        <td width="200" height="20" align="center"><FONT SIZE="4"><strong><c:out value="${pseudo}" /></strong></FONT></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Civilité</td>
                        <td><c:out value="${civilite}" /></td>
                    </tr>

                    <tr height="20">
                        <td bgcolor="lightgrey">Nom</td>
                        <td><c:out value="${nomclient}" /></td>
                    </tr>
                    <tr>
                        <td bgcolor="lightgrey">Prénom</td>
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
                        <td bgcolor="lightgrey">Numéro de téléphone</td>
                        <td><c:out value="${telClient}" /></td>
                    </tr>
                </table>
            </div>  
            <hr>
            <div> 

                <form method="get" action="www01/register">
                    <input class= "button" type="submit" value="Modifier votre Email"  />
                </form>



                <form method="get" action="www01/register">
                    <input class= "button" type="submit" value="Modifier votre Mot de Passe"  />
                </form>


            </div>
            <hr height="2">
            <div>
                <table width="400" border="1">
                    <caption>Adresse de Facturation</caption>
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
            <hr>
            <div>
                <table width="400" border="1">
                    <caption>Adresse de Livraison</caption>
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
        <div class="col-md-7">


            <table align="center" valign="middle">
                <caption>Vos commandes</caption>
                <tr>
                    <td>
                        <form action="" method="post">
                            <select name="selection" style=" width:400px; height:30px" >
                                <c:forEach var="com" items="${listeCom}">
                                    <option  value="${com.numeroCommande}">
                                        <c:out value="${com.numeroCommande}"/> - 
                                        <c:out value="${com.dateCommande}"/> - 
                                        <c:out value="${com.statutCommande}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <input style="width:50px; height:30px" type="submit" value="OK"/>
                        </form>
                    </td>
                </tr>
            </table>
            <hr>
            <div>
                <jsp:include page="/WEB-INF/view/ligneCommande.jsp"/>
            </div>


        </div>
    </body>
</html>
