<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:meta name="app.name"/>: Home</title>
    </head>
    <body>
        Welcome to the home page!
        
        <nav>
            <g:if test="${user.isAdmin()}">
                <g:link controller="user" action="index" class="btnlink">Manage users</g:link>
            </g:if>
        </nav>
    </body>
</html>
