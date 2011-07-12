

<%@ page import="com.onb.ozmness.Employee" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'employee.label', default: 'Employee')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="employee.control.label" args="[entityName]" /></h1>
            
            <ul>
            	<li><g:link class="list" action="mentees"><g:message code="employee.control.menteesNavLabel" args="[entityName]" /></g:link></li>
            	
            	<li><g:link controller="rating" action="selection"><g:message code="employee.control.employeeRatingNavLabel" args="[entityName]" /></g:link></li>
            	
            	<li><g:link controller="project" action="selection">My Projects</g:link></li>
            	
            
            </ul>
            
            
        </div>
    </body>
</html>
