//Author: Amar & Chrisse


//for the edit button in the Manage Exams View for the professor
function myFunction(elem) {
	
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
					  });
					}
			  
			});	

}	


