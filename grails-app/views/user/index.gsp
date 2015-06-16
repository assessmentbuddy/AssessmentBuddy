<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Users</title>
    </head>
    <body>
        <div class="pageinfo">Manage users</div>
        <g:link action="create" class="btnlink">Add a new user</g:link>
        <div class="pageinfo">Edit existing users</div>
        <g:each in="${users}" var="u">
            <g:link action="edit" class="btnlink" id="${u.id}">${u.userName}</g:link>
        </g:each>
        <g:paginate total="${userCount}"/>
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
