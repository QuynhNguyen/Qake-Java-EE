<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Manage Category</h1></header>

${info}

<c:forEach items="${categories}" var="category">
	
		<h2>${category.name}</h2> <a href="edit-category/${category.id}.html">Edit</a> <a href="delete-category/${category.id}.html" onclick="return confirm('Are You Sure?')">Delete</a>
		<p class="info">Description: ${category.description}</p>
	
</c:forEach>

<jsp:include page="../../includes/footer.jsp"></jsp:include>