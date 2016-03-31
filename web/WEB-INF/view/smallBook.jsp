<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <br><hr>
    <p class="lead">Livres du moment</p>

    <c:forEach items="${Edition}" var="element">

        <div class="col-sm-4 col-lg-4 col-md-4">

            <div class="thumbnail">
                
                <img src="${element.lienVignetteAccueil}" alt="">
                <hr>
                <div class="caption">
                    <h4 >${element.prixTtc} EUR</h4>
                    <h4><a href="./index?section=focus&value=${element.numeroIsbn}">${element.titre}</a></h4>
                    <h5><a href="./index?section=focus&value=${element.numeroIsbn}">${element.sousTitre}</a></h5>

                    <h6>Auteur :${element.nomAuteur}</a>
                    </h6>
                    <p>Edition : ${element.nomEditeur}</p>
                </div>

                <div class="ratings">

                    <p> Note :
                        <c:if test="${empty element.note}">
                            Aucune note pour l'instant
                        </c:if>
                        <c:forEach var="i" begin="1" end="5" step="1">
                            <c:if test="${i<=element.note}">
                                <span class="glyphicon glyphicon-star"></span>
                            </c:if> 
                            <c:if test="${i>element.note}">
                                <span class="glyphicon glyphicon-star-empty"></span>
                            </c:if> 
                        </c:forEach> </p>
                </div>

                <a href="./index?section=pan&add=${element.numeroIsbn}">
                    <img src="ImagesLibrairie/Bouton/commander.jpg" alt="">
                </a>

            </div>
        </div>
    </c:forEach>