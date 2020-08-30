<%@page import="com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Set"%>
<%@ page import="org.springframework.security.core.context.SecurityContext" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="com.onlineedu.model.UserModel" %>
<%@ page import="com.onlineedu.model.RoleModel" %>
<%@page import="com.onlineedu.service.CustomUserDetails"%>

<jsp:include page="../navbar/usernavbar.jsp"></jsp:include>

<%
SecurityContext ctx=(SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
Authentication auth=ctx.getAuthentication();
CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal();
UserModel user = userDetails.getUserModel();
String userFullName = null;
if( user != null ){
  userFullName = user.getFirstName()+" "+user.getLastName();
}
%>
<div class="bgbody">
<div class="container">
    <div class="main-body">
    
          <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                    <div class="mt-3">
                      <h4><%= userFullName  %></h4>
                      <p class="text-secondary mb-1">
                      <% Set<RoleModel> roles = user.getRoles(); 
                      	 for( RoleModel role : roles){
                      	     out.println(role.getRole());
                      	 }
                      %><br>${path}
                      </p>
                      <p class="text-muted font-size-sm">India</p>
                      <button class="btn btn-primary">Follow</button>
                      <button class="btn btn-outline-primary">Message</button>
                      <form enctype="multipart/form-data" id="fileUploadForm">
						  <a class="btn btn-danger" href="javascript:uploadFile()">Upload</a>
						  <input type="file" id="myFile" name="filename">
					  </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-8">
              <div class="card mb-3">
                <div class="card-body">
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Full Name</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <%= userFullName %>
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Email</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <%= user.getEmail() %>
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Phone</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      8930575966
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Mobile</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      9911126822
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Address</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <%= user.getUsername() %>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row gutters-sm">
                <div class="col-sm-6 mb-3">
                  <div class="card h-100">
                    	<a class="btn btn-primary mb-2" href="${path}/createQuestion"></a><br>
                    	<a class="btn btn-success mb-2" href="${path}/createTestType"></a>
                   </div>
                  </div>
                <div class="col-sm-6 mb-3">
                  <div class="card h-100">
                  <ul id="fileList" >
                  
                  </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    </div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	getUserFiles();
});
var uploadFile = function(){
	 var form = $('#fileUploadForm')[0];
	 var data = new FormData(form);
	 $.ajax({
	        type: "POST",
	        enctype: 'multipart/form-data',
	        url: "${path}/uploadFile.do",
	        data: data,
	        processData: false, //prevent jQuery from automatically transforming the data into a query string
	        contentType: false,
	        cache: false,
	        success: function (data) {
				if(data.isSuccess){
					alert("uploaded successfully");
					location.reload();
				}else{
					alert(data.error);
				}
	        },
	        error: function (e) {

	         alert(e.error);

	        }
	    });
};

var getUserFiles = function(){
	 $.ajax({
	        type: "GET",
	        url: "${path}/getUserFiles.do",
	        success: function (data) {
				if(data.isSuccess){
					$.each(data.data,function(ind, val){
						$("#fileList").append($("<li></li>").html("<a href='#'>"+val+"</a>"));						
					});
				}else{
					alert(data.error);
				}
	        },
	        error: function (e) {

	         alert(e.error);

	        }
	    });
};
</script>