
<jsp:include page="../../includes/header.jsp"></jsp:include>

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

<header><h1>Report Facility</h1></header>

<h3>Our Business Statistics</h3>
<p><b>Logo:</b> <img src="http://a1.twimg.com/profile_images/1523291719/cake-logo_normal.png" alt="Qake.info" /></p>
<p><b>Twitter Name:</b> TeamQake </p>
<p id="tweet_follower"><b>Total Followers:</b>  </p>
<p id="tweet_following"><b>Total Following:</b>  </p>
<p id="tweet_total"><b>Total Tweets:</b>  </p>

<h3>Follower Vs Following Graph</h3>
<p id="company_report_graph"></p>



<header><h1>Look Up Another Twitter Account?</h1></header>
<form method="GET">
<label>Account Name: <input type="text" id="twitter_search_query" /></label> <input type="button" value="search" id="account_lookup" />
</form>
<p id="account_lookup_result"></p>


<jsp:include page="../../includes/footer.jsp"></jsp:include>