<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Outcomes</title>
    </head>
    <body>
        <div class="pageinfo">Manage outcomes</div>
        
        <g:link class="btnlink" controller="outcome" action="create">Create new outcome</g:link>
        
        <g:if test="${session.program}">
            <div class="pageinfo">Edit existing outcomes in ${session.program.name}</div>

            <g:each in="${outcomes}" var="outcome">
                <g:link
                    enabled="${session.program != null}"
                    class="btnlink"
                    controller="outcome"
                    action="edit"
                    id="${outcome.id}">${outcome.toDisplay()}</g:link>
            </g:each>
        </g:if>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
