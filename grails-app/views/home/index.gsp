<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Home</title>
    </head>
    <body>
        <div class="pageinfo">Welcome to AssessmentBuddy!</div>
        
        <g:if test="${user.isAdmin()}">
            <g:link controller="user" action="index" class="btnlink">Manage users</g:link>
            <g:link controller="program" action="index" class="btnlink">Manage programs</g:link>
        </g:if>
    </body>
</html>
