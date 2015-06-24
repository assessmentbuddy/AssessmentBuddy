<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: ${rubricToEdit.id ? 'Edit' : 'Create'} rubric</title>
    </head>
    <body>
        <div class="pageinfo">${rubricToEdit.id ? "Edit rubric ${rubricToEdit.toDisplay()}" : 'Create new rubric'} in ${session.program.name}</div>

        <g:form action="save" id="${rubricToEdit.id}">
            <fieldset>
                <div class="formfield">
                    <label class="left" for="name">Name:</label>
                    <g:textField class="right" name="name" value="${rubricToEdit.name != 0 ? rubricToEdit.name : ''}" size="40" id="focuselt"/>
                </div>
                <div class="formsubmit">
                    <label class="left" for="save"></label>
                    <g:submitButton class="right" name="save" value="${rubricToEdit.id ? 'Edit rubric' : 'Save rubric'}"/>
                </div>
            </fieldset>
        </g:form>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
        <g:hasErrors bean="${flash.rubricToEdit}">
            <div class="errors">
                <g:renderErrors bean="${flash.rubricToEdit}"/>
            </div>
        </g:hasErrors>
    </body>
</html>
