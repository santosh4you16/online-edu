<jsp:include page="../navbar/usernavbar.jsp"></jsp:include>

<div class="container">
<form id="testTypeForm">
   <div class="form-group">
    <label for="testType">Enter Test Type:</label>
    <input type="text" class="form-control" id="testType" name="testType"  placeholder="Exam Type">
  </div> 
  <div class="form-group">
    <label for="examScore">Exam Score:</label>
    <input type="text" class="form-control" id="examScore" name="examScore"  placeholder="Total marks">
  </div>   
  <div class="form-group">
    <label for="passingScore">Passing Score:</label>
    <input type="text" class="form-control" id="passingScore" name="passingScore"  placeholder="Passing Marks">
  </div>     
  
  <div class="form-group">
  	<button onclick="saveTestType()" class="btn btn-success">Add Question</button>
  </div>
</form>
</div>

<script type="text/javascript">
var formData ={};

var saveTestType = function(){
	formData = $("#testTypeForm").serializeObject();
	$.ajax({
		type : "POST",
		url : "${path}/saveTestType.do",
		contentType : "application/json",
		data:JSON.stringify(formData),
		success : function(response) {
			if(response.isSuccess){
				alert("Saved Successfully!");
			}else{
				alert("Something went wrong!");
			}
		},
		error : function(res){
			alert("Something went wrong!");
			
		}
	});
};
</script>
