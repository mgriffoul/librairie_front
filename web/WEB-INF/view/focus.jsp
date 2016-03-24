<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<br>
<br>
<br>
<div class="col-md-9">

    <div class="thumbnail">
        <img class="img-responsive" src="${Edition.lienVignetteFocus}" alt="">
        <div class="caption-full">
            <h4 class="pull-right">${Edition.prixTtc} EUR</h4>
            <h3>${Edition.titre}</h3>
            <h4> ${Edition.nomAuteur}</h4>
            <h5>Edition : ${Edition.editeur}
            <br>
            <p><h4>Synopsis :</h4> <br>${Edition.resume}</p>
        </div>
        <div class="ratings">
            <p class="pull-right">3 reviews</p>
            <p>
               Note : 
                <c:if test="${empty Edition.note}">
                    Aucune note pour l'instant
                </c:if>
                <c:if test="${not empty Edition.note}">
                <c:forEach var="i" begin="1" end="5" step="1">
                    <c:if test="${i<=Edition.note}">
                        <span class="glyphicon glyphicon-star"></span>
                    </c:if> 
                    <c:if test="${i>Edition.note}">
                        <span class="glyphicon glyphicon-star-empty"></span>
                    </c:if> 
                </c:forEach> </p>
                </c:if>

            </p>
        </div>
        
            <p>  <a href="./index?section=panier&add=${element.numeroIsbn}">
                    <h4> <img class="pullright" src="ImagesLibrairie/Bouton/commander.jpg" alt=""></h4>
                </a>
            </p>     
    </div>

    <div class="well">

        <div class="text-right">
            <a class="btn btn-success">Leave a Review</a>
        </div>

        <hr>

        <div class="row">
            <div class="col-md-12">
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star-empty"></span>
                Anonymous
                <span class="pull-right">10 days ago</span>
                <p>This product was great in terms of quality. I would definitely buy another!</p>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col-md-12">
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star-empty"></span>
                Anonymous
                <span class="pull-right">12 days ago</span>
                <p>I've alredy ordered another one!</p>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col-md-12">
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star-empty"></span>
                Anonymous
                <span class="pull-right">15 days ago</span>
                <p>I've seen some better than this, but not at this price. I definitely recommend this item.</p>
            </div>
        </div>

    </div>
</div>





<!-- /.container -->




