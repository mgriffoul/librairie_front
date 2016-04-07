<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="nav navbar-nav">
    <c:if test="${!empty sessionScope.sessionUtilisateur}">
        <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
        <li>
            <div class="dropdown">
                <button class="dropdown-toggle drop" type="button" data-toggle="dropdown">Mon compte
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="./index?section=acc">Mes informations personnelles</a></li>
                    <li><a href="./index?section=com">Mes commande(s)</a></li>
                </ul>
            </div>  
        </li>
        <li>
            <a href="./index?section=loggout">Deconnexion</a>
        </li>

    </c:if>
    <c:if test="${empty sessionScope.sessionUtilisateur}">
        <li>
            <a href="./index?section=log">Connexion</a>
        </li>
        <li>
            <a href="./index?section=reg">Inscription</a>
        </li>
    </c:if>
</ul>
