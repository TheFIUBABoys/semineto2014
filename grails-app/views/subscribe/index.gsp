<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>EsteSale?</title>
</head>

<body>

<div class="panel">
    <g:form action="update">
        <g:each in="${allServices}" var="service">
            <li>
                ${service.key} <g:checkBox name="${service.key}" value="${service.value}"/>
            </li>
        </g:each>
        <button class="small" type="submit">Update</button>
    </g:form>
</div>

</body>
</html>
