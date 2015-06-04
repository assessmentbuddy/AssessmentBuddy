<!DOCTYPE html>
<html>
    <head>
        <title><g:meta name="app.name"/>: Log in</title>
    </head>
    <body>
        <h1><g:meta name="app.name"/>: Log in</h1>
        Please enter your username and password:
        <g:form action="login">
            <table>
                <tr>
                    <td><label for="userName">Username:</label></td>
                    <td><g:textField name="userName"/></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><g:passwordField name="password"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><g:submitButton name="login" value="Log in"/></td>
                </tr>
            </table>
        </g:form>
        <g:if test="${flash.message}">
            ${flash.message}
        </g:if>
    </body>
</html>
