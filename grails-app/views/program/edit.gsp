<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: ${programToEdit.id ? 'Edit' : 'Create'} program</title>
    </head>
    <body>
        <div class="pageinfo">${programToEdit.id ? "Edit program ${programToEdit.name}" : 'Create new program'}</div>

        <g:form action="save" id="${programToEdit.id}">
            <fieldset>
                <div class="formfield">
                    <label class="left" for="name">Program name:</label>
                    <g:textField class="right" name="name" value="${programToEdit.name}" size="40" id="focuselt"/>
                </div>
                <div class="formsubmit">
                    <label class="left" for="save"></label>
                    <g:submitButton class="right" name="save" value="${programToEdit.id ? 'Edit program' : 'Save program'}"/>
                </div>
            </fieldset>
        </g:form>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
        <g:hasErrors bean="${flash.programToEdit}">
            <div class="errors">
                <g:renderErrors bean="${flash.programToEdit}"/>
            </div>
        </g:hasErrors>
    </body>
</html>
