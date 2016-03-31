<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


    <div class="thumbnail">
        <img class="img-responsive" src="${Edition.lienVignetteFocus}" alt="">
        <div class="caption-full">
            <h4 class="pull-right">${Edition.prixTtc} EUR</h4>
            <div class="titre">  <h3>${Edition.titre}</h3></div>
            <h4>${Edition.sousTitre}</h4>
            <h4>de ${Edition.nomAuteur}</h4>
            <h5>Edition : ${Edition.editeur}<br>
                <br>
                Description : ${Edition.description}
                <p><h4>Synopsis :</h4> ${Edition.resume}</p>

                <div class="ratings">
                    <p class="pull-right">3 reviews</p>
                    <p>
                        Note moyenne : 
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



                <h4><a href="./index?section=focus&value=${Edition.numeroIsbn}&action=pan&add=${Edition.numeroIsbn}">
                        <img  src="ImagesLibrairie/Bouton/commander.jpg" alt="">
                    </a></h4>

        </div>
    </div>


    <div class="well">

        <c:if test="${empty sessionUtilisateur}">

            <a href="./index?section=log" class="btn btn-success">Connection</a> Connectez vous pour laisser des commentaires
        </c:if>
        <c:if test="${not empty sessionUtilisateur}">
            <c:if test="${empty presComent}">

                <c:if test="${not empty presAchat}">
                    <c:if test="${not empty com}">
                        <FONT COLOR="red"><h5>${Erreur}</h5></FONT>
                        <%@include file="/WEB-INF/view/commentaireForm.jsp" %>
                    </c:if>
                    <c:if test="${empty com}">
                        <a href="./index?section=focus&value=${Edition.numeroIsbn}&com=let" class="btn btn-success">Laisser un commentaire</a>
                    </c:if>
                </c:if>   
                    <c:if test="${empty presAchat}">
                        Vous devez avoir acheté ce livre pour le commenter
                    </c:if>        
            </c:if>
            <c:if test="${not empty presComent}">
                Vous avez déjà commenté ce livre.
            </c:if>    
        </c:if>

        <hr>


        <hr>
        <c:forEach items="${Commentaire}" var="coment">
            <div class="row">
                <div class="col-md-12">
                    <c:forEach var="i" begin="1" end="5" step="1">
                        <c:if test="${i<=coment.note}">
                            <span class="glyphicon glyphicon-star"></span>
                        </c:if> 
                        <c:if test="${i>coment.note}">
                            <span class="glyphicon glyphicon-star-empty"></span>
                        </c:if> 
                    </c:forEach> 
                    ${coment.pseudo}
                    <span class="pull-right">${coment.dateFormat}</span>
                    <p>${coment.commentaire}</p>
                </div>
            </div>
            <hr>
        </c:forEach>



    </div>






<!-- /.container -->




