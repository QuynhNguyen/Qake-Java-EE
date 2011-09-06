<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Create Category</h1></header>

<form:form commandName="category">
<p><label for="email">Category Category: </label> <form:input type="text" path="name" /></p>
									  <form:errors path="name" cssClass="error"/>
<p><label for="email">Description: </label><br/> <form:textarea type="text" path="description" /></p>
									  <form:errors path="description" cssClass="error"/>
									  
<input type="submit" value="Create Category!" />

</form:form>

<jsp:include page="../../includes/footer.jsp"></jsp:include>