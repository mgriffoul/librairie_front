
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<img src="ImagesLibrairie/Bouton/logov3g.png" alt="">

<FORM action="./index" method="GET" >
    <br><input type="text" name="rec" value="" class="find" />
    <!--<input type="image" src="ImagesLibrairie/Bouton/find.png"  name="go" height="25" width="25"/>-->
    <!--<input type="submit"><img src="ImagesLibrairie/Bouton/find.png" height="25" width="25"  alt="Submit"></>
    <!--<input type="submit" value="rechercher" name="go" /><br>-->
    <input type="submit"  class="findSubmit" value="" name="go" style="background: url(ImagesLibrairie/Bouton/find.png); padding: 10px 10px 10px 10px; margin-left: 5px;width:25px; height:25px; border: none" />
    <br>
</FORM> 
<p class="lead">Categorie</p>
<div class="list-group">
    <c:forEach items="${Categorie}" var="element">
        <a href="./index?section=cat&value=${element.idCategorie}" class="list-group-item"><c:out value="${element.nomCategorie}"/></a>
    </c:forEach>

</div>
