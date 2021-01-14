<!DOCTYPE html>
<head>
    <link href="/css/main.css" rel="stylesheet" type="text/css">
    <title>Signin</title>
</head>

<body>
<div id="logo">
    <h1>${title}</h1>
</div>

<div class="signin">
    <form action="/signin" method="post">
        <label>Username: </label>
        <input type="text" name="username">

        <label>Password: </label>
        <input type="password" name="password">

        <button type="submit">Sign in</button>
    </form>


</div>
</body>