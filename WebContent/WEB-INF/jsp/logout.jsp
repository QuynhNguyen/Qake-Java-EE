<script>

deleteCookie("qake_email");
deleteCookie("qake_password");

function deleteCookie(name){
	document.cookie = name + '=; expires=Thu, 01-Jan-70 00:00:01 GMT;';
}

window.location = "index.html";

</script>