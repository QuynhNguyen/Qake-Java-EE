
<jsp:include page="../../includes/header.jsp"></jsp:include>



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