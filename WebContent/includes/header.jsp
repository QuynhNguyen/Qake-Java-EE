<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<html>

<head>
	<title>
	<% if( request.getAttribute("title") == null){ %>
		Qake - Welcome to Qake
	<%} else {%>
		Qake - ${title} 
	<% } %>
	</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" > 
	<link rel="stylesheet" href="/TwitterQake/css/style.css" type="text/css" media="all" />
	<style>
		#floatingHeader{
			background-color: black;
			color: white;
			width: 100%;
			height: 35px;
			margin: 0 auto;
			text-align: center;
		}
		#floatingHeader, form > a {color: white;}
		form{padding : 5px;}
		
	</style>
	
</head>

<body>
	<div id="floatingHeader">
		<% if(session.getAttribute("User") == null){ %>
		<form action="/TwitterQake/login.html" method="POST"><label>Email </label> <input type="email" name="email" /> <label>Password: </label> <input type="password" name="password" /> <input type="submit" value="login" /> <a href="signup.html">Sign Up</a></form>
		<% } else { %>
			Welcome back, <em>${User.email}</em> ----- <input type="button" value="Control Panel" onclick="window.location='/TwitterQake/controlpanel.html'"/> <input type="button" value="Logout" onclick="window.location='/TwitterQake/logout.html'"/>
		<% } %>
	</div>
	<div id="container">
		<header id="header">
		<a href="/TwitterQake/index.html"><img src="/TwitterQake/images/cake-logo.png" alt="Qake Logo" title="Qake.info" border="0"/></a><span>Welcome to Qake! -- ${User.email}${debug} <br/>${whatif}</span>
		</header>
		<div id="main">