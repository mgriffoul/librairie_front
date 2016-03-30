<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>Nouvelle adresse</title>
    </head>
    <body>
        <h3>Nouvelle adresse</h3>
        <form action="index" method="POST">
            <div>
                <select name="civilite">
                    <option value="1">M.</option>
                    <option value="2">Mme.</option>
                </select>
            </div>
            <div>
                <div>
                    Nom : 
                    <input type="text" name="nomClient" value="">
                </div>
                <div>
                    Pr&eacute;nom : 
                    <input type="text" name="prenomClient" value="">
                </div>
            </div>
            <div>
                <div>
                    Adresse : 
                    <input type="text" name="adresseClient" value="" id="tailleInput">
                </div>
            </div>
            <div>
                <div>
                    Compl&eacute;ment : 
                    <input type="text" name="adresseComplement" value="" id="tailleInput">
                </div>
            </div>
            <div>
                <div>
                    Code postal : 
                    <input type="text" name="codePostal" value="">
                </div>
                <div>
                    Ville : 
                    <input type="text" name="ville" value="">
                </div>
                <div>
                    Pays :
                    <input type="text" name="pays" value="">
                </div>
            </div>
            <div>
                <div>
                    Type d'adresse : 
                </div>
                <div>
                    <input id="rad1" type="radio" name="natureAdresse" value="L">
                    <label for="rad1"> Adresse de livraison</label>
                    <input id="rad2" type="radio" name="natureAdresse" value="F">
                    <label for="rad2"> Adresse de facturation</label>
                </div>
            </div>
           <%-- <input type="hidden" name="vientde" value="nouvelleadresse"> --%>
            <input type="hidden" name="section" value="enregistreradresse">
            <input type="submit" name="doIt" value="Enregistrer et continuer">
        </form>
    </body>
</html>
