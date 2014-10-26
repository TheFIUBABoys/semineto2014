<%@ page import="com.seminario.AuthController" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
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
    <g:layoutHead/>
</head>

<body>

<!-- Top navigation bar -->
<nav class="top-bar" data-topbar role="navigation">
    <ul class="title-area">
        <li class="name">
            <h1>
                <a href="<g:createLink controller="landingPage"/>">Este Sale?</a>
            </h1>
        </li>
        <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
    </ul>

    <section class="top-bar-section">
        <!-- Right Nav Section -->
        <ul class="right">
            <shiro:isLoggedIn>
                <li>
                    <div class="user-welcome">Welcome, <span class="username"><shiro:principal/></span></div>
                </li>
            </shiro:isLoggedIn>
            <shiro:isNotLoggedIn>
                <li>
                    <g:link controller="auth" action="register">SignUp</g:link>
                </li>
            </shiro:isNotLoggedIn>
            <li>
                <shiro:isLoggedIn>
                    <g:link controller="auth" action="signOut">Logout</g:link>
                </shiro:isLoggedIn>
                <shiro:isNotLoggedIn>
                    <g:link controller="auth" action="index">Login</g:link>
                </shiro:isNotLoggedIn>
            </li>
            <li class="active"><a href="#">Feedback</a></li>
            <li class="has-dropdown">
                <a href="#">About Us <i class="step fi-info"></i></a>
                <ul class="dropdown">
                    <li><a href="#">Que es Este Sale?</a></li>
                    <li><a href="#">Quienes somos los creadores?</a></li>
                </ul>
            </li>
        </ul>

        <!-- Left Nav Section -->
        <ul class="left">
            <li>
                <a href="#">Estado <i class="step fi-marker"></i></a>
            </li>
            <li>
                <a href="#" data-reveal-id="new-prediction">Aportar <i class="step fi-megaphone"></i></a>
            </li>
            <li>
                <a href="#">Favoritos <i class="step fi-heart"></i></a>
            </li>
            <li>
                <a href="<g:createLink controller="tweetsPage"/>">Lista de tweets</a>
            </li>
            <li>
                <g:link controller="informationChannel" action="show"
                        params="[keywords:['#TrenTigre', 'tren tigre']]">Show channel</g:link>
            </li>
        </ul>
    </section>
</nav>

<g:layoutBody/>

<!-- CSS: partials/modals.css -->
<div id="new-prediction" class="reveal-modal" data-reveal>
    <a class="close-reveal-modal">&#215;</a>

    <h3>Prediccion de estado</h3>
    <hr>

    <form>
        <div class="row">
            <!-- Transport type -->
            <div class="small-6 large-4 columns">
                <label>Para qué transporte?
                    <select>
                        <option value="tren">Tren</option>
                        <option value="subte">Subte</option>
                    </select>
                </label>
            </div>
            <!-- Service line -->
            <div class="small-6 large-4 columns">
                <label>Para qué línea?
                    <select>
                        <option value="tigre">Tigre</option>
                        <option value="mitre">Mitre</option>
                    </select>
                </label>
            </div>
            <!-- Prediction status -->
            <div class="small-6 large-4 columns">
                <label>En qué estado está?
                    <select>
                        <option value="down">Demorado/Interrumpido</option>
                        <option value="up">Ok</option>
                    </select>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-12 columns">
                <label>Comentario
                    <textarea placeholder="Déjenos un comentario que refuerce la predicción"></textarea>
                </label>
            </div>
        </div>
    </form>
</div>

<g:javascript library="application"/>

<script src="${resource(dir: 'js', file: 'vendor/jquery.js')}"></script>
<script src="${resource(dir: 'js', file: 'foundation.min.js')}"></script>
<script>
    $(document).foundation();
</script>
</body>
</html>
