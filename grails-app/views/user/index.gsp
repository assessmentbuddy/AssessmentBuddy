<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:meta name="app.name"/>: Users</title>
    </head>
    <body>
        <div class="pageinfo">Manage users</div>
        <nav>

            <g:link action="create" class="btnlink">Add a new user</g:link>
        </nav>
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
        
        <g:paginate controller="user" action="index" total="${userCount}"/>
    </body>
</html>
