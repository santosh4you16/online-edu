<jsp:include page="../navbar/usernavbar.jsp"></jsp:include>

<div class="container">
<form id="questionForm">
	<div class="form-group">
	    <label for="testTypeId">Select Test Type:</label>
		<select id="testTypeId" name="testTypeId" class="form-control">
		  <option value=0>--SELECT TEST TYPE--</option>
		</select>
	</div>
	<div class="form-group">
	  <label for="mark">Enter Mark:</label>
	  <input type="number" class="form-control" id="mark" name="mark" placeholder="Enter Mark for question">
	</div>
	<div class="form-group">
	  <label for="question">Enter Question:</label>
	  <textarea class="form-control rounded-10" id="question"  name="question" rows="6"></textarea>
	</div>
   <div class="form-group">
    <label for="optionA">Option A:</label>
    <input type="text" class="form-control" id="optionA" name="optionA" placeholder="OPTION A">
  </div> 
   <div class="form-group">
    <label for="optionB">Option B:</label>
    <input type="text" class="form-control" id="optionB" name="optionB" placeholder="OPTION B">
  </div>   
  <div class="form-group">
    <label for="optionC">Option C:</label>
    <input type="text" class="form-control" id="optionC" name="optionC" placeholder="OPTION C">
  </div>   
  <div class="form-group">
    <label for="optionD">Option D:</label>
    <input type="text" class="form-control" id="optionD" name="optionD" placeholder="OPTION D">
  </div> 
  <!-- Correct Answer  -->
  <div class="form-group">
    <label>Answer:</label>
  </div> 
  <div class="form-check form-check-inline">
   <input class="form-check-input" type="radio" name="answer" id="answer" value="optionA">
   <label class="form-check-label" for="answer">A</label>
  </div>
  <div class="form-check form-check-inline">
   <input class="form-check-input" type="radio" name="answer" id="answer" value="optionB">
   <label class="form-check-label" for="answer">B</label>
  </div>
  <div class="form-check form-check-inline">
   <input class="form-check-input" type="radio" name="answer" id="answer" value="optionC">
   <label class="form-check-label" for="answer">C</label>
  </div>
  <div class="form-check form-check-inline">
   <input class="form-check-input" type="radio" name="answer" id="answer" value="optionD">
   <label class="form-check-label" for="answer">D</label>
  </div>
  <div class="form-group">
  	<button onclick="saveQuestion()" class="btn btn-primary">Add Question</button>
  </div>
</form>
</div>

<script type="text/javascript">
var formData = {};
$(document).ready(function(){
	getTestType();
});

var getTestType = function(){
	$.ajax({
		type : "GET",
		url : "${path}/getTestType.do",
		contentType : "application/json",
		success : function(response) {
			if(response.isSuccess){
				$.each(response.data,function(ind, val){
					$("#testTypeId").append($("<option></option>").attr("value",val.id).text(val.testType));						
				});
			}else{
				alert("Something went wrong!");
			}
		},
		error : function(res){
			alert("Something went wrong!");
			
		}
	});	
};

var saveQuestion = function(){debugger
	formData = $("#questionForm").serializeObject();
	$.ajax({
		type : "POST",
		url : "${path}/saveQuestion.do",
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
