<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:meta name="app.name"/>: Users</title>
    </head>
    <body>
        <p><g:link action="create">Add a new user</g:link></p>
        
        <g:if test="${flash.message}">
            ${flash.message}
        </g:if>
        
        <g:paginate controller="user" action="index" total="${userCount}"/>
    </body>
</html>
