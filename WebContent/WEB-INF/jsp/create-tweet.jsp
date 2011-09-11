<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Create Tweet</h1></header>

${info}
<form:form commandName="tweet">
<p><label for="content">Tweet Content: <br/></label> <form:textarea type="text" path="content" /></p>
									  <form:errors path="content"/>
									  
									  
<c:forEach items="${categories}" var="category">
<p><input type="checkbox" name="categories_checkboxes" value="${category.id}" /> ${category.name}</p>
</c:forEach>

<p><input type="submit" value="Create Tweet" /></p>
</form:form>



<jsp:include page="../../includes/footer.jsp"></jsp:include>