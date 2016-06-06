<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
Music :: a Spring Framework demonstration
--%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags --%>

    <spring:url value="/resources/images/favicon.ico" var="favicon"/>
    <link rel="shortcut icon" type="image/x-icon" href="${favicon}">

    <title>Music :: a Spring Framework demonstration</title>

    <%-- CSS generated from LESS --%>
    <spring:url value="/resources/css/music.css" var="musicCss"/>
    <link href="${musicCss}" rel="stylesheet"/>


    <%-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries --%>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Only datepicker is used -->
    <spring:url value="/vendors/jquery-ui/themes/base/minified/jquery-ui.min.css" var="jQueryUiCss"/>
    <link href="${jQueryUiCss}" rel="stylesheet"/>
    <spring:url value="/vendors/jquery-ui/themes/base/minified/jquery.ui.theme.min.css" var="jQueryUiThemeCss"/>
    <link href="${jQueryUiThemeCss}" rel="stylesheet"/>
    <spring:url value="/vendors/jquery-ui/themes/base/minified/jquery.ui.datepicker.min.css" var="jQueryUiDatePickerCss"/>
    <link href="${jQueryUiDatePickerCss}" rel="stylesheet"/>
    
    <!-- Initialize scripts -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>

    <spring:url value="/resources/js/instruments.js" var="musicJs"/>
	<script src="${musicJs}"></script>
</head>
