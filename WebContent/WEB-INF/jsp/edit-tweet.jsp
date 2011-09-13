<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Edit Tweet</h1></header>

${info}

<form:form commandName="tweet">
<p><label for="email">Tweet: </label><br/> <form:textarea type="text" path="content" value="${category.name}"/></p>
									  <form:errors path="content" cssClass="error"/>
<br/>
									  
<p><input type="submit" value="Update Tweet" /></p>

</form:form>


<jsp:include page="../../includes/footer.jsp"></jsp:include>