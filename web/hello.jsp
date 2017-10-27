<%-- 
    Document   : hello
    Created on : 23 oct. 2017, 10:14:01
    Author     : Aurelien Courgeau
--%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="_partials/_header.jspf"%>
<body>
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
            message = " Vous etes Trop vieux" + "<br>" + " Name session: " + session.getAttribute("userName");
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

    <div class="container-fluid">
        <div class="row">
            
            <h1>Hello <%= name%></h1>
            <p>Nous sommes en <%= sf.format(now)%> </p>
            <P>Et vous etes agé de <%= age%> ans !!!</P>
            <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">Afficher le message</button>
            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">MESSAGE</h4>
                        </div>
                        <div class="modal-body">
                            <p><%= message%></p>
                            <p>Name session: <%= session.getAttribute("userName")%> </p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
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
