<%-- 
    Document   : test
    Created on : 6 avr. 2016, 13:36:21
    Author     : cdi206
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Iterator"%>
<%@page import="beans.Commande"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.beanClient"%>
<%@page import="beans.LigneCommande"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>

        <div>
            <table  align="center" valign="middle"  CELLPADDING="5" width="220">
                <caption>Détail commande <font color="red"><c:out value="${select}"/></font></caption>
                <thead>
                    <tr style="background-color:lightgrey;">
                        <th style="width:100px;">Titre du livre</th>
                        <th style="width:30px;">Qté</th>
                        <th style="width:30px;">Prix UHT</th>
                        <th style="width:30px;">TVA</th>
                        <th style="width:30px;">Réduc</th>
                    </tr>
                    </head> 
                <tbody> 
                    <c:forEach var="ligne" items="${llcom}">
                        <tr>
                            <td style="text-align:left ; width:100px; height:30px;"><c:out value="${ligne.titreLivre}"/></td>
                            <td style="text-align:center ; width:30px; height:30px;"><c:out value="${ligne.qte}"/></td>
                            <td style="text-align:center  ; width:30px; height:30px;"><c:out value="${ligne.prixTTC2}"/> euro</td>
                            <td style="text-align:center  ; width:30px; height:30px;"><c:out value="${ligne.tvaAppli}"/> %</td>
                            <td style="text-align:center  ; width:30px; height:30px;"><c:out value="${ligne.reduc}"/> %</td>
                        </tr>
                    </c:forEach>
                </tbody> 
                <tfoot>
                    <tr style="background-color:lightgrey; text-align:center; width:220px;">
                        <td><strong><c:out value="${select}"/></strong></td>
                        <td></td>
                        <td></td>
                        <td><strong>total TTC : </strong></td>
                        <td><strong><c:out value="${totalCommande}"/> euro</strong></td>
                    </tr>
                </tfoot>

            </table>

        </div>

    </body>
</html>
