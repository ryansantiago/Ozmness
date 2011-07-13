
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
			<h2>Mentored Employees</h2>
			
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="lastName" title="${message(code: 'employee.lastName.label', default: 'Name')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${menteeList}" status="i" var="employeeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
							<td><g:link action="byEmployee" id="${employeeInstance.id}">${employeeInstance}</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
				</table>
            </div>
            <g:if test="${menteeListTotal > 10 }">
            <div class="paginateButtons">
                <g:paginate total="${menteeListTotal}" />
            </div>
			</g:if><br/>
			<g:link action="byEmployee" id="${self.id}">View my ratings</g:link></li><br/><br/>
				
			<g:link controller="employee" action="control">&lt;&lt;&nbsp;Back to Employee Control</g:link>
        </div>
    </body>
</html>
