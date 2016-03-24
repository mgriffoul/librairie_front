
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<br>
<br>
    <p class="lead">Categorie</p>
    <div class="list-group">
        <c:forEach items="${Categorie}" var="element">
            <a href="./index?section=cat&value=${element.idCategorie}" class="list-group-item"><c:out value="${element.nomCategorie}"/></a>
        </c:forEach>

    </div>
