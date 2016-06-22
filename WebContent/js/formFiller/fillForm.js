//Author: Amar & Chrisse


//for the edit button in the Manage Exams View for the professor
function myFunction(elem) {
	
	//Filling the Select Course description and the type into the form
	$("table#mainTable tr").each(function(i) {
		
		if($(this).attr("id") == elem.name.split("-")[0])
		{
			$("td", this).each(function( j ) {
		
			if($(this).attr("id") == "examCourseDescription"){
				$("#courseSelected").val($(this).text());
			}
			
			if($(this).attr("id") == "examDateId"){
				$("#courseSelected").val($(this).text());
			}
			
			if($(this).attr("id") == "examType"){
				$("#typeSelected").val($(this).text());
			}			
			
			
			});
	
		}
	
	});
	
	
	
	//Filling the examDate Description, date and room of the examdatemodel of an exam
	$("table#collapsedTable tr").each(function ( i ) {
				if($(this).attr("id") == elem.name)
					{
					$("td", this).each(function( j ) {
						
						if($(this).attr("id") == "examDateDescription")
							{
								$("#examDescription").val($(this).text())
							}
						if($(this).attr("id") == "examDateDate")
						{
							$("#examDate").val($(this).text())
						}
						if($(this).attr("id") == "examDateRoom")
						{
							$("#roomSelected").val($(this).text())
						}
						
						if($(this).attr("id") == "examDateId"){
							$("#examDateIdSelected").val($(this).text());
						}
						
					  });
					}
			  
			});	
	
	$(document.getElementById("submitExam").style.visibility = 'hidden');
	$(document.getElementById("updateExam").style.visibility = 'visible');
	$(document.getElementById("resetExam").style.visibility = 'visible');
	
	document.getElementById("courseSelected").disabled=true;
	document.getElementById("typeSelected").disabled=true;



}	


