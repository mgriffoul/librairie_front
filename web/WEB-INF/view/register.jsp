<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un client</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>

    <body>
        <div>
            <form method="get" action="www01/register">
                <legend>Informations client</legend>
                <fieldset>
                    <label for="civiliteClient">Civilité <span class="requis">*</span></label>
                    <INPUT type="checkbox" name="choix1" value="1"> Homme
                    <INPUT type="checkbox" name="choix2" value="2"> Femme
                    <br />   

                    <label for="loginClient">Login <span class="requis">*</span></label>
                    <input type="text" id="loginClient" name="loginClient" value="" size="30" maxlength="20" />
                    <br />

                    <label for="passClient">Mot de passe: <span class="requis">*</span></label>
                    <input type="password" id="passClient" name="passClient" value="" size="30" maxlength="20" />
                    <br />

                    <label for="nomClient">Nom <span class="requis">*</span></label>
                    <input type="text" id="nomClient" name="nomClient" value="" size="30" maxlength="20" />
                    <br />

                    <label for="prenomClient">Prénom </label>
                    <input type="text" id="prenomClient" name="prenomClient" value="" size="30" maxlength="20" />
                    <br />

                    <label for="emailClient">Adresse email</label>
                    <input type="email" id="emailClient" name="emailClient" value="" size="30" maxlength="60" />
                    <br />

                    <label for="naissanceClient">Date de naissance</label>
                    <input type="date" id="naissanceClient" name="naissanceClient" value="" size="30" maxlength="60" />
                    <br />


                    <label for="telephoneClient">Numéro de téléphone<span class="requis">*</span></label>
                    <input type="text" id="telephoneClient" name="telephoneClient" value="" size="30" maxlength="20" />
                    <br />

                    <label for="adresseClient">Adresse<span class="requis">*</span></label>
                    <input type="text" id="adresseClient1" name="adresseClient1" value="" size="30" maxlength="20" />
                    <br />

                    <label for="adresseClient">Complément d'adresse<span class="requis">*</span></label>
                    <input type="text" id="adresseClient2" name="adresseClient2" value="" size="30" maxlength="20" />
                    <br />

                    <label for="adresseClient">Code postal<span class="requis">*</span></label>
                    <input type="text" id="codePostalClient" name="codePostalClient" value="" size="30" maxlength="20" />
                    <br />

                    <label for="adresseClient">Ville<span class="requis">*</span></label>
                    <input type="text" id="villeClient" name="villeClient" value="" size="30" maxlength="20" />
                    <br />

                    <label for="adresseClient">Pays<span class="requis">*</span></label>
                    <input type="text" id="paysClient" name="paysClient" value="" size="30" maxlength="20" />
                    <br />    

                    <input class= "button" type="submit" value="Valider"  />
                    <input class= "button" type="reset" value="Remettre à zéro" /> <br />

                    <c:if test="${!empty message}">
                        <label class="erreur">${message}</label> 
                    </c:if>
                </fieldset>
            </form>
        </div>
    </body>
</html>