<%-- 
    Document   : hello
    Created on : 23 oct. 2017, 10:14:01
    Author     : formation
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="./bower_components/bootstrap/dist/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>

        <script src="./bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>

        <%!
            String name;
            //on utilise Integer car un int ne peut pas etre null!!!!
            Integer age = 0;
            Date now;
            SimpleDateFormat sf;
            String inputAge;
            String message;
            String color;
            Map<String, String> colorMap;

        %>
        <%
            colorMap = new HashMap<String, String>();
            colorMap.put("Rouge", "red");
            colorMap.put("Vert", "green");
            colorMap.put("Bleu", "blue");
            colorMap.put("Gris", "CCCCCC");
            colorMap.put("Brunh", "brown");

            // Récuperation d'un parametre
            name = request.getParameter("Who");
            // Affectation d'une valeur par defaut si l'utilisateur n'est pas renseigné ( hello.jsp? who=toto
            if (name == null) {
                name = "inconnu";
            }
            inputAge = request.getParameter("age");
            if (inputAge == null) {
                age = 10;
            } else {
                age = Integer.valueOf(inputAge);
            }

            // Définition du message en fonction de l'age
            if (age < 18) {
                message = " Vous etes Mineur!!! ha ha ha ha ha";

            } else if (age <= 62) {
                message = "Vous etes majeur";
            } else {
                message = " Vous etes Trop vieux";
            }

            now = new Date();
            sf = new SimpleDateFormat("YYYY");

            color = request.getParameter("color");
            if (color == null) {
                color = "white";
            }

        %>
        <style>
            body{
                background-color: <%= color%>;
            }


        </style>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Acceuil</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                        <li><a href="#">Link</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Link</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <!-- Small modal -->


        <div class="container-fluid">
            <div class="row">
                <h1>Hello <%= name%></h1>
                <p>Nous sommes en <%= sf.format(now)%> </p>
                <P>Et vous etes agé de <%= age%> ans !!!</P>

                <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Afficher le message</button>

                <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <%= message%>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-2">
                <form method="post">

                    <div class="form-group" >
                        <p> Choisisez une couleur de fond<p>
                            <select style="background-color: grey; font-weight: bold"  name="color" class="form-control">
                                <%--
                                <option value="red" class="form-control" <%= color.equals("red") ? "selected" : ""%> >Rouge</option>
                                <option value="green" class="form-control" <%= color.equals("green") ? "selected" : ""%>>Vert</option>
                                <option value="blue" class="form-control" <%= color.equals("blue") ? "selected" : ""%>>Bleu</option>
                                <option value="CCCCCC" class="form-control" <%= color.equals("CCCCCC") ? "selected" : ""%>>Gris</option>
                                --%>
                                <%
                                    String selected;
                                    for (Map.Entry<String, String> en : colorMap.entrySet()) {
                                        String key = en.getKey();
                                        String val = en.getValue();
                                        selected = color.equals(val) ? "selected" : "";


                                %>

                                <option value="<%=val%>"<%=selected%>><%= key%></option>

                                <%
                                        // fin de boucle
                                    }
                                %>
                            </select><br>
                    </div>
                    <div class="checkbox">

                        <button type="submit" class="btn btn-default">Submit</button>
                </form> 

            </div>
        </div>
        <script src="./bower_components/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
