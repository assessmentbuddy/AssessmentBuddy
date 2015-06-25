<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: ${rubricToEdit.id ? 'Edit' : 'Create'} rubric</title>
    </head>
    <body>
        <div class="pageinfo">${rubricToEdit.id ? "Edit rubric ${rubricToEdit.toDisplay()}" : 'Create new rubric'} in ${session.program.name}</div>

        <g:form action="save" id="${rubricToEdit.id}">
            <fieldset>
                <div class="formsection">
                    Edit rubric details
                </div>
                <div class="formfield">
                    <label class="left" for="rubric.name">Name:</label>
                    <g:textField class="right" name="rubric.name" value="${rubricToEdit.name != 0 ? rubricToEdit.name : ''}" size="40" id="focuselt"/>
                </div>
                
                <div class="formsection">Edit achievement levels</div>
                <g:hiddenField name="achievementLevelIds" value="${achievementLevelIds.collect { it.id }.join(' ')}"/>
                <g:each in="${rubricToEdit.achievementLevels ? rubricToEdit.achievementLevels.sort { it.rank } : []}" var="a">
                    <div class="formfield">
                        <label class="left" for="achievementLevelsToDelete.${a.id}">Achievement level:</label>
                        <span class="right">
                            <span class="depfield">${a.name}</span>
                            <span class="depfield">${a.rank}</span>
                            <label for="achievementLevelsToDelete.${a.id}">Delete:</label> <g:checkBox name="achievementLevelsToDelete.${a.id}" value="${false}"/>
                        </span>
                    </div>
                </g:each>
                <div class="formfield">
                    <label class="left" for="achivementLevelToAdd.name">Add achievement level:</label>
                    <div class="right">
                        Name: <g:textField name="achievementLevelToAdd.name" size="20"/><br>
                        Rank: <g:textField name="achievementLevelToAdd.rank" size="5"/>
                    </div>
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
