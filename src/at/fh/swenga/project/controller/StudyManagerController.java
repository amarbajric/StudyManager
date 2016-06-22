package at.fh.swenga.project.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import at.fh.swenga.project.dao.CourseRepository;
import at.fh.swenga.project.dao.DegreeProgramRepository;
import at.fh.swenga.project.dao.ExamApplicationRepository;
import at.fh.swenga.project.dao.ExamDateRepository;
import at.fh.swenga.project.dao.ExamRepository;
import at.fh.swenga.project.dao.ProfessorRepository;
import at.fh.swenga.project.dao.RoomRepository;
import at.fh.swenga.project.dao.StudentRepository;
import at.fh.swenga.project.dao.UserRepository;
import at.fh.swenga.project.dao.YearRepository;
import at.fh.swenga.project.model.CourseModel;
import at.fh.swenga.project.model.ExamApplicationModel;
import at.fh.swenga.project.model.ExamDateModel;
import at.fh.swenga.project.model.ExamModel;
import at.fh.swenga.project.model.ProfessorModel;
import at.fh.swenga.project.model.RoomModel;
import at.fh.swenga.project.model.StudentModel;
import at.fh.swenga.project.queryModels.Q_ExamDateModelWithRoom;
import at.fh.swenga.project.queryModels.Q_ExamModelWithDates;
import at.fh.swenga.project.queryModels.Q_GradedExamWithOutcome;
import at.fh.swenga.project.queryModels.Q_ProfessorExam;
import at.fh.swenga.project.queryModels.Q_studentExam;
import at.fh.swenga.project.util.GradeForm;
import at.fh.swenga.project.util.MapSorter;


@Controller
@SessionAttributes({"studentData","professorData"})
public class StudyManagerController {

	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	ProfessorRepository professorRepo;

	@Autowired
	YearRepository yearRepo;

	@Autowired
	DegreeProgramRepository degreeProgramRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ExamApplicationRepository examApplicationRepo;
	
	@Autowired
	ExamDateRepository examDateRepo;

	@Autowired
	ExamRepository examRepo;
	
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	RoomRepository roomRepo;

