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
            <h4>${Edition.sousTitre}</h4>
            <h4> ${Edition.nomAuteur}</h4>
            <h5>Edition :<br> ${Edition.editeur}<br>
                
                Description :<br> ${Edition.description}<br>
                <p><h4>Synopsis :</h4> <br>${Edition.resume}</p>

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
                    <br><br> 
                </div>


                <c:if test="${empty ss7}">
                    <div class="text-right">
                        <a href="./index?section=focus&value=${Edition.numeroIsbn}&det=ok" > plus de détails</a>
                    </div>
                </c:if>
                <c:if test="${not empty ss7}">
                    <jsp:include page="${ss7}" />
                </c:if>

                <p>  <a href="./index?section=panier&add=${element.numeroIsbn}">
                        <img  src="ImagesLibrairie/Bouton/commander.jpg" alt=""></h4>
                    </a>
                </p>     
        </div>
    </div>


    <div class="well">


        <a class="btn btn-success">Leave a Review</a>


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




