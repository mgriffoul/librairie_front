<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="detail">
    <div class = "details1">
        <u>Numéro Isbn :</u><br> ${Edition.numeroIsbn}<br>
         <u>Langue : </u><br>${Edition.langue}<br>
        <u> Format :</u><br>${Edition.formatEdition}<br>
        <u> Dimmension :</u><br>${Edition.dimmension}<br>
        <u> Traducteur :</u><br> ${Edition.nomTraducteur}<br>
        <u> Prefaceur :</u><br> ${Edition.nomPrefaceur}<br>
    </div>
    <div class="details2">
       <u>  Taux de tva appliquée :</u> <br>${Edition.tauxTva}<br>
       <u>  Nombre de page :</u><br> ${Edition.nombrePage}<br>
       <u>  Date de parution :</u><br> ${Edition.stringDateParution}<br>
       <u>  Poids(gr):</u><br> ${Edition.poids}<br>
       <u>  Mots clefs associés :</u><br>${Edition.stringMotClef}<br>
    </div>
</div>
    
    <!-- jsp de détails des editions -->