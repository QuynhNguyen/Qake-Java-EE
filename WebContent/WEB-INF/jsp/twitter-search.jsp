
<jsp:include page="../../includes/header.jsp"></jsp:include>
<script>
	window.addEventListener('load', function(){
			var output = "";
			var twitter_search = document.getElementById("submit_button");
			
			twitter_search.addEventListener('click', function(){
				var twitter_search_query = document.getElementById("twitter_search_query").value;
				if(twitter_search_query.substring(0, 1) == "#")
					twitter_search_query = "%23" + twitter_search_query.substring(1,twitter_search_query.length);
				if(document.getElementById("twitter_search_query").value == ""){
					alert("Please insert a search query");
				}else{
					$("#search_result").empty();
					
				$.ajax({
				url: "http://search.twitter.com/search.json",
				cache: false,
				type: "GET",
				async: false,
				data: "result_type=mixed&lang=en&q=" + twitter_search_query,
				dataType: "jsonp",
				success: function(data, textStatus, jqXHR){
					for(var temp in data["results"]){
						output += '<section class="search_result_list"><img src="'+data["results"][temp]["profile_image_url"]+'" alt="'+data["results"][temp]["from_user"]+'"/><b>'+data["results"][temp]["from_user"]+'</b> <br/>'+data["results"][temp]["text"]+'</section>';
						$("#search_result").html(output);
					}
					$("#search_box").html('<a href="?pagename=twitter_search">Search Again? </a>');
				},
				statusCode: {
				   404: function(){alert("Page not found!");}
				},
				error: function(jqXHR, textStatus, errorThrow){alert ("Status Code: " + textStatus + " -- " + errorThrow);}
				  
				});
				
				
				
				}
				
			}, false);
	}, false);

</script>
<div id="search_box">
<label>Search Query: <input type="text" id="twitter_search_query" /></label>
<input type="button" id="submit_button" value="Search">
</div>

<hr/>

<section>
<header><h1>Search Result</h1></header>
<div id="search_result">

</div>
</section>

<jsp:include page="../../includes/footer.jsp"></jsp:include>