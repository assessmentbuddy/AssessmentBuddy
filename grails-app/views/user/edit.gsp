<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:meta name="app.name"/>: ${userToEdit.id ? 'Edit' : 'Create'} user</title>
    </head>
    <body>
        <g:form action="save" id="${params.id}">
            <fieldset>
                <!-- user fields -->
                <div class="pageinfo">
                    Edit ${userToEdit.id ? 'user' : 'new user'} details
                </div>
                <div class="formfield">
                    <label class="left" for="userToEdit.userName">Username:</label>
                    <g:textField class="right" name="userToEdit.userName" value="${userToEdit.userName}"/>
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
                <div class="pageinfo">
                    Edit roles
                </div>
                <g:if test="${session.user.isAdmin()}">
                    <g:each in="${userToEdit.roles}" var="r">
                        <div class="formfield">
                            <label class="left">Current role:</label>
                            <span class="right">
                                <span class="roleType">${r.roleType}</span> <span class="scope">${r.scope}</span>
                                <g:checkBox name="rolesToDelete.${r.id}" value="${false}"/>
                                <label for="rolesToDelete.${r.id}">Delete</label>
                            </span>
                        </div>
                    </g:each>
                    <div class="formfield">
                        <label class="left">New role:</label>
                        <span class="right">
                            <g:select name="roleToAdd.roleType" from="${roleTypes}"
                                noSelection="['' : 'Select role type']"/>
                            <g:select name="roleToAdd.scope" from="${scopes}"
                                noSelection="['' : 'Select scope']"/>
                            <g:select name="roleToAdd.program" from="${programs}"
                                noSelection="['' : 'Select program']"/>
                        </span>
                    </div>
                </g:if>
                
                <div class="formfield">
                    <label class="left" for="save"></label>
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
