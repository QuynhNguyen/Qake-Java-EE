<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Edit Category</h1></header>

${info}

<form:form commandName="category">
<p><label for="email">Category Category: </label> <form:input type="text" path="name" value="${category.name}"/></p>
									  <form:errors path="name" cssClass="error"/>
<p><label for="email">Description: </label><br/> <form:textarea type="text" path="description" value="${category.description}" /></p>
									  <form:errors path="description" cssClass="error"/>
									  
<p><input type="submit" value="Update Category!" /></p>

</form:form>


<jsp:include page="../../includes/footer.jsp"></jsp:include>