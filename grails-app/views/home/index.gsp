<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:meta name="app.name"/>: Home</title>
    </head>
    <body>
        Welcome to the home page!
        
        <g:if test="${user.isAdmin()}">
            <g:link controller="user" action="index">Manage users</g:link>
        </g:if>
    </body>
</html>
