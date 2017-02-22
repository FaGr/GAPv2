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
             Welcome ${user.firstname} ${user.lastname}.
            <p>Seleziona l'aula e l'operazione, poi clicca su submit</p>
            <h:form>
                
              <form action="">
                <fieldset>
                    <legend>Cosa vuoi fare?</legend>
                        <h:commandButton id="crea_csv" value="Crea csv" action="#{csvwritereadtest.prova}"/>
                        <h:commandButton id="prenota" value="Prenota aula" action="#{aula.prenota(aula.getNomeaula(), user.getStatus(), user.getMail())}"/>
                        <h:commandButton id="cancella" value="Cancella prenotazione" action="#{aula.cancella(aula.getNomeaula(), user.getStatus(), user.getMail())}"/>
                </fieldset>
            </form>
                
                
                
                
                
                <h:commandButton id="logout" value="Logout" action="#{usermanager.logout}"/>
            </h:form>
        </f:view>
    </body>
</html>
