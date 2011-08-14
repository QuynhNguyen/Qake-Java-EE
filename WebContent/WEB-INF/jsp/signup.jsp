
<jsp:include page="../../includes/header.jsp"></jsp:include>

<header><h1>Sign up</h1></header>

<form method="POST">
<p><label for="email">Email: </label> <input type="email" name="email" id="email" /></p>
<p><label for="email">Full Name: </label> <input type="text" name="name" id="name" /></p>
<p><label for="email">Password: </label> <input type="password" name="password" id="password" /></p>
<p><label for="email">Confirm: </label> <input type="password" name="confirm" id="confirm" /></p>
<p><input type="submit" value="Sign up" /></p>
</form>

<jsp:include page="../../includes/footer.jsp"></jsp:include>