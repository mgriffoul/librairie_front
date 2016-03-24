<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    
    <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
                                </div>
                                
                            </div>
    
    <br><hr>
   

    <c:if test="${empty Edition}">
        <h4><font color="red">Désolé, aucun résultat trouvé pour "${Recherche}"</font></h4>
    </c:if>
     <c:if test="${not empty Edition}">
     <p class="lead">Nos livres correspondant à "${Recherche}"</p>
        </c:if>
    <c:forEach items="${Edition}" var="element">

        
        <div class="col-sm-4 col-lg-4 col-md-4">

            <div class="thumbnail">
                <img src="${element.lienVignetteAccueil}" alt="">
                <div class="caption">
                    <h4>${element.prixTtc} EUR</h4>
                    <h4><a href="./index?section=focus&value=${element.numeroIsbn}">${element.titre}</a></h4>
                    <h5><a href="./index?section=focus&value=${element.numeroIsbn}">${element.sousTitre}</a></h5>

                    <h5>${element.nomAuteur}</a>
                    </h5>
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


                <a href="./index?section=panier&add=${element.numeroIsbn}">
                    <img src="ImagesLibrairie/Bouton/commander.jpg" alt="">
                </a>


            </div>
        </div>
    </c:forEach>