<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: ${indicatorToEdit.id ? 'Edit' : 'Create'} indicator</title>
    </head>
    <body>
        <div class="pageinfo">${indicatorToEdit.id ? "Edit indicator ${indicatorToEdit.toDisplay()}" : 'Create new indicator'}</div>

        <g:form action="save" id="${indicatorToEdit.id}">
            <fieldset>
                <div class="formfield">
                    <label class="left" for="programId">Outcome:</label>
                    <g:select
                        class="right"
                        name="outcomeId"
                        from="${outcomes}"
                        optionKey="id"
                        optionValue="${{it.toDisplay()}}"
                        noSelection="[ '-1' : 'Select outcome' ]"
                        />
                </div>
                <div class="formfield">
                    <label class="left" for="name">Short name:</label>
                    <g:textField class="right" name="shortName" value="${indicatorToEdit.shortName}" size="40" id="focuselt"/>
                </div>
                <div class="formfield">
                    <label class="left" for="name">Description:</label>
                    <g:textArea class="right" name="description" value="${indicatorToEdit.description}" cols="40" rows="4"/>
                </div>
                <div class="formsubmit">
                    <label class="left" for="save"></label>
                    <g:submitButton class="right" name="save" value="${indicatorToEdit.id ? 'Edit indicator' : 'Save indicator'}"/>
                </div>
            </fieldset>
        </g:form>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
        <g:hasErrors bean="${flash.indicatorToEdit}">
            <div class="errors">
                <g:renderErrors bean="${flash.indicatorToEdit}"/>
            </div>
        </g:hasErrors>
    </body>
</html>
