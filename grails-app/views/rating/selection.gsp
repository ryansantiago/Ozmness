
<%@ page import="com.onb.ozmness.Rating" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'rating.label', default: 'Rating')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1>Employee Rating Select</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
			<label>Mentored Employees</label>
			<ul>
				<g:each in="${mentees}" status="i" var="employeeInstance">
					<li><g:link action="byEmployee" id="${employeeInstance.id}">${employeeInstance}</g:link></li>
				</g:each>
            </ul>
            <br/>
			<g:link action="byEmployee" id="${self.id}">View my ratings</g:link></li><br/><br/>
				
			<g:link controller="employee" action="control">&lt;&lt;&nbsp;Back to Employee Control</g:link>
        </div>
    </body>
</html>
