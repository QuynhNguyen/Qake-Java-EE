<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Sign up</h1></header>


<form:form commandName="myuser">

<p><label for="email">Email: </label> <form:input type="email" path="email" /></p>
									  <form:errors path="email" cssClass="error"/>
<p><label for="name">Full Name: </label> <form:input type="text" path="name" /></p>
										  <form:errors path="name" cssClass="error"/>	
<p><label for="password">Password: </label> <form:input type="password" path="password" /></p>
										 <form:errors path="password" cssClass="error"/>
<p><label for="confirm">Confirm: </label> <form:input type="password" path="confirm" /></p>
										<form:errors path="confirm" cssClass="error"/>
<p><input type="submit" value="Sign up" /></p>
</form:form>

<jsp:include page="../../includes/footer.jsp"></jsp:include>