<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:meta name="app.name"/>: Log in</title>
    </head>
    <body>
        Please enter your username and password:
        <g:form action="login">
            <fieldset>
                <div class="formfield">
                    <label for="userName">Username:</label>
                    <g:textField name="userName"/>
                </div>
                <div class="formfield">
                    <label for="password">Password:</label>
                    <g:passwordField name="password"/>
                </div>
                <div class="formfield">
                    <label for="login"></label>
                    <g:submitButton name="login" value="Log in"/>
                </div>
            </fieldset>
        </g:form>
        <g:if test="${flash.message}">
            ${flash.message}
        </g:if>
    </body>
</html>
