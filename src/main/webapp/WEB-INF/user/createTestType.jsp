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
<hr>
	<!-- <table class="lsDataTabl" id="testType" name="testType" cellspacing="0" cellpadding="0" border="1">
		<thead>
			<tr>
				<th width="10%">sa</th>
				<th width="75%">ss</th>
				<th width="15%">ss</th>
			</tr>
		</thead>
	</table> -->
</div>

<script type="text/javascript">
var formData ={};
$(document).ready(function(){
	getTestType();
});
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

var getTestType = function(){
	$.ajax({
		type : "GET",
		url : "${path}/getTestType.do",
		contentType : "application/json",
		success : function(response) {
			if(response.isSuccess){
				renderTestType(response.data)
			}else{
				alert("Something went wrong!");
			}
		},
		error : function(res){
			alert("Something went wrong!");
			
		}
	});	
};

/* var renderTestType = function(responseJson)
{
	noteTab = $JQ("#testType").dataTable( {
		"bAutoWidth": false			
		,"bPaginate": true
		, "sDom": '<"top"<"clear">>rt<"bottom"lip<"clear">>'
		, "bFilter": true
		, "bServerSide": false
		, "aaSorting": []
		, "aaData":responseJson.data
		, "aoColumns": [
			  { mData: 'creationDateS', sWidth: "51px;", "iDataSort":8 }
			, { mData: 'noteText', sWidth: "121px;" }
			, { mData: 'createdBy', sWidth: "116px;" }
			]
		} );
	$JQ("#queryInternalNote").keyup( function () {
		noteTab.fnFilter($JQ("#queryInternalNote")[0].value);
	 });
}; */

</script>
