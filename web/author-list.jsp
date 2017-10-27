<%-- 
    Document   : author-list
    Created on : 24 oct. 2017, 15:24:19
    Author     : Aurelien Courgeau
--%>
<%@page import="fr.aurelien.webapp.entity.AuthorEntity"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="_partials/_header.jspf" %>


<div class="container-fluid">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <h1 style="text-align: center;font-weight: bold">Liste des Auteurs</h1>
            <% if (request.getAttribute("message") != null) {%>
            <div class="alert alert-danger">Suite a un acte de malveillance : <%=request.getAttribute("message")%></div>
            <%} //fin e condition%>
        </div>
    </div>
</div>     
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3">
            <% List<AuthorEntity> authorList = (List<AuthorEntity>) request.getAttribute("authorList"); %>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th style="text-align: center"><span class="glyphicon glyphicon-hourglass ">&nbsp;</span>id</th>      
                        <th><span class="glyphicon glyphicon-user ">&nbsp;</span>Prénom</th>
                        <th><span class="glyphicon glyphicon-user ">&nbsp;</span>Nom</th>
                        <th><span class="glyphicon glyphicon-plus-sign ">&nbsp;</span>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%! AuthorEntity author; %>
                    <% for (AuthorEntity author : authorList) { %>
                    <tr>
                        <td style="text-align: center"> <%= author.getId()%></td>
                        <td> <%= author.getFirstName()%></td>
                        <td> <%= author.getName()%></td>
                        <td style="text-align: justify">
                            <form method="post">
                                <a href="/firstWebApp/author-form?id=<%=author.getId()%>" class="btn btn-warning">Modifier</a>
                                <input hidden name="id" value="<%= author.getId()%>">&nbsp;
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
                            <button type="submit" class="btn btn-group-lg btn-primary">Ajouter un Utilisateur</button></td>
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
