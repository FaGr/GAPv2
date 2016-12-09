<%-- 
    Document   : controlla
    Created on : Dec 9, 2016, 11:09:47 AM
    Author     : valeriotanferna
--%>

<%@  page import="java.io.*,java.util.*"  language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"     "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean  id="user" class="gapPackage.userProfile" scope="session"></jsp:useBean>
<jsp:setProperty name="user" property="*"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login checking</title>
</head>
<body>
<%

 String USER = user.getName();
 String PASSWORD = user.getPassword();
 if(USER.equals("Bechini@unipi.it"))
 {
 if(PASSWORD.equals("54321"))
 {
     pageContext.forward("prenotazione.jsp");
 }
 else
 {
     out.println("Wrong password");
     pageContext.include("index.jsp");
 }
 pageContext.include("index.jsp");

 }



%>

</body>
</html>
