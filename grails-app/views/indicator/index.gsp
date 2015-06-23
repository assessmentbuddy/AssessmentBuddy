<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Indicators</title>
    </head>
    <body>
        <g:if test="${mayEdit}">
            <div class="pageinfo">Manage indicators</div>
            <g:link class="btnlink" controller="indicator" action="create">Create new indicator</g:link>

            <div class="pageinfo">Edit existing indicators in ${session.program.name}</div>

            <g:each in="${indicators}" var="indicator">
                <g:link
                    class="btnlink"
                    controller="indicator"
                    action="edit"
                    id="${indicator.id}">${indicator.toDisplay()}</g:link>
            </g:each>
        </g:if>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
