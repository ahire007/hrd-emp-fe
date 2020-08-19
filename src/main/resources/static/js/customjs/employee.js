
function isExist(){
		var empId= $("#empId").val();
		console.log(empId);
		$.ajax({
	    url: '/employee-exist/'+empId,
	    type: 'GET',
	    success: function(data)
	    {
	    	if(data==true){
	    		console.log(data);
	    		$('#empid').css('color', 'red');
	    		$("#empid").text("Employee Id is already exist");
	    		}else{
	    		$('#empid').css('color', 'green');
	    		$("#empid").text("Employee Id is available");
	    		}
	    		
	    },
	     error: function(errorThrown) 
	     {
	    	 console.log(errorThrown);
	     }          
	    });
	
}