	/********************************************INDEX VIEW***********************************************************/
	/**
	 * The default requestMapper only checks if the logged in user is a student, professor or admin.
	 * Then it checks the given role of the logged in user and forwards the browser to the specific view
	 **/
	@RequestMapping(value = {"/"})
	public String showIndex(Model model) {
		
		// Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        String mailOfUser = auth.getName();
        String targetUrl = "";
        

         /***************************PROFESSOR INDEX PAGE*******************************************/
        if(role.toLowerCase().contains("professor")) {
        	ProfessorModel profData = professorRepo.findByMail(mailOfUser);
        	
        	//getting the amount of exams held by a professor
        	int numberOfExamsHeld = examDateRepo.countByProfessorId(profData.getId());
        	       	
        	//getting the total amount of professors
        	int numberOfAllProfessors = professorRepo.countAll();
        	
        	/*************************GET THE AMOUNT OF STUDENTS***************************/
        	//get the total amount of students
        	int numberOfAllStudents = studentRepo.countAll();
        	//get the amount of distinct students who are or were in my courses
        	int numberOfMyStudents = studentRepo.countByCoursesOfProfessor(profData.getId());
        	
        	
        	//Get the average grade on all exams of a professor
        	Double professorAverageExamGrade = professorRepo.findAverageGradeOfAllExams(profData.getId());
        	
        	/*************************GET UPCOMING EXAMS OF THE PROFESSOR***************************/
        	//getting the data of all the upcoming exams of a professor
        	List<Object[]> professorExamsData = examDateRepo.findUpcomingProfExams(profData.getId());
        	
        	//Adding the Data of the upcoming exams into a List of Q_ProfessorExam
        	List<Q_ProfessorExam> professorExams = new ArrayList<Q_ProfessorExam>();
           	for (int i = 0; i < professorExamsData.size(); i++) {
        		Object[] arr = professorExamsData.get(i);
        		Date date = (Date)arr[1];
        		Q_ProfessorExam exam = new Q_ProfessorExam(Integer.parseInt(arr[0].toString()) ,date,arr[2].toString(),arr[3].toString(),arr[4].toString(), arr[5].toString(), Integer.parseInt(arr[6].toString()));
        		professorExams.add(exam);
        		}

           	
           	/*************************SETTING THE MODELS***************************/
           	model.addAttribute("numberOfExamsHeld",numberOfExamsHeld);
           	model.addAttribute("professorAverageExamGrade",professorAverageExamGrade);
           	model.addAttribute("numberOfAllProfessors",numberOfAllProfessors);
           	model.addAttribute("numberOfAllStudents",numberOfAllStudents);
           	model.addAttribute("numberOfMyStudents",numberOfMyStudents);
        	model.addAttribute("professorExams",professorExams);
        	model.addAttribute("professorData",profData);
            targetUrl = "professor/index";
            
            
            
        /***************************STUDENT INDEX PAGE*******************************************/
        } else if(role.toLowerCase().contains("student")) {
        	//find all the data of the specific Student who logged in.
        	StudentModel studentData = studentRepo.findByMail(mailOfUser);
        	
        	/*************************GET THE GRADED EXAMS***************************/
        	//get all graded exams
        	List<ExamApplicationModel> gradedExams = examApplicationRepo.findByStudentAndGradeIsNotNullOrderByExamDateDateDesc(studentData);
        	
        	//get the amount of passed exams
        	int amountOfPassedExams = gradedExams.stream().filter(e -> e.getGrade() < 5).collect(Collectors.toList()).size();
        	int amountOfGradedExams = gradedExams.size();
        	double percentageOfPassedExams = (double)amountOfPassedExams / ((double)amountOfGradedExams) * 100;
        	
        	
        	/*************************GET THE AMOUNT OF STUDENTS***************************/
        	//get the total amount of students
        	int numberOfAllStudents = studentRepo.countAll();
        	//get all students who are studying with the specific student
        	int numberOfStudentColleagues = studentRepo.countByYearYear(studentData.getYear().getYear());
        	
        	/*************************GET THE AMOUNT OF EVERY GRADE FOR THE STUDENT***************************/
        	//get the amount for every grade data
        	List<Object[]> numberOfGradesByGrade = examApplicationRepo.findNumberOfGradesByGrade(studentData.getId());
        	//get the data into a Map with the grade as the key and the amount as the value
        	Map<Integer,Integer> gradesMap = new HashMap<Integer,Integer>();
        	for (int i = 0; i < numberOfGradesByGrade.size(); i++) {
        		Object[] arr = numberOfGradesByGrade.get(i);
        		gradesMap.put(Integer.parseInt(arr[0].toString()), Integer.parseInt(arr[1].toString()));
        		}
        	//set the amount of grades which don't exist to zero
        	for (int i = 1; i < 6; i++) {
        		if (!gradesMap.containsKey(i)){
        			gradesMap.put(i,0);
        		}
        	}
        	//setting the amount of every grade to a list
        	List<Integer> grades = new ArrayList<Integer>(gradesMap.values());
        	
        	/*************************GET THE AVERAGE GRADE FROM ALL STUDENTS***************************/
        	//get the average grade data from all students
        	List<Object[]> averageGrades = examApplicationRepo.findAverageGrades();
        	
        	//get average grades into map
        	Map<Integer,Double> averageMap = new HashMap<Integer,Double>();
        	for (int i = 0; i < averageGrades.size(); i++) {
        		Object[] arr = averageGrades.get(i);
        		averageMap.put(Integer.parseInt(arr[0].toString()), Double.parseDouble(arr[1].toString()));
        		}
        	
        	//sort the map
        	Map<Integer, Double> averageMapSorted = MapSorter.sortByValue(averageMap);
        	
        	// get index of the map
        	List<Integer> indexes = new ArrayList<Integer>(averageMapSorted.keySet());
        	
        	int rank = indexes.indexOf(studentData.getId())+1; // +1 because list starts at 0
        	double average = averageMapSorted.get(studentData.getId());
        	
        	/*************************GET 5 NEXT UPCOMING EXAMS***************************/
        	//getting the 5 next upcoming exams data of a student
        	List<Object[]> upcomingExamsData = examDateRepo.findUpcomingExams(studentData.getId());
        	
        	//Adding the Data of the 5 upcoming exams into a List of Q_studentExams
        	List<Q_studentExam> upcomingStudentExams = new ArrayList<Q_studentExam>();
           	for (int i = 0; i < upcomingExamsData.size(); i++) {
        		Object[] arr = upcomingExamsData.get(i);
        		Date date = (Date)arr[4];
        		Q_studentExam exam = new Q_studentExam(Integer.parseInt(arr[0].toString()),arr[1].toString(),arr[2].toString(),arr[3].toString(),date,Double.parseDouble(arr[5].toString()),arr[6].toString());
        		upcomingStudentExams.add(exam);
        		}

           	/*************************SETTING THE MODELS***************************/
        	model.addAttribute("average",average);
        	model.addAttribute("rank",rank);
        	model.addAttribute("numberOfStudentColleagues",numberOfStudentColleagues);
        	model.addAttribute("numberOfAllStudents",numberOfAllStudents);
        	model.addAttribute("studentData",studentData);
        	model.addAttribute("gradedExams",gradedExams);
        	model.addAttribute("percentageOfPassedExams",percentageOfPassedExams);
        	model.addAttribute("grades",grades);
        	model.addAttribute("upcomingStudentExams", upcomingStudentExams);

            
        	targetUrl = "student/index";
        }
      
		//no forward because then it won't search in views folder!
		return targetUrl;
	}
	
	
	
/********************************************GRADES STUDENT PAGE***********************************************************/
	@RequestMapping(value = "/grades", method = RequestMethod.GET)
	public String showGrades(Model model,@ModelAttribute("studentData") StudentModel studentData) {
    	
    	//get all graded exams
		List<ExamApplicationModel> gradedExams = examApplicationRepo.findByStudentAndGradeIsNotNullOrderByExamDateDateDesc(studentData);
		
		
		/*************************GRADED EXAMS WITH OUTCOME***************************/
		//Getting the Data
    	List<Object[]> gradedExamsWithOutcomeData = examApplicationRepo.findByStudentAndGradeIsNotNullWithOutcomeOrderByExamDateDateDesc(studentData);
    	
    	//Adding the Data of the graded exams into a List of Q_GradedExamWithOutcome
    	List<Q_GradedExamWithOutcome> gradedExamsWithOutcome = new ArrayList<Q_GradedExamWithOutcome>();
       	for (int i = 0; i < gradedExamsWithOutcomeData.size(); i++) {
    		Object[] arr = gradedExamsWithOutcomeData.get(i);
    		
    		Integer id = Integer.parseInt(arr[0].toString());
    		String courseName = arr[1].toString();
    		String examType = arr[2].toString();
    		Integer attempt = Integer.parseInt(arr[3].toString());
    		Date date = (Date)arr[4];
    		Integer grade = Integer.parseInt(arr[5].toString());
    		
    		List<Integer> gradesOverview = new ArrayList<Integer>(Arrays.asList(Integer.parseInt(arr[6].toString()),Integer.parseInt(arr[7].toString()),Integer.parseInt(arr[8].toString()),Integer.parseInt(arr[9].toString()),Integer.parseInt(arr[10].toString())));
    		
    		Double averageGrade = Double.parseDouble(arr[11].toString());
    		
    		Q_GradedExamWithOutcome gradedExamWithOutcome = new Q_GradedExamWithOutcome(id, courseName, examType, attempt, date, grade, gradesOverview, averageGrade);
    		gradedExamsWithOutcome.add(gradedExamWithOutcome);
    		}
    	
    	
    	/*************************SETTING THE MODELS***************************/
       	model.addAttribute("gradedExamsWithOutcome",gradedExamsWithOutcome);
    	model.addAttribute("studentData",studentData);
    	model.addAttribute("gradedExams",gradedExams);
		return "student/grades";
	}
	
	
	
/********************************************EXAM STUDENT PAGE***********************************************************/
	@RequestMapping(value = "/exams", method = RequestMethod.GET)
	public String showExams(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String mailOfUser = auth.getName();
    	//find all the data of the specific Student who logged in.
    	StudentModel studentData = studentRepo.findByMail(mailOfUser);

    	/*************************GET ALL FUTURE EXAMS***************************/
    	//getting all the future exams of a student
    	List<Object[]> futureExamsData = studentRepo.findFutureExams(studentData.getId());
    	
    	//Adding the Data of the future exams into a List of Q_studentExams
    	List<Q_studentExam> futureStudentExams = new ArrayList<Q_studentExam>();
       	for (int i = 0; i < futureExamsData.size(); i++) {
    		Object[] arr = futureExamsData.get(i);
    		Date date = (Date)arr[4];
    		Q_studentExam exam = new Q_studentExam(Integer.parseInt(arr[0].toString()),arr[1].toString(),arr[2].toString(),arr[3].toString(),date,Double.parseDouble(arr[5].toString()),arr[6].toString());
    		exam.setEnrolled(Integer.parseInt(arr[7].toString()));
    		futureStudentExams.add(exam);
    		}
       	
       	
    	model.addAttribute("futureStudentExams",futureStudentExams);
		return "student/exams";
	}
	


/********************************************MANAGE EXAMS PAGE (STUDENT)***********************************************************/
	@RequestMapping(value="/manageExam", method=RequestMethod.GET)
	public String manageExam(Model model,@RequestParam String action, @RequestParam int id)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String mailOfUser = auth.getName();
        //find all the data of the specific Student who logged in.
        StudentModel studentData = studentRepo.findByMail(mailOfUser);
        
