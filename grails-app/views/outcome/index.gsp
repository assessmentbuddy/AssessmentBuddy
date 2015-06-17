<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Outcomes</title>
    </head>
    <body>
        <div class="pageinfo">Manage outcomes</div>
        
        <g:link class="btnlink" controller="outcome" action="create">Create new outcome</g:link>
        
        <div class="pageinfo">Edit existing outcomes</div>
        
        <g:each in="${outcomes}" var="outcome">
            <ab:conditionalLink
                enabled="${session.program != null}"
                class="btnlink"
                controller="outcome"
                action="edit"
                id="${outcome.id}">${outcome.shortName} &mdash; ${outcome.description}</ab:conditionalLink>
        </g:each>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
