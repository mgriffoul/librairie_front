<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transporteur</title>
    </head>
    <body>
        <h1>Selection du transporteur</h1>
        <div>
            <form action="index" method="POST">
            
            <div style="display: inline-block; float: left; height: 500px; width: 500px; 
                 margin-right: 2px; border-right: 1px solid black; padding-right: 8px; overflow: scroll">
                <label>Choisissez le transporteur : </label>
                <br/>
                <br/>
                <c:forEach var="tr" items="${transporteur}">
                    <input type="radio" name="transporteur" value="${tr.idTransporteur}">
                    ${tr.nomTransporteur}
                  
                </c:forEach>
                <br/>
            </div>
            <br/><br/>
            <div style="display: inline-block; height: 50px; width: 900px; float: left;"></div>
            <div style="display: inline-block; height: 50px; width: 100px;">
                <input type="hidden" name="section" value="vuepaiement">
                <input type="submit" name="doIt" value="Terminer">
            </div>
        </div>
    </body>
</html>
