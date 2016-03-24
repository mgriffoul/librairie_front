<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<c:if test="${estVide}">
 

<a href="./index"> Retour </a>
Panier vide !!! 
</c:if>




<c:if test="${!estVide}">
<c:forEach var="i" items="${list}">
    ${i.titreLivre} / ${i.qte}
    <a href="./index?section=panier&dec=${i.isbn}">-</a>
    <a href="./index?section=panier&del=${i.isbn}">X</a>
    <br>    
</c:forEach>
    <a href="./index?section=panier&clear">Vider le panier</a>
    <br>
<a href="index">retour à l'acceuil</a>
</c:if>
    </body>>
    </html>