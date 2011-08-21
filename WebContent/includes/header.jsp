<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<html>

<head>
	<title>Qake - ${title} </title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" > 
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
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
		<form><label>Email </label> <input type="email" name="email" /> <label>Password: </label> <input type="password" name="password" /> <input type="submit" value="login" /> <a href="signup.html">Sign Up</a></form>
	</div>
	<div id="container">
		<header id="header">
		<a href="./index.html"><img src="images/cake-logo.png" alt="Qake Logo" title="Qake.info" border="0"/></a><span>Welcome to Qake!</span>
		</header>
		<div id="main">