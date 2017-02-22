
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>

    <h1>Per poter accedere è necessario effettuare il login</h1>
    
    <f:view>
        <h:messages style="color: red"
                    showDetail="true"/>
        <h:form id="login">
            <h:panelGrid columns="2" border="0">
                Mail: <h:inputText id="mail" 
                                       value="#{usermanager.mail}"/>        
                Password: <h:inputSecret id="password"
                                         value="#{usermanager.password}"/>
                <%--<h:selectOneRadio value="#{usermanager.status}">
                        <f:selectItem itemValue="Professore" itemLabel="Professore" />
                        <f:selectItem itemValue="Studente" itemLabel="Studente" />	   			
                </h:selectOneRadio> 
                --%>
                <!--rimuovere Ratio -->
                <h:selectOneRadio value="#{usermanager.status}">
                        <f:selectItem itemValue="professore" itemLabel="professore" />
                        <f:selectItem itemValue="studente" itemLabel="studente" />	   			
                </h:selectOneRadio>
            </h:panelGrid>
            <h:commandButton id="submit" 
                             type="submit"
                             value="Login"
                             action="#{usermanager.validateUser}"/>
            <br>
            <h:commandLink id="create"
                           value="Create New Account"
                           action="create"/>
        </h:form>
       
    </f:view>
    
    </body>
</html>
