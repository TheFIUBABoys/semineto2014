<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Login</title>
</head>

<body>
<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<br/>
<br/>

<g:form action="signIn">

    <input type="hidden" name="targetUri" value="${targetUri}"/>

    <div class="row">
        <div class="small-6 small-centered columns">
            <label for="username">Username
                <input id="username" type="text" name="username" value="${username}"/>
            </label>
        </div>

        <div class="small-6 small-centered columns">
            <label for="password">Password:
                <input id="password" type="password" name="password" value="${password}"/>
            </label>
        </div>

        <div class="small-6 small-centered columns">
            <label for="rememberMe">Remember me?
            <g:checkBox id="rememberMe" name="rememberMe" value="${rememberMe}"/>
            </label>
        </div>
    </div>

    <div class="row">
        <div class="small-6 small-centered columns">
            <button class="small" type="submit">Sign In</button>
        </div>
    </div>

</g:form>
</body>
</html>
