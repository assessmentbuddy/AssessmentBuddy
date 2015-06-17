<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: ${outcomeToEdit.id ? 'Edit' : 'Create'} outcome</title>
    </head>
    <body>
        <div class="pageinfo">${outcomeToEdit.id ? "Edit outcome ${outcomeToEdit.name}" : 'Create new outcome'}</div>

        <g:form action="save" id="${outcomeToEdit.id}">
            <fieldset>
                <div class="formfield">
                    <label class="left" for="name">Short name:</label>
                    <g:textField class="right" name="shortName" value="${outcomeToEdit.shortName}" size="40" id="focuselt"/>
                </div>
                <div class="formfield">
                    <label class="left" for="name">Description:</label>
                    <g:textArea class="right" name="description" value="${outcomeToEdit.description}" cols="40" rows="4"/>
                </div>
                <div class="formsubmit">
                    <label class="left" for="save"></label>
                    <g:submitButton class="right" name="save" value="${outcomeToEdit.id ? 'Edit outcome' : 'Save outcome'}"/>
                </div>
            </fieldset>
        </g:form>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
        <g:hasErrors bean="${flash.outcomeToEdit}">
            <div class="errors">
                <g:renderErrors bean="${flash.outcomeToEdit}"/>
            </div>
        </g:hasErrors>
    </body>
</html>
