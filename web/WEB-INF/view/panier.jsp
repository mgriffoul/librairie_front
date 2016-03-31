<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${empty list}">
    <h1>Panier vide!</h1>
</c:if>
<c:if test="${!empty list}">
    <table>
        <caption>Panier: ${fn:length(list)} article!</caption>
        <tr>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Editeur</th>
            <th>Prix</th>
            <th>Quantité</th>
            <th>Supprimer</th>
        </tr>
        <tr>
            <c:forEach var="i" items="${list}">
                <td>${i.titreLivre}</td>
                <td>${i.nomAuteur}</td>
                <td>${i.nomEditeur}</td>
                <td>${i.prixTTC} EUROS</td>
                <td>
                    <select id="selectBox" onchange="changeFunc();">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="4">5</option>
                        <option value="4">6</option>
                        <option value="4">7</option>
                        <option value="4">8</option>
                        <option value="4">9</option>
                        <option value="4">10</option>
                        <option selected="selected">
                            ${i.qte}
                        </option>
                    </select>
                </td>
                <td>
                    <a href="./index?section=pan&del=${i.isbn}">
                        <img src="ImagesLibrairie/Bouton/corbeille.png" alt="shop" style="width:25px;height:25px;">    
                    </a> 
                </td>
        </tr>
        </c:forEach>      
    </table>
    <p class='total'> Prix total: ${commande.prixCommande} EUROS </p>
    <a href="./index?section=pan&clear" >Vider le panier</a>
    <hr>
    <form action="./index?section=choixadresse" method="POST">
    <input type="submit" name="doIt" value="Valider le panier" />
    </form> 
</c:if>
   <script type="text/javascript">
   function changeFunc() {
    var selectBox = document.getElementById("selectBox");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    document.location.href="./index?section=pan&qte="+selectedValue;
   }
  </script>