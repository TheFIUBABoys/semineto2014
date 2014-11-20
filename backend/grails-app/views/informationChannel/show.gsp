<html>
<head>
    <title>Information channel</title>
    <meta name="layout" content="main">
</head>

<body>

<div class="text-center">
    <h4>
        Tweets for:
        <g:each in="${topics}" status="i" var="topic">
            ${topic}<g:if test="${i < topics.size() - 1}">,</g:if>
        </g:each>
    </h4>
</div>

<div class="columns small-6 small-centered">
    <g:each in="${tweets}" status="i" var="tweet">
        <div class="panel">
            <a href="http://www.twitter.com/${tweet.user.screenName}">
                @${tweet.user.screenName}</a> <small>${tweet.user.name}</small>
            <br>
            <small style="color:rgb(128, 124, 124)">${tweet.createdAt}</small>

            <p>${tweet.text}</p>
        </div>

    </g:each>
</div>
</body>
</html>