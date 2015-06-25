<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: ${userToEdit.id ? 'Edit' : 'Create'} user</title>
    </head>
    <body>
        <div class="pageinfo">Edit ${userToEdit.id ? "user ${userToEdit.userName}" : 'New user'}</div>
        <g:form action="save" id="${params.id}">
            <fieldset>
                <!-- user fields -->
                <div class="formsection">
                    Edit ${userToEdit.id ? 'user' : 'new user'} details
                </div>
                <div class="formfield">
                    <label class="left" for="userToEdit.userName">Username:</label>
                    <g:textField class="right" name="userToEdit.userName" value="${userToEdit.userName}" id="focuselt"/>
                </div>
                <div class="formfield">
                    <label class="left" for="password">Password:</label>
                    <g:passwordField class="right" name="password" value="${password}"/>
                </div>
                <div class="formfield">
                    <label class="left" for="passwordConfirm">Confirm password:</label>
                    <g:passwordField class="right" name="passwordConfirm" value="${passwordConfirm}"/>
                </div>
                <div class="formfield">
                    <label class="left" for="userToEdit.fullName">Full name:</label>
                    <g:textField class="right" name="userToEdit.fullName" value="${userToEdit.fullName}"/>
                </div>
                <div class="formfield">
                    <label class="left" for="userToEdit.email">Email address:</label>
                    <g:textField class="right" name="userToEdit.email" value="${userToEdit.email}"/>
                </div>
                
                <!-- roles (only if user is an administrator) -->
                <g:if test="${session.user.isAdmin()}">
                    <div class="formsection">
                        Edit roles
                    </div>
                    <g:hiddenField name="roleIds" value="${roleIds.join(' ')}"/>
                    <g:each in="${userToEdit.roles?.sort { it.id }}" var="r">
                        <div class="formfield">
                            <label class="left" for="rolesToDelete.${r.id}">Current role:</label>
                            <span class="right">
                                <span class="depfield">${r.roleType}</span>
                                <span class="depfield">${r.scope}</span>
                                <g:if test="${r.program}">
                                    <span class="depfield">${r.program.name}</span>
                                </g:if>
                                <g:checkBox name="rolesToDelete.${r.id}" value="${false}"/>
                                <label for="rolesToDelete.${r.id}">Delete</label>
                            </span>
                        </div>
                    </g:each>
                    <div class="formfield">
                        <label class="left">New role:</label>
                        <span class="right">
                            <g:select
                                name="roleToAdd.roleType"
                                from="${roleTypes}"
                                optionKey="${{it?.name()}}"
                                noSelection="['' : 'Select role type']"/>
                            <g:select
                                name="roleToAdd.scope"
                                from="${scopes}"
                                optionKey="${{it?.name()}}"
                                noSelection="['' : 'Select scope']"/>
                            <g:select
                                name="roleToAdd.program"
                                from="${programs}"
                                optionKey="${{it?.id}}"
                                noSelection="['' : 'Select program']"/>
                        </span>
                    </div>
                </g:if>
                
                <div class="formsubmit">
                    <g:submitButton class="right" name="save" value="${userToEdit.id ? 'Save user' : 'Create user'}"/>
                </div>
            </fieldset>
        </g:form>
        
        <g:if test="${flash.message}">
            <div class="info">
                    ${flash.message}
            </div>
        </g:if>
        <g:hasErrors bean="${userToEdit}">
            <div class="errors">
                <g:renderErrors bean="${userToEdit}"/>
            </div>
        </g:hasErrors>
    </body>
</html>
