<%--
  Created by IntelliJ IDEA.
  User: fdv
  Date: 10/25/14
  Time: 18:01
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Information channel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'foundation.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'foundation-icons.css')}" type="text/css">

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'partials/modals.css')}" type="text/css">

    <script src="${resource(dir: 'js', file: 'vendor/modernizr.js')}"></script>
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

<div class="columns small-6">
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