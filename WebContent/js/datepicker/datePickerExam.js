$(document.getElementById('examForm')).ready(function datePick() {
    	examDate = $('#examDate').datetimepicker({ format : 'yyyy-mm-dd hh:ii:00' });
    	console.log(examDate.toISOString());
    	$('#examForm').append("<input type='hidden' name='examDate' value='"+
                submitVal+"' />");
    	
    });