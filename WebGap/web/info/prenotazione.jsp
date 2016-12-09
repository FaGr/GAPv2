<%-- 
    Document   : prenotazione
    Created on : Dec 8, 2016, 10:45:32 AM
    Author     : valeriotanferna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean  id="user" class="gapPackage.userProfile" scope="session"></jsp:useBean>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        Bentornato <% gapPackage.userProfile.getEmail();%>
    </body>
</html>
