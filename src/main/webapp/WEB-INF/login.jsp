<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" href="css/signup.css">

<!-- SignUP Form -->
<div class="signup-form">
    <form id="lognForm" method="post">
		<h2>Sign In</h2>
		<p>Enter Your Creds:</p>
		<hr>
        <div class="form-group">
			<input type="text" class="form-control" name="username" placeholder="username" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="Password" required="required">
        </div>
		<div class="form-group">
            <button type="submit" class="btn btn-primary btn-lg">Log In</button>
        </div>
    </form>
</div>

<script type="text/javascript">
var loginData = {};
var register = function(){debugger
	loginData = $("#loginForm").serializeObject();
	$.ajax({
		type : "POST",
		url : "/login",
		contentType : "application/json",
		data:JSON.stringify(registerData),
		success : function(response) {
		}
	});
}


</script>