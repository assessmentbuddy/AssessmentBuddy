<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:meta name="app.name"/>: Log in</title>
    </head>
    <body>
        <div class="pageinfo">
            Please enter your username and password
        </div>
        <g:form action="login">
            <fieldset>
                <div class="formfield">
                    <label class="left" for="userName">Username:</label>
                    <g:textField class="right" name="userName"/>
                </div>
                <div class="formfield">
                    <label class="left" for="password">Password:</label>
                    <g:passwordField class="right" name="password"/>
                </div>
                <div class="formsubmit">
                    <g:submitButton class="right" name="login" value="Log in"/>
                </div>
            </fieldset>
        </g:form>
        <g:if test="${flash.message}">
            ${flash.message}
        </g:if>
    </body>
</html>
