<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: ${academicYearToEdit.id ? 'Edit' : 'Create'} academic year</title>
    </head>
    <body>
        <div class="pageinfo">${academicYearToEdit.id ? "Edit academic year ${academicYearToEdit.toDisplay()}" : 'Create new academic year'}</div>

        <g:form action="save" id="${academicYearToEdit.id}">
            <fieldset>
                <div class="formfield">
                    <label class="left" for="name">Start:</label>
                    <g:textField class="right" name="start" value="${academicYearToEdit.start != 0 ? academicYearToEdit.start : ''}" size="10" id="focuselt"/>
                </div>
                <div class="formfield">
                    <label class="left" for="name">End:</label>
                    <g:textField class="right" name="end" value="${academicYearToEdit.end != 0 ? academicYearToEdit.end : ''}" size="10"/>
                </div>
                <div class="formsubmit">
                    <label class="left" for="save"></label>
                    <g:submitButton class="right" name="save" value="${academicYearToEdit.id ? 'Edit academic year' : 'Save academic year'}"/>
                </div>
            </fieldset>
        </g:form>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
        <g:hasErrors bean="${flash.academicYearToEdit}">
            <div class="errors">
                <g:renderErrors bean="${flash.academicYearToEdit}"/>
            </div>
        </g:hasErrors>
    </body>
</html>
