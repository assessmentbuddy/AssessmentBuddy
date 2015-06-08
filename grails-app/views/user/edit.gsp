<!DOCTYPE html>
<html>
    <head>
        <title><g:meta name="app.name"/>: Edit user</title>
    </head>
    <body>
        <h1><g:meta name="app.name"/>: Edit user</h1>
		
		<g:form action="save">
			<table>
				<tr>
					<td><label for="">Username:</label></td>
					<td><g:textField name="userName" value="${userToEdit.userName}"/></td>
				</tr>
				<tr>
					<td><label for="password">Password:</label></td>
					<td><g:passwordField name="password" value="${password}"/></td>
				</tr>
				<tr>
					<td><label for="passwordConfirm">Confirm password:</label></td>
					<td><g:passwordField name="passwordConfirm" value="${passwordConfirm}"/></td>
				</tr>
				<tr>
					<td><label for="fullName">Full name:</label></td>
					<td><g:textField name="fullName" value="${userToEdit.fullName}"/></td>
				</tr>
				<tr>
					<td><label for="email">Email address:</label></td>
					<td><g:textField name="email" value="${userToEdit.email}"/></td>
				</tr>
				<tr>
					<td></td>
					<td><g:submitButton name="save" value="${userToEdit.id ? 'Save user' : 'Create user'}"/></td>
				</tr>
			</table>
		</g:form>
		
		<div>
			<g:if test="${flash.message}">
				${flash.message}
			</g:if>
		</div>
		<g:hasErrors bean="${userToEdit}">
			<div>
				User to edit has errors?
			</div>
		</g:hasErrors>
		<div>
			<g:renderErrors bean="${userToEdit}"/>
		</div>
    </body>
</html>
