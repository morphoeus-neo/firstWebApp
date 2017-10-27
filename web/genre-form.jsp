<%-- 
    Document   : genre-form
    Created on : 26 oct. 2017, 14:45:06
    Author     : Aurelien Courgeau
--%>

<%@page import="fr.aurelien.webapp.entity.GenreEntity"%>


<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="_partials/_header.jspf" %>


<% GenreEntity genre = (GenreEntity) request.getAttribute("genre");%>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1 class="h1" style="text-align: center">MODIFICATION D'UN GENRE</h1><br>

        </div>

    </div>

</div>
<form>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 col-md-offset-3"
                 <div class="form form-group">
         
                    <label style='text-decoration: underline' for="basic-url">Modifier le nom d'un Genre : </label>
                    <div class="input-group">
                        <span style="font-weight: bold;color: black; border-color: black; border-right-color: black" class="input-group-addon" id="basic-addon3">Nom Genre</span>
                        <input type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3" value="<%=genre.getGenre()%>">
                    </div>
                    <div><br>
                        <button class="btn btn-lg btn-primary " type="submit">Valider</button>
                    </div>                                                
                </div>        
            </div>  
        </div>
</form>
</body>
</html>
