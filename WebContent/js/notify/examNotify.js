function enroll() {	
	
	$('button[id^="enroll"]').prop('disabled', true);
	$('button[id^="signOut"]').prop('disabled', true);

	new PNotify({
        title: 'Enrolled for Exam',
        text: 'You successfully enrolled to an exam!',
        type: 'success'})

};




function signOut() {

	$('button[id^="signOut"]').prop('disabled', true);
	$('button[id^="enroll"]').prop('disabled', true);

new PNotify({
    title: 'Signed out!',
    text: 'You signed out of an exam!',
    type: 'info'})

};


function newExamSuccess() {
	
	$('button[id^="submitExam"]').prop('disabled', false);
	
	new PNotify({
        title: 'Exam created',
        text: 'You successfully created a new exam!',
        type: 'success'})
		
};


function newExamFailed() {
	
	$('button[id^="submitExam"]').prop('disabled', false);
	
	new PNotify({
        title: 'Error!',
        text: 'The exam you were trying to create, failed!',
        type: 'error'})	
	
};


function newExamUpdate() {
	
	$('button[id^="submitExam"]').prop('disabled', false);
	
	new PNotify({
        title: 'Exam updated!',
        text: 'The exam was successfully updated!',
        type: 'info'})	
	
};



$('form#examForm').submit(function(){
	
	$('button[id^="submitExam"]').prop('disabled', true);
	
});