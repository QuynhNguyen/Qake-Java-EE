
<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Login</h1></header>

<div class="error">${errors}</div>

<form action="login.html" method="POST" >

<p><label>Email: <input type="email" name="email" id="email" /></label></p>
<p><label>Password: <input type="password" name="password" id="password" /></label></p>
<p><input type="submit" value="Login" /></p>
</form>

<jsp:include page="../../includes/footer.jsp"></jsp:include>