        /*************************ENROLLING AND SIGNING OUT OF EXAMS***************************/
		if(action.equals("enroll"))
		{
			
		Integer newAttemptOfExam = examApplicationRepo.attemptOfExam(studentData.getId(),id) + 1;
		ExamDateModel examDate = examDateRepo.findById(id);
		ExamApplicationModel examToEnroll = new ExamApplicationModel(newAttemptOfExam, studentData, examDate);
		examApplicationRepo.save(examToEnroll);
		

		}
		else if(action.equals("signOut"))
		{			
			
		examApplicationRepo.removeByStudentIdAndExamDateId(studentData.getId(), id);
				
		}			
		
		
		return "forward:/exams";
	}
	
	
	
/********************************************PROFILE***********************************************************/
	@RequestMapping(value = "/profile")
	public String showProfile(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        String mailOfUser = auth.getName();
        
        String UrlAsString = "";
        boolean isStudent;
        
        if(role.toLowerCase().contains("professor"))
        {
        	ProfessorModel profileData = professorRepo.findByMail(mailOfUser);
        	UrlAsString = "profile";
        	isStudent = false;
        	model.addAttribute("profileData",profileData);
        	model.addAttribute("isStudent",isStudent);
        	return UrlAsString;
        }
        else if(role.toLowerCase().contains("student"))
        {
        	StudentModel profileData = studentRepo.findByMail(mailOfUser);
        	UrlAsString = "profile";
        	isStudent = true;
        	model.addAttribute("profileData",profileData);
        	model.addAttribute("isStudent",isStudent);
        	return UrlAsString;
        }
        
        
		return UrlAsString;
	}
	
	
