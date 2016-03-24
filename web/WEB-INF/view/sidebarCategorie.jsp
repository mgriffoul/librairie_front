
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<FORM action="./index" method="GET" >
     <br><input type="text" name="rec" value="" /><input type="submit" value="rechercher" name="go" /><br>
     <br>
</FORM> 



    <p class="lead">Categorie</p>
    <div class="list-group">
        <c:forEach items="${Categorie}" var="element">
            <a href="./index?section=cat&value=${element.idCategorie}" class="list-group-item"><c:out value="${element.nomCategorie}"/></a>
        </c:forEach>

    </div>
