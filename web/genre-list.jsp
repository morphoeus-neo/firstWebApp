<%-- 
    Document   : genre-list
    Created on : 26 oct. 2017, 12:43:59
    Author     : formation
--%>
<%@page import="fr.aurelien.webapp.entity.GenreEntity"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="_partials/_header.jspf" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <h1 style="text-align: center;font-weight: bold">Liste des Genres</h1>
            <% if (request.getAttribute("message") != null) {%>
            <div class="alert alert-danger">Suite a un acte de malveillance : <%=request.getAttribute("message")%></div>
            <%} //fin e condition%>
        </div>
    </div>
</div>     
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3">
             <% List<GenreEntity> genreList = (List<GenreEntity>) request.getAttribute("genreList"); %>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th style="text-align: center"><span class="glyphicon glyphicon-hourglass ">&nbsp;</span>id</th>      
                        <th><span class="glyphicon glyphicon-user ">&nbsp;</span>Genre</th>
                        
                        <th><span class="glyphicon glyphicon-plus-sign ">&nbsp;</span>Actions</th>
                    </tr>
                </thead>
                <tbody>
                     <%! GenreEntity genre; %>
                    <% for (GenreEntity genre : genreList) { %>
                    <tr>
                        <td style="text-align: center"> <%= genre.getId()%></td>
                        <td> <%= genre.getGenre()%></td>
                        
                        <td style="text-align: justify">
                            <form method="post">
                                <a href="/firstWebApp/genre-form?id=<%=genre.getId()%>" class="btn btn-warning">Modifier</a>
                                <input hidden name="id" value="<%= genre.getId()%>">&nbsp;
                                <button  type="submit" class="btn btn-danger">Supprimer</button>
                            </form>
                        </td>
                    </tr>
                    <% } // fin de la boucle%>
                </tbody>
                <tfoot>
                <form method="post" class="form-group">
                    <tr>
                        <th>Actions supplémentaires</th>
                    </tr> 
                    <tr>
                        <td>
                            <button type="submit" class="btn btn-group-lg btn-primary">Ajouter un Genre</button></td>
                    </tr>
                </form>
                </tfoot>
            </table>
        </div>
    </div>
</div>
<script src="./bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>
</body>
</html>
