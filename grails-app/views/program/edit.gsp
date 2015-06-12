<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:meta name="app.name"/>: ${programToEdit.id ? 'Edit' : 'Create'} program</title>
    </head>
    <body>
        <div class="pageinfo">${programToEdit.id ? 'Create new' : 'Edit'} program</div>

        <g:form action="save">
            <fieldset>
                <div class="formfield">
                    <label for="name">Program name:</label>
                    <g:textField name="name" value="${programToEdit.name}" size="40"/>
                </div>
                <div class="formfield">
                    <label for="save"></label>
                    <g:submitButton name="save" value="${programToEdit.id ? 'Edit program' : 'Save program'}"/>
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
