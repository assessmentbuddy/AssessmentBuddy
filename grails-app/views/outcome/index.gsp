<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Outcomes</title>
    </head>
    <body>
        <g:if test="${mayEdit}">
            <div class="pageinfo">Manage outcomes in ${session.program.name}</div>
        
            <g:link class="btnlink" controller="outcome" action="create">Create new outcome</g:link>

            <g:if test="${!outcomes.isEmpty()}">
                <div class="pageinfo">Edit existing outcomes in ${session.program.name}</div>

                <g:each in="${outcomes}" var="outcome">
                    <g:link
                        class="btnlink"
                        controller="outcome"
                        action="edit"
                        id="${outcome.id}">${outcome.toDisplay()}</g:link>
                </g:each>
            </g:if>
        </g:if>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
