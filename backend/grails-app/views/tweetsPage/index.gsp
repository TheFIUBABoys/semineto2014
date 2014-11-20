<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>EsteSale?</title>
</head>
<body>

<div class="row">
    <div class="columns">
        <g:select name="service"
                  from="${serviceList}"
                  optionKey="id"
                  optionValue="name"
                  onchange="${remoteFunction(action: 'index',
                          params: '\'service=\' + this.value',
                          method: "GET")}"
          />
    </div>
    <div class="columns">
        <g:if test="${positiveUpdates.size > negativeUpdates.size}">
            <div class="alert-box success">
                <i class="fi-arrow-up"> SALE :)</i>
            </div>
        </g:if>
        <g:if test="${negativeUpdates.size >= positiveUpdates.size}">
            <div class="alert-box warning">
                <i class="fi-arrow-down"> NO SALE :(</i>
            </div>
        </g:if>
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

</body>
</html>
