
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>

    <h1>Create a New Account</h1>
    
    <f:view>
        <h:form id="create">            
            <h:panelGrid columns="3" border="0">
                First Name: <h:inputText id="fname"       
                                         requiredMessage="*"
                                         value="#{usermanager.fname}"
                                         required="true"/>  
                            <h:message for="create:fname" style="color: red"/>
                Last Name: <h:inputText id="lname"  
                                        requiredMessage="*"
                                        value="#{usermanager.lname}"
                                        required="true"/>
                           <h:message for="create:lname" style="color: red"/>
                Username: <h:inputText id="username" 
                                       requiredMessage="*"
                                       value="#{usermanager.username}"
                                       required="true"/>
                          <h:message for="create:username" style="color: red"/>
                          
                         
                Status:   <h:selectOneRadio value="#{usermanager.status}">
                                <f:selectItem itemValue="@unipi.it" itemLabel="professore" />
                                <f:selectItem itemValue="studente" itemLabel="studente" />	   			
                             </h:selectOneRadio>  
                          <h:message for="create:status" style="color: red"/>
                Password: <h:inputSecret id="password"    
                                         requiredMessage="*"
                                         value="#{usermanager.password}"
                                         required="true"/>
                          <h:message for="create:password" style="color: red"/>
                Password (verify): <h:inputSecret id="passwordv"   
                                                  requiredMessage="*"
                                                  value="#{usermanager.passwordv}"
                                                  required="true"/>
                                   <h:message for="create:passwordv" style="color: red"/>
            </h:panelGrid>
            <h:commandButton id="submit" 
                             value="Create"
                             action="#{usermanager.createUser}"/>
            <h:messages style="color: red" globalOnly="true"/>
        </h:form>
    </f:view>
    
    </body>
</html>