/********************************************LOGIN***********************************************************/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}

	
/********************************************ADD EXAM PAGE***********************************************************/
	@RequestMapping(value = "/manageExams", method = RequestMethod.GET)
	public String manageExam(Model model,@ModelAttribute("professorData")ProfessorModel professorData) {
		 
		/*************************GETTING ALL EXAMS OF THE PROFESSOR***************************/
		//Getting All Exams of the professor with its according dates ordered by date asc
       	List<ExamModel> exams = examRepo.findByCourseProfessor(professorData.getId());
       	
       	//create an arraylist in which all exams with the needed data will get stored
       	ArrayList<Q_ExamModelWithDates> examList = new ArrayList();
       	
       	//store the arraylist with the right data
       	for (ExamModel exam : exams){
       		
       		//Getting all Dates for the exam in an unordered set
       		Set<ExamDateModel> examDates = exam.getExamDates();
       		
       		//create an arraylist in which all the dates of the set will get stored
       		ArrayList<Q_ExamDateModelWithRoom> examDateList = new ArrayList();
       		
       		//store the examDates into the arraylist
       		for (ExamDateModel examDate : examDates){
       			Integer id = examDate.getId();
       			Date date = examDate.getDate();
       			String description = examDate.getDescription();
       			String room = examDate.getRoom().getDescription();
       			
       			Q_ExamDateModelWithRoom newExamDate = new Q_ExamDateModelWithRoom(id,date,description,room);
       			examDateList.add(newExamDate);
       		}
       		
       		//sort the list of examDates by date asc
       		Collections.sort(examDateList);
       		
       		//Get the missing data for the exam
       		Integer id = exam.getId();
       		String courseDescription = exam.getCourse().getDescription();
       		String examDescription = exam.getType();
       		
       		//save the exam with its dates into the arraylist
       		Q_ExamModelWithDates newExam = new Q_ExamModelWithDates(id,courseDescription,examDescription,examDateList);
       		examList.add(newExam);
       	}

       	
       	/*************************GETTING ALL ROOMS***************************/
       	//Getting all rooms
       	List<RoomModel> rooms = roomRepo.findAll();
       	
       	
       	/*************************SETTING THE MODELS***************************/
       	model.addAttribute("examList",examList);
    	model.addAttribute("rooms",rooms);
    	model.addAttribute("professorData",professorData);

		return "professor/manageExams";
	}
	
	
