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
	
	new PNotify({
        title: 'Exam created',
        text: 'You successfully created a new exam!',
        type: 'success'})
		
};


function newExamFailed() {
	
	new PNotify({
        title: 'Error!',
        text: 'The exam you were trying to create, failed!',
        type: 'error'})	
	
};