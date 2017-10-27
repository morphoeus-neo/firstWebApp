<%-- 
    Document   : author-form
    Created on : 25 oct. 2017, 16:30:14
    Author     : formation
--%>
<%@page import="fr.aurelien.webapp.entity.Author"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="_partials/_header.jspf" %>


<% Author author = (Author) request.getAttribute("author"); %>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1 class="h1" style="text-align: center">MODIFICATION D'UN AUTHEUR</h1><br>
            
        </div>
        
    </div>
    
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 col-md-offset-3"
             <div class="form form-group">
                 <div class="form-control">
                    <label>Nom</label>
                    <input type="text" name="authorName" value="<%=author.getName() %>">
                </div>
                <div class="form-inline ">
                    <label>Prenom</label>
                    <input type="text" name="authorFirstName" value="<%=author.getFirstName()%>">
                </div>
                <div>
                    <button type="submit">Valider</button>
                </div>                                                
            </div>        
        </div>  
    </div>

</body>
</html>
