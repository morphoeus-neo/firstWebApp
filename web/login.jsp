<%-- 
    Document   : login.jsp
    Created on : 23 oct. 2017, 15:34:20
    Author     : Aurelien Courgeau
--%>
<%@include file="_partials/_header.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="jumbotron">
                        <h1>CONNEXION</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 col-md-offset-3">
                    <form method="post">
                        <div class="form-group">
                            <label for="login">Login:</label>
                            <input type="email" class="form-control" id="login" name="login">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Mot de passe :</label>
                            <input type="password" class="form-control" id="pwd" name="pwd">
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox"> Se Souvenir de moi</label>
                        </div>
                        <button type="submit" class="btn btn-default">Valider</button>
                    </form> 
                    <p><%= request.getAttribute("error")%></p>
                </div>                    
            </div>           
        </div>                  
        <script src="./bower_components/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
