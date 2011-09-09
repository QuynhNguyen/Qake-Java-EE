<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<html>

<head>
	<title>Qake - ${title}
	</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" > 
	<link rel="stylesheet" href="/TwitterQake/css/style.css" type="text/css" media="all" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
	<script>
		window.addEventListener('load', function(){
			$.ajax({
				cache: false,
				url: "http://api.twitter.com/1/users/show.json?screen_name=teamqake",
				type: "GET",
				//data: "screen_name=TeamQake",
				dataType: "JSONP",
				statusCode: {
   					404: function(){alert("Page not found!");}
   				},
   				error: function(jqXHR, textStatus, errorThrow){alert ("Status Code: " + textStatus + " -- " + errorThrow);},
   				success: function(data, textStatus, jqXHR){
   					$("#tweet_follower").append(data['followers_count']);
					$("#tweet_following").append(data['friends_count']);
					$("#tweet_total").append(data['statuses_count']);
					$("#company_report_graph").append('<img src="http://chart.apis.google.com/chart?chs=500x225&amp;cht=p&amp;chd=t:' +data['friends_count']+ ',' + data['followers_count'] +'&amp;chdl=Following|Followers&amp;chl=Following|Followers&amp;chma=20,25,25,25&amp;chco=80C65A|4D89F9&amp;chtt=Following+v.s.+Followers+on+Twitter" width="500" height="225" alt="Following vs Followers on Twitter" />')
   				}
			});
			
			$("#account_lookup").click(function(){
				if($("#twitter_search_query").val() == ""){
					alert("Please enter a search query");
				}else{
					$.ajax({
						cache: false,
						url: "http://api.twitter.com/1/users/show.json",
						type: "GET",
						data: "screen_name="+$("#twitter_search_query").val(),
						dataType: "JSONP",
						statusCode: {
		   					404: function(){alert("Page not found!");}
		   				},
		   				error: function(jqXHR, textStatus, errorThrow){alert ("Status Code: " + textStatus + " -- " + errorThrow);},
		   				success: function(data, textStatus, jqXHR){
		   					$("#account_lookup_result").html('<p><b>Logo:</b> <img src="'+data["profile_image_url"]+'" alt="Twitter Account Lookip" /></p><p><b>Twitter Name:</b>'+$("#twitter_search_query").val()+' </p><p id="tweet_follower"><b>Total Followers:</b>'+data["followers_count"]+' </p><p id="tweet_following"><b>Total Following:</b> '+data["friends_count"]+' </p><p id="tweet_total"><b>Total Tweets:</b> '+data["statuses_count"]+' </p><p><em>For simplicity and learning sake, I wont implement the pretty graph like the PHP version!</em></p>');
		   				}
					});
				}
			});
			
		
		
		}, false);
	</script>
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