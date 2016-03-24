        <form method="post" action="connexion">
            <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>

                <label for="nom">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value=""/>
                <span class="erreur">${Login.erreurs['email']}</span>
                

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${Login.erreurs['motdepasse']}</span>


                <input type="submit" value="Connexion" class="sansLabel" />
                           
                <p class="${empty Login.erreurs ? 'succes' : 'erreur'}">${Login.resultat}</p>
            </fieldset>
        </form>