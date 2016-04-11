<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>Choix de l'adresse</title>
    </head>
    <body>

        
        <h1>Selection des adresses de facturation et de livraison</h1>
        <div style="display: inline-block; height: 30px; width: 410px; padding-left: 10px; float: left"></div>
        <div style="display: inline-block; height: 30px; width: 130px; padding-left: 10px">
                <form action="index">
                    <input type="hidden" name="section" value="nouvelleadresse">
                    <input type="submit" name="test" value="Nouvelle adresse">
                </form>
            <br/>
            <br/>
        </div>
        <form action="index" method="POST">
            
            <div style="display: inline-block; float: left; height: 500px; width: 500px; 
                 margin-right: 2px; border-right: 1px solid black; padding-right: 8px; overflow: scroll">
                <label>Choisissez l'adresse de facturation : </label>
                <br/>
                <br/>
                <c:forEach var="adresses" items="${adressefacturation}">
                    <input type="radio" name="adressefacturation" value="${adresses.idAdresse}">
                    <%--Nom : ${adresses.nomClient} ${adresses.prenomClient}--%>
                    <%--<br/>--%>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${adresses.adresseClient}
                    <br/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${adresses.codePostal} ${adresses.ville}
                    <br/><br/>
                </c:forEach>
                <br/>
            </div>
            <div style="display: inline-block; height: 500px; width: 500px; margin-left: 2px; 
                 border-left: 1px solid black; padding-left: 8px; overflow: scroll">
                <label>Choisissez l'adresse de livraison : </label>
                <br/>
                <br/>
                <c:forEach var="adresses" items="${adresselivraison}">
                    <input type="radio" name="adresselivraison" value="${adresses.idAdresse}">
                    <%--Nom : ${adresses.nomClient} ${adresses.prenomClient}--%>
                    <%--<br/>--%>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${adresses.adresseClient}
                    <br/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${adresses.codePostal} ${adresses.ville}
                    <br/><br/>
                </c:forEach>
                <br/>
            </div>
            <br/>
            <br/>
            <div style="display: inline-block; height: 50px; width: 900px; float: left;"></div>
            <div style="display: inline-block; height: 50px; width: 100px;">
                <input type="hidden" name="section" value="validCommande">
                <input type="submit" name="doIt" value="Terminer">
            </div>
        </form>

    </body>
</html>
