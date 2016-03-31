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
                        <option value="${i.isbn}">1</option>
                        <option value="${i.isbn}">2</option>
                        <option value="${i.isbn}">3</option>
                        <option value="${i.isbn}">4</option>
                        <option value="${i.isbn}">5</option>
                        <option value="${i.isbn}">6</option>
                        <option value="${i.isbn}">7</option>
                        <option value="${i.isbn}">8</option>
                        <option value="${i.isbn}">9</option>
                        <option value="${i.isbn}">10</option>
                        <option selected="selected">
                            ${i.qte}
                        </option>
                    </select>
                </td>
                <td>
                    <a href="./index?action=pan&section=pan&del=${i.isbn}">
                        <img src="ImagesLibrairie/Bouton/corbeille.png" alt="shop" style="width:25px;height:25px;">    
                    </a> 
                </td>
        </tr>
        </c:forEach>      
    </table>
    <p class='total'> Prix total: ${commande.prixCommande} EUROS </p>
    <a class='clear' href="./index?action=pan&section=pan&clear" >Vider le panier</a>
</c:if>
   <script type="text/javascript">
   function changeFunc() {
    var selectBox = document.getElementById("selectBox");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    var selectedText = selectBox.options[selectBox.selectedIndex].text;
    document.location.href="./index?action=pan&section=pan&qte="+selectedText+"&isbn="+selectedValue;
   }
  </script>