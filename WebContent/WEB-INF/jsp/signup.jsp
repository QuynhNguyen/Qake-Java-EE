<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Sign up</h1></header>

<form:form commandName="myuser">
<p><label for="email">Email: </label> <form:input type="email" path="email" /></p>
									  <form:errors path="email"/>
<p><label for="email">Full Name: </label> <form:input type="text" path="name" /></p>
										  <form:errors path="name"/>	
<p><label for="email">Password: </label> <form:input type="password" path="password" /></p>
										 <form:errors path="password"/>
<p><label for="email">Confirm: </label> <form:input type="password" path="confirm" /></p>
										<form:errors path="confirm"/>
<p><input type="submit" value="Sign up" /></p>
</form:form>

<jsp:include page="../../includes/footer.jsp"></jsp:include>