<link rel="stylesheet" href="css/signup.css">

<!-- SignUP Form -->
<div class="signup-form">
    <form id="signupForm" method="post">
		<h2>Sign Up</h2>
		<p>Please fill in this form to create an account!</p>
		<hr>
        <div class="form-group">
			<div class="row">
				<div class="col"><input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" required="required"></div>
				<div class="col"><input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required="required"></div>
			</div>        	
        </div>
        <div class="form-group">
			<input type="text" class="form-control" id="username" name="username" placeholder="username" required="required">
        </div>
        <div class="form-group">
        	<input type="email" class="form-control" id="email" name="email" placeholder="Email" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required="required">
        </div>        
        <div class="form-group">
			<label class="form-check-label"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
		</div>
		<div class="form-group">
            <a type="submit" href="javascript:register()" class="btn btn-primary btn-lg">Sign Up</a>
        </div>
    </form>
	<div class="hint-text">Already have an account? <a href="/login.do">Login here</a></div>
</div>

<script rel="text/javascript">
$(document).ready(function(){
	
});
var registerData = {};
var register = function(){debugger
	registerData = $("#signupForm").serializeObject();
	$.ajax({
		type : "POST",
		url : "/register.do",
		contentType : "application/json",
		data:JSON.stringify(registerData),
		success : function(response) {
			alert("User saved Successfully!")
		}
	});
}


</script>
