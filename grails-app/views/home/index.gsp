<!DOCTYPE html>
<html>
    <head>
        <title><g:meta name="app.name"/>: Home</title>
    </head>
    <body>
        <h1><g:meta name="app.name"/>: Home</h1>
        Welcome to the home page!
		
		<g:if test="${user.isAdmin()}">
			<g:link controller="user" action="index">Manage users</g:link>
		</g:if>
    </body>
</html>
