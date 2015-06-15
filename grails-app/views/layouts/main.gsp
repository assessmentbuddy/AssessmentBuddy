<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><g:layoutTitle default="AssessmentBuddy"/></title>
        <asset:stylesheet href="site.css"/>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <g:layoutHead/>
    </head>
    <body>
        <div class="content">
            <div class="header">
                <g:layoutTitle default="AssessmentBuddy"/>
            </div>
            <nav class="navbar">
                <div id="navleft">
                    <g:if test="${session.user}">
                        Welcome, ${session.user.userName}
                    </g:if>
                </div>
                &nbsp;
                <div id="navright">
                    <g:if test="${session.user}">
                        <g:link controller="home" action="index">Home</g:link>
                        | <g:link controller="login" action="logout">Log out</g:link>
                    </g:if>
                </div>
            </nav>
            <div class="body">
                <g:layoutBody/>
            </div>
            <div class="footer">
                AssessmentBuddy: An <a class="projlink" href="https://github.com/assessmentbuddy/AssessmentBuddy">open-source assessment tool</a> |
                <a href="http://www.gnu.org/licenses/agpl-3.0.en.html"><asset:image alt="AGPLv3 logo" class="logo" src="agplv3-88x31-whitebg.png"/></a>
            </div>
        </div>
    </body>
</html>
