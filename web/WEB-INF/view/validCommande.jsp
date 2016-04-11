<%-- 
    Document   : validCommande
    Created on : 11 avr. 2016, 11:12:26
    Author     : cdi211
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Validation de la Commande!</h1>
        
        
        
    <c:if test="${!empty sessionScope.Panier}">
    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
    <h3>Commande validée ! Votre commande porte le numéro ${sessionScope.Panier.numComm}</h3>
    <h3> REDIRECTION VERS L'ACCUEIL... </h3>
<SCRIPT LANGUAGE='JavaScript'>
function redirect()
{
window.location='./index' ;
}
setTimeout('redirect()',3000); 
</SCRIPT>
    </body>
</html>
