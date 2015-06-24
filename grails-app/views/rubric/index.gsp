<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Rubrics</title>
    </head>
    <body>
        <g:if test="${mayEdit}">
            <div class="pageinfo">Manage rubrics</div>
            <g:link class="btnlink" controller="rubric" action="create">Create new rubric</g:link>

            <div class="pageinfo">Edit existing rubrics</div>

            <g:each in="${rubrics}" var="rubric">
                <g:link
                    class="btnlink"
                    controller="rubric"
                    action="edit"
                    id="${rubric.id}">${rubric.toDisplay()}</g:link>
            </g:each>
        </g:if>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
