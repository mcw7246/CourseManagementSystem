<!DOCTYPE html>

<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>

<body>
<div id="logo">
<h1>${title}</h1>
</div>

<div id="homePageActions">
    <#if !signin>
        <form action="/" method="get">
            <button>Signin!</button>
        </form>
    </#if>
</div>
</body>