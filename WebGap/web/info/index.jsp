<%-- 
    Document   : index
    Created on : 5-dic-2016, 18.19.00
    Author     : fa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home GAP</title>
    </head>
    <body>
        <h1>Login page</h1>
        ---------------------------------------- Sostituisco cio' con quello sotto
        <div class="form-group">
		<label for="j_username">Username  :</label><input class="form-control" name="j_username" id="j_username" type="text" required="" />
	</div>
	<div class="form-group">
		<label for="j_password"> Password    :</label><input class="form-control" name="j_password" id="j_password" type="password" />
	</div>
        <input type="button" value="Login" onclick="window.location.href='Autenticated()';"/>
        ----------------------------------------
        
        <form method="post" action="controlla.jsp">
            Username:<input type="text" name="username" size="20" value="<%=gapPackage.userProfile.getEmail() %>" >       <br>
            Password:<input type="password" name="password" size="20" value=<%=gapPackage.userProfile.getPassword()   %> ><br>
        <input type="submit">
        </form>
        
        
        
        <div>
          <div class="alert alert-warning">
          <center><b>Importante: </b>L'accesso &egrave; consentito ai titolari di username e password</center>
          </div>
	</div>
        <div style="font-size: 10px; color: gray; text-align: center;">
          <p>powered by <a href="http://www.ing.unipi.it" target="_blank"> FaVa</a></p>
        </div>
    </body>
</html>
