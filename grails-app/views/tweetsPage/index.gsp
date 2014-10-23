<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>EsteSale?</title>
</head>
<body>

<div class="row">
    <div class="columns">
        <g:if test="${positiveTweets.size > negativeTweets.size}">
            <div class="alert-box success">
                <i class="fi-arrow-up"> UP</i>
            </div>
        </g:if>
        <g:if test="${negativeTweets.size >= positiveTweets.size}">
            <div class="alert-box warning">
                <i class="fi-arrow-down"> DOWN</i>
            </div>
        </g:if>
    </div>

    <div class="columns small-6">
        <g:each in="${positiveTweets}" status="i" var="tweet">
            <div class="panel">
                <a href="http://www.twitter.com/${tweet.user.screenName}">@${tweet.user.screenName}</a> <small>${tweet.user.name}</small>
                <br>
                <small style="color:rgb(128, 124, 124)">${tweet.createdAt}</small>
                <p>${tweet.text}</p>
            </div>
        </g:each>
    </div>
    <div class="columns small-6">
        <g:each in="${negativeTweets}" status="i" var="tweet">
            <div class="panel">
                <a href="http://www.twitter.com/${tweet.user.screenName}">@${tweet.user.screenName}</a> <small>${tweet.user.name}</small>
                <br>
                <small style="color:rgb(128, 124, 124)">${tweet.createdAt}</small>
                <p>${tweet.text}</p>
            </div>
        </g:each>
    </div>
</div>
<div class="container">
</div>

</body>
</html>
