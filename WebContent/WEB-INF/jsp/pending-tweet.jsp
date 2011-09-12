<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Pending Tweets</h1></header>

${info}

<table border="0">
<tr>
<th>Tweet ID</th>
<th>Tweet Content</th>
<th>Author</th>
<th>Categories</th>
<th>Action</th>
</tr>
<c:forEach items="${pendingTweets}" var="tweet">
<tr>
<td>${tweet.id}</td>
<td>${tweet.content }</td>
<td>${tweet.author}</td>
<td><ol><c:forEach items="${tweet.categories}" var="category" ><li>${category.name}</li></c:forEach> </ol></td>
<td>
<p><a href="${tweet.id}/publish-tweet.html" onclick="return confirm('Are You Sure?')">Publish Tweet</a></p>
<p><a href="${tweet.id}/delete-tweet.html" onclick="return confirm('Are You Sure?')">Schedule Tweet</a></p>
<p><a href="${tweet.id}/delete-tweet.html" onclick="return confirm('Are You Sure?')">Edit Tweet</a></p>
<p><a href="${tweet.id}/delete-tweet.html" onclick="return confirm('Are You Sure?')">Delete Tweet</a></p>
</td>
</tr>
</c:forEach>
</table> 
<jsp:include page="../../includes/footer.jsp"></jsp:include>