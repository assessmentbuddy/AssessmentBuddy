<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:meta name="app.name"/>: Programs</title>
    </head>
    <body>
        <div class="pageinfo">Manage programs</div>
        
        <nav>
            <g:link action="create" class="btnlink">Add a new program</g:link>
        </nav>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
