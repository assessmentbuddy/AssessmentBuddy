<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: ${targetToEdit.id ? 'Edit' : 'Create'} target</title>
    </head>
    <body>
        <div class="pageinfo">${targetToEdit.id ? "Edit target ${targetToEdit.toDisplay()}" : 'Create new target'} in ${session.program.name}</div>

        <g:form action="save" id="${targetToEdit.id}">
            <fieldset>
                <div class="formsection">
                    Edit target details
                </div>
                <div class="formfield">
                    <label class="left" for="target.name">Name:</label>
                    <g:textField class="right" name="target.name" value="${targetToEdit.name != 0 ? targetToEdit.name : ''}" size="40" id="focuselt"/>
                </div>

                <div class="formfield">
                    <label class="left" for="target.rubricId">Rubric:</label>
                    <g:select
                        class="right"
                        name="target.rubricId"
                        from="${rubrics}"
                        optionKey="id"
                        optionValue="${{it.toDisplay()}}"
                        noSelection="[ '-1' : 'Select rubric' ]"
                        value="${targetToEdit.rubric ? targetToEdit.rubric.id : -1}"
                        />
                </div>
                
                <div class="formfield">
                    <label class="left" for="target.achievementLevelId">Achievement level:</label>
                    <g:select
                        class="right"
                        name="target.achievementLevelId"
                        from="${achievementLevels}"
                        optionKey="id"
                        noSelection="[ '-1' : 'Select achievement level' ]"
                        value="${targetToEdit.achievementLevel ? targetToEdit.achievementLevel.id : -1}"
                        />
                </div>

                <div class="formfield">
                    <label class="left" for="target.percentAtOrAbove">% at or above:</label>
                    <g:textField class="right" name="target.percentAtOrAbove" value="${targetToEdit.percentAtOrAbove}" size="5"/>
                </div>

                <div class="formsubmit">
                    <label class="left" for="save"></label>
                    <g:submitButton class="right" name="save" value="${targetToEdit.id ? 'Edit target' : 'Save target'}"/>
                </div>
            </fieldset>
        </g:form>
        
        <g:if test="${flash.message}">
            <div class="info">
                ${flash.message}
            </div>
        </g:if>
        <g:hasErrors bean="${flash.targetToEdit}">
            <div class="errors">
                <g:renderErrors bean="${flash.targetToEdit}"/>
            </div>
        </g:hasErrors>
    </body>
</html>
