$(document.getElementById('examForm')).ready(function datePick() {
    	examDate = $('#examDate').datetimepicker({ format : 'dd.mm.yyyy hh:ii' });
    	console.log(examDate.toISOString());
    	$('#examForm').append("<input type='hidden' name='examDate' value='"+
                submitVal+"' />");
    	
    });