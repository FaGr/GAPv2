
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GAP unipi</title>
    </head>
    <body>
        <f:view>
            <h1><h:outputText value="GAP unipi" /></h1>
            <p>Seleziona l'aula e l'operazione, poi clicca su submit</p>
            Welcome ${user.firstname} ${user.lastname}!  You've been registered since ${user.since}.
            <h:form>
                
              <form action="">
                <fieldset>
                    <legend>Cosa vuoi fare?</legend>
                    Richiedi aula <input type="radio" name="linguaggio" value="html"/>
                    Prenota aula <input type="radio" name="linguaggio" value="css" disabled="${usermanager.checkStatus()}"/>
                    Annulla prenotazione aula <input type="radio" name="linguaggio" value="javascript"/>
                </fieldset>
            </form>
                
                
                
                
                
                <h:commandButton id="logout" value="Logout" action="#{usermanager.logout}"/>
            </h:form>
        </f:view>
    </body>
</html>
