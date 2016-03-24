<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${!empty sessionScope.sessionUtilisateur}">
    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
</c:if>
       <h2>Connexion</h2>
<form method="post" action="./index?section=log&action=submit">
    <fieldset>
        <p>Vous pouvez vous connecter via ce formulaire.</p>
        <label for="nom">Adresse email <span class="requis">*</span></label>
        <input type="email" id="email" name="email" value=""/>
        <span class="erreur">${form.erreurs['email']}</span>           
        <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
        <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
        <span class="erreur">${form.erreurs['motdepasse']}</span>
        <input type="submit" value="Connexion" class="sansLabel" />  
        </br>
        <span class="erreur">${form.erreurs['utilisateur']}</span>
    </fieldset>
         <p class="${empty Login.erreurs ? 'succes' : 'erreur'}">${Login.resultat}</p>
</form>