/********************************************ADDING EXAM***********************************************************/	
	@RequestMapping(value="/addExamModel", method=RequestMethod.GET)
	public String modelAdd(Model model,@RequestParam String courseSelected, @RequestParam String typeSelected, @RequestParam String examDescription, @RequestParam String examDate, @RequestParam String roomSelected, @RequestParam(required=false) Integer examDateIdSelected)
	{
		
		CourseModel course = courseRepo.findByAcronym(courseSelected);
		ExamModel existsExam = examRepo.findByDescriptionAndTypeAndCourse(courseSelected,typeSelected, course);		
		ExamModel exam;
		String status;		
				
		DateFormat date = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		Date formatDate = new Date();
		try {
			formatDate = date.parse(examDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(existsExam == null)//if no Exam of this type exists...
		{
			exam = new ExamModel(courseSelected,typeSelected, course);		
			examRepo.save(exam);
			status = "newExamModel";
			
		}
		else{
			
				exam = existsExam;
			/***********************CHECK, wheter for a new Exam or existing Exam, if one of the dates exist, otherwise create a date(attempt) *****/
			// Check if examDate already exists
			if(examDateRepo.findByExamAndDate(exam.getId(), formatDate) < 1)//ExamDateModel doesn't exist
			{
				
				RoomModel room = roomRepo.findByDescription(roomSelected);
				
				ExamDateModel newExamDate = new ExamDateModel(formatDate, examDescription, room, exam);
				examDateRepo.save(newExamDate);
				status = "newExamDateModel";
			}		
			
			else if(examDateIdSelected != null && examDateRepo.findById(examDateIdSelected) != null){//exists -> Update ExamDateModel
				
				RoomModel room = roomRepo.findByDescription(roomSelected);
				ExamDateModel updatingModel = examDateRepo.findById(examDateIdSelected);
				updatingModel.setDate(formatDate);
				updatingModel.setDescription(examDescription);
				updatingModel.setRoom(room);
				
				//update ExamDateModel
				examDateRepo.save(updatingModel);
				
				status = "updatedExamDateModel";
			}
			
			else{//ExamModel and ExamDateModel already exists
				
				status = "alreadyExists";
				
			}
		
		}
		
		model.addAttribute("status",status);

		return "forward:/manageExams";
	}
	
	/******************************************** SHOW EXAMS TO GRADE VIEW ***********************************************************/
	
	@RequestMapping(value = "/writtenExams", method = RequestMethod.GET)
	public String writtenExam(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String mailOfUser = auth.getName();
	        ProfessorModel professorData = professorRepo.findByMail(mailOfUser);
	        
		//getting all the exams to grade of a professor
    	List<Object[]> professorExamsData = examDateRepo.findProfExamsToGrade(professorData.getId());
    	
    	//Adding the Data of the exams into a List of Q_ProfessorExam
    	List<Q_ProfessorExam> professorExams = new ArrayList<Q_ProfessorExam>();
       	for (int i = 0; i < professorExamsData.size(); i++) {
    		Object[] arr = professorExamsData.get(i);
    		Date date = (Date)arr[1];
    		Q_ProfessorExam exam = new Q_ProfessorExam(Integer.parseInt(arr[0].toString()) ,date,arr[2].toString(),arr[3].toString(),arr[4].toString(), arr[5].toString(), Integer.parseInt(arr[6].toString()));
    		professorExams.add(exam);
    		}
       	
       	model.addAttribute("professorExams", professorExams);
    	model.addAttribute("professorData",professorData);

		return "professor/writtenExams";
	}
	
	/******************************************** GRADE EXAM - GET Method ***********************************************************/
	
	@RequestMapping(value="/gradeExam", method=RequestMethod.GET)
	public String gradeExam(Model model,@RequestParam int examDateId, @RequestParam String course, @RequestParam String type, @RequestParam String dateNumber,@RequestParam String date)
	{
		List<ExamApplicationModel> applicantsList = examApplicationRepo.findByExamDate_id(examDateId);
		
		/*************************PARSING THE DATE***************************/
		date = date.substring(0,date.length()-2);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date formatDate = new Date();
		try {
			formatDate = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GradeForm gradeForm = new GradeForm();
		gradeForm.setApplicants(applicantsList);
		
		/*************************SETTING THE MODELS***************************/
		model.addAttribute("course", course);
		model.addAttribute("type", type);
		model.addAttribute("dateNumber", dateNumber);
		model.addAttribute("date", formatDate);
		model.addAttribute("gradeForm", gradeForm);
		
		return "professor/gradeExam";
	}
	
	
	
	/******************************************** GRADE EXAM - POST Method ***********************************************************/
	@RequestMapping(value="/gradeExam", method=RequestMethod.POST)
	public String gradeExam(@Valid @ModelAttribute GradeForm gradeForm, Model model, RedirectAttributes redirectAttributes)
	{
		// Set the new grade for every applicant
		gradeForm.getApplicants().forEach((applicant) -> {
			
			if(applicant.getGrade() == 6)
			{
				examApplicationRepo.updateGrade(null, applicant.getId());
				applicant.setGrade(null);
			}
			else
			{
				examApplicationRepo.updateGrade(applicant.getGrade(), applicant.getId());
			}
		});
		
		boolean examGraded = true;
		
		redirectAttributes.addFlashAttribute("examGraded", examGraded);
		return "redirect:/writtenExams";
	}
}
