<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Sign Up</title>
</head>

<body>
<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<br/>
<br/>

<g:form action="signUp">

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
    </div>

    <div class="row">
        <div class="small-6 small-centered columns">
            <button class="small" type="submit">Sign Up</button>
        </div>
    </div>

</g:form>
</body>
</html>
