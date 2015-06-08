<!DOCTYPE html>
<html>
    <head>
        <title><g:meta name="app.name"/>: Users</title>
    </head>
    <body>
        <h1><g:meta name="app.name"/>: Users</h1>
        Create/edit users
		
		<p><g:link action="create">Add a new user</g:link></p>
		
        <g:if test="${flash.message}">
            ${flash.message}
        </g:if>
		
		<g:paginate controller="user" action="index" total="${userCount}"/>
    </body>
</html>
