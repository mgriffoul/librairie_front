<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <ul class="nav navbar-nav">
        <c:if test="${!empty sessionScope.sessionUtilisateur}">
            <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
            <li>
                <a href="./index?section=loggout">deconnexion</a>
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
