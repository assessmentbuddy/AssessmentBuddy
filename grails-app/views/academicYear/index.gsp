<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Academic Years</title>
    </head>
    <body>
        <g:if test="${mayEdit}">
            <div class="pageinfo">Manage academic years</div>
            <g:link class="btnlink" controller="academicYear" action="create">Create new academic year</g:link>

            <div class="pageinfo">Edit existing academic years</div>

            <g:each in="${academicYears}" var="academicYear">
                <g:link
                    class="btnlink"
                    controller="academicYear"
                    action="edit"
                    id="${academicYear.id}">${academicYear.toDisplay()}</g:link>
            </g:each>
        </g:if>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
    </body>
</html>
