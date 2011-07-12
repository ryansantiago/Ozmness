
<%@ page import="com.onb.ozmness.Rating" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'rating.label', default: 'Mentee')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    	${mentees }
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <%--<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>--%>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="lastName" title="${message(code: 'employee.lastName.label', default: 'Last Name')}" />
                        
                            <g:sortableColumn property="firstName" title="${message(code: 'employee.firstName.label', default: 'First Name')}" />
                        
                            <th><g:message code="employee.position.label" default="Position" /></th>
                            
                            <th colspan=2><g:message code="employee.position.label" default="Action" /></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${menteeList}" status="i" var="employeeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" >
                        
                            <td>${fieldValue(bean: employeeInstance, field: "lastName")}</td>
                        
                            <td>${fieldValue(bean: employeeInstance, field: "firstName")}</td>
                        
                            <td>${fieldValue(bean: employeeInstance, field: "position")}</td>
                            
                            <td><g:link action="show" id="${employeeInstance.id}">Show Info</g:link></td>
                            
                            <td><g:link controller="rating" action="showByEmployee" id="${employeeInstance.id}">View Ratings</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <br/>
			<g:link controller="employee" action="control">&lt;&lt;&nbsp;Back to Employee Control</g:link>
            </div><%--
            <div class="paginateButtons">
                <g:paginate total="${menteeTotal}" />
            </div>
        --%></div>
    </body>
</html>
