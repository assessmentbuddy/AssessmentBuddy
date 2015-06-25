<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Targets</title>
    </head>
    <body>
        <g:if test="${mayEdit}">
            <div class="pageinfo">Manage targets</div>
            <g:link class="btnlink" controller="target" action="create">Create new target</g:link>

            <div class="pageinfo">Edit existing targets</div>

            <g:each in="${targets}" var="target">
                <g:link
                    class="btnlink"
                    controller="target"
                    action="edit"
                    id="${target.id}">${target.toDisplay()}</g:link>
            </g:each>
        </g:if>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
