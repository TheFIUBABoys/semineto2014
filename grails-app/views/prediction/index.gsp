<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>EsteSale?</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'partials/tweetPage.css')}" type="text/css">

</head>

<body>

<div class="row">
    <div class="columns">
        <g:form action="showService">
            <g:select name="service"
                      value="${serviceId}"
                      from="${serviceList}"
                      optionKey="id"
                      optionValue="name"
                      onchange="submit()"
            />
        </g:form>
    </div>

    <div class="columns">
        <g:if test="${positiveUpdates.size > negativeUpdates.size}">
            <div class="alert-box success">
                <i class="fi-arrow-up">SALE :)</i>
                <a href="https://twitter.com/share" class="twitter-share-button" data-url="http://estesale.com.ar"
                   data-text="${serviceName}, Sale!" data-hashtags="EsteSale">Tweet</a>
            </div>
        </g:if>
        <g:if test="${negativeUpdates.size >= positiveUpdates.size}">
            <div class="alert-box warning">
                <i class="fi-arrow-down">NO SALE :(</i>
                <a href="https://twitter.com/share" class="twitter-share-button" data-url="http://estesale.com.ar"
                   data-text="${serviceName}, No Sale!" data-hashtags="EsteSale">Tweet</a>
            </div>
        </g:if>
        <a href="#" data-reveal-id="new-rate">Calificar esta predicción <i class="step fi-megaphone"></i></a>

    </div>

    <div class="columns small-6">
        <div class="column-label">Positivo</div>
        <g:each in="${positiveUpdates}" status="i" var="update">
            <div class="panel">
                <a href="${update.getSourceUrl()}" target="_blank">Go to Source</a>
                <br>
                <small style="color:rgb(128, 124, 124)">${update.getSourceDateCreated()}</small>

                <p>${update.getBody()}</p>
            </div>
        </g:each>
    </div>

    <div class="columns small-6">
        <div class="column-label">Negativo</div>
        <g:each in="${negativeUpdates}" status="i" var="update">
            <div class="panel">
                <a href="{update.getSourceUrl()}"></a> <small>Source</small>
                <br>
                <small style="color:rgb(128, 124, 124)">${update.getSourceDateCreated()}</small>

                <p>${update.getBody()}</p>
            </div>
        </g:each>
    </div>
</div>

<div class="container">
</div>


<!-- CSS: partials/modals.css -->
<div id="new-rate" class="reveal-modal" data-reveal>
    <a class="close-reveal-modal">&#215;</a>

    <h3>Opinar sobre la predicción</h3>
    <hr>

    <form>
        <div class="row">
            <div class="small-6 large-4 columns">
                <label>La predicción me pareció...
                    <select id="positiveNegativeSelect">
                        <option value="true">Buena</option>
                        <option value="false">Mala</option>
                    </select>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-12 columns">
                <label>Porque...
                    <textarea name="commentTextArea" placeholder="Comentario"></textarea>
                </label>
            </div>
        </div>
        <input type="button" class="small" value="Enviar" id="btn_submit" onclick="return sendFeedback()">
    </form>
</div>

<g:javascript library="application"/>

<script src="${resource(dir: 'js', file: 'vendor/jquery.js')}"></script>
<script src="${resource(dir: 'js', file: 'foundation.min.js')}"></script>
<script>
    $(document).foundation();
</script>
<script type="text/javascript">
    window.twttr = (function (d, s, id) {
        var t, js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {
            return
        }
        js = d.createElement(s);
        js.id = id;
        js.src = "https://platform.twitter.com/widgets.js";
        fjs.parentNode.insertBefore(js, fjs);
        return window.twttr || (t = {
                    _e: [], ready: function (f) {
                        t._e.push(f)
                    }
                })
    }(document, "script", "twitter-wjs"));
</script>
<script>
    function sendFeedback() {
        var isPositive = $("#positiveNegativeSelect").val() == "true" ? "true" : "false";
        $.post('http://localhost:8080/seminario-latest-grails/api/predictionRating',
                {"comment": $("textarea").val(), "isPositive": isPositive},
                function (response) {
                    alert("Created comment" + JSON.stringify(response));
                }).error(function () {
                    alert("Could not post");
                });

        return isPositive;
    }
</script>
</body>
</html>
