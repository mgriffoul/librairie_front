<FORM action="./index?section=focus&value=${Edition.numeroIsbn}" method="POST">
    Note : <SELECT name="note">
        <OPTION></OPTION>
        <OPTION>0</OPTION>
        <OPTION>1</OPTION>
        <OPTION>2</OPTION>
        <OPTION>3</OPTION>
        <OPTION>4</OPTION>
        <OPTION>5</OPTION>
    </SELECT>
    <p><h5> Votre commentaire :</h5></P><br>   
<textarea name="coment" id="com" rows="10" cols="80"> </textarea>   <br>  
<INPUT hidden="true" name="section" value="focus">
<INPUT hidden="true" name="value" value="${Edition.numeroIsbn}">
<input class="btn btn-success" type="submit" value="Valider" name="go" /><br>


</FORM>

