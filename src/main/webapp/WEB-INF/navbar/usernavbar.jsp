<jsp:include page="../head.jsp"></jsp:include>

<%@ page import="org.springframework.security.core.context.SecurityContext" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="com.onlineedu.model.UserModel" %>
<%@ page import="com.onlineedu.model.RoleModel" %>
<%@page import="com.onlineedu.service.CustomUserDetails"%>
<%@ page import="java.util.Set" %>


<%
SecurityContext ctx=(SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
Authentication auth=ctx.getAuthentication();
CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal();
UserModel user = userDetails.getUserModel();
%>
<div class="marBtm60">
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
  <div class="container">
    <a class="navbar-brand" href="${path}/userProfile">Online-Edu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <%
        	Set<RoleModel> roleObject = user.getRoles();
        	for( RoleModel role: roleObject ){
        	   // System.out.println(role.getId());
        	    if( role.getId() != 3 ) {
        %>
	        <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Tools
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		          <a class="dropdown-item" href="${path}/createQuestion">Add Question</a>
		          <a class="dropdown-item" href="${path}/createTestType">Create Test Type</a>
		        </div>
	       </li>
       <% } } %>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="${path}/logout">Log Out</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
</div>
