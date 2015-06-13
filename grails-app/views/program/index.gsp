<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Programs</title>
    </head>
    <body>
        <div class="pageinfo">Manage programs</div>
        <g:link action="create" class="btnlink">Add a new program</g:link>
        
        <div class="pageinfo">Edit existing programs</div>
        <g:each in="${programs}" var="p">
            <g:link action="edit" id="${p.id}" class="btnlink">${p.name}</g:link>
        </g:each>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
