<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<html>

<head>
	<title>Qake - ${title}
	</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" > 
	<link rel="stylesheet" href="/TwitterQake/css/style.css" type="text/css" media="all" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
	
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
		${globalheader}
	</div>
	<div id="container">
		<header id="header">
		<a href="/TwitterQake/index.html"><img src="/TwitterQake/images/cake-logo.png" alt="Qake Logo" title="Qake.info" border="0"/></a><span>Welcome to Qake! -- ${User.email}${debug} <br/>${whatif}</span>
		</header>
		<div id="main">