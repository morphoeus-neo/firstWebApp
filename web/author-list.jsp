<%-- 
    Document   : author-list
    Created on : 24 oct. 2017, 15:24:19
    Author     : formation
--%>
<%@page import="fr.aurelien.webapp.entity.Author"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="_partials/_header.jspf" %>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <h1 style="text-align: center;font-weight: bold">Liste des Auteurs</h1>
                
            </div>
        </div>
    </div>
            
            
    <div class="container">
        <% List<Author> authorList = (List<Author>) request.getAttribute("authorList"); %>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>id</th>      
                    <th>Prénom</th>
                    <th>Nom</th>
                </tr>
            </thead>
            <tbody>
                <% for (Author author : authorList) { %>
                <tr>
                    <td> <%= author.getId()%></td>
                    <td> <%= author.getFirstName()%></td>
                    <td> <%= author.getName()%></td>
                </tr>
                <% } // fin de la boucle%>
            </tbody>
        </table>
    </div>

</body>
</html>
