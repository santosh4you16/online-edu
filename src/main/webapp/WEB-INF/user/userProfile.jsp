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
                  <hr>
                   <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Upload Document:</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <form enctype="multipart/form-data" id="fileUploadForm">
						  <input type="file" id="myFile" name="filename">
						   <a class="btn btn-success" href="javascript:uploadFile()">Upload</a>
					  </form>
                    </div>
                  </div>
                  <hr>
                   <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Give Test:</h6>
                    </div>
                    <!-- Button trigger modal -->
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
						  Select Subject
						</button>
						                    
					 </div>
                </div>
              </div>
              <div class="row gutters-sm">
                <div class="col-sm-6 mb-3">
                  <div class="card h-100">
                    	<%-- <a class="btn btn-primary" href="${path}/createQuestion">Create Questions</a>
                    	<a class="btn btn-success" href="${path}/createTestType">Create Test Type</a> --%>
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
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
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