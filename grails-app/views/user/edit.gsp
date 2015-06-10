<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:meta name="app.name"/>: ${userToEdit.id ? 'Edit' : 'Create'} user</title>
    </head>
    <body>
        <div class="pageinfo">
            Edit ${userToEdit.id ? 'user' : 'new user'} details
        </div>
        <g:form action="save">
            <fieldset>
                <div class="formfield">
                    <label for="">Username:</label>
                    <g:textField name="userName" value="${userToEdit.userName}"/>
                </div>
                <div class="formfield">
                    <label for="password">Password:</label>
                    <g:passwordField name="password" value="${password}"/>
                </div>
                <div class="formfield">
                    <label for="passwordConfirm">Confirm password:</label>
                    <g:passwordField name="passwordConfirm" value="${passwordConfirm}"/>
                </div>
                <div class="formfield">
                    <label for="fullName">Full name:</label>
                    <g:textField name="fullName" value="${userToEdit.fullName}"/>
                </div>
                <div class="formfield">
                    <label for="email">Email address:</label>
                    <g:textField name="email" value="${userToEdit.email}"/>
                </div>
                <div class="formfield">
                    <label for="save"></label>
                    <g:submitButton name="save" value="${userToEdit.id ? 'Save user' : 'Create user'}"/>
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
