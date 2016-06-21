    $(document.getElementById('examForm')).ready(function() {
    
      $('#examDate').daterangepicker({
    	dateFormat: "dd-mm-yyyy",
        singleDatePicker: true,
        calender_style: "picker_2"
      }, function(start, end, label) {
       
       examDate = start.toISOString();
       $('#examForm').append("<input type='hidden' name='examDate' value='"+
               submitVal+"' />");

   
      });
      
      
      


});   