package at.fh.swenga.project.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import at.fh.swenga.project.dao.CourseRepository;
import at.fh.swenga.project.dao.DegreeProgramRepository;
import at.fh.swenga.project.dao.ExamApplicationRepository;
import at.fh.swenga.project.dao.ExamDateRepository;
import at.fh.swenga.project.dao.ExamRepository;
import at.fh.swenga.project.dao.ProfessorRepository;
import at.fh.swenga.project.dao.StudentRepository;
import at.fh.swenga.project.dao.UserRepository;
import at.fh.swenga.project.dao.YearRepository;
import at.fh.swenga.project.model.CourseModel;
import at.fh.swenga.project.model.ExamApplicationModel;
import at.fh.swenga.project.model.ExamDateModel;
import at.fh.swenga.project.model.ExamModel;
import at.fh.swenga.project.model.ProfessorModel;
import at.fh.swenga.project.model.Q_ProfessorExam;
import at.fh.swenga.project.model.Q_studentExam;
import at.fh.swenga.project.model.StudentModel;
import at.fh.swenga.project.util.MapSorter;


@Controller
@SessionAttributes("student")
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
        	
        	//getting all the future exams of a student
        	List<Object[]> professorExamsData = examDateRepo.findProfExams(profData.getId());
        	
        	//Adding the Data of the exams into a List of Q_ProfessorExam
        	List<Q_ProfessorExam> professorExams = new ArrayList<Q_ProfessorExam>();
           	for (int i = 0; i < professorExamsData.size(); i++) {
        		Object[] arr = professorExamsData.get(i);
        		Date date = (Date)arr[1];
        		Q_ProfessorExam exam = new Q_ProfessorExam(Integer.parseInt(arr[0].toString()) ,date,arr[2].toString(),arr[3].toString(),arr[4].toString(), arr[5].toString(), Integer.parseInt(arr[6].toString()));
        		professorExams.add(exam);
        		}


        	model.addAttribute("professorExams",professorExams);
        	model.addAttribute("professorData",profData);
            targetUrl = "professor/index";
            
            
            
        /***************************STUDENT INDEX PAGE*******************************************/
        } else if(role.toLowerCase().contains("student")) {
        	//find all the data of the specific Student who logged in.
        	StudentModel studentData = studentRepo.findByMail(mailOfUser);
        	//get all graded exams
        	List<ExamApplicationModel> gradedExams = examApplicationRepo.findByStudentAndGradeIsNotNullOrderByExamDateDateDesc(studentData);
        	
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
        	model.addAttribute("grades",grades);
        	model.addAttribute("upcomingStudentExams", upcomingStudentExams);

            
        	targetUrl = "student/index";
        }
      
		//no forward because then it won't search in views folder!
		return targetUrl;
	}
	
	
	
/********************************************GRADES STUDENT PAGE***********************************************************/
	@RequestMapping(value = "/grades", method = RequestMethod.GET)
	public String showGrades(Model model) {
    	//find all the data of the specific Student who logged in.
    	StudentModel studentData = studentRepo.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
		
    	//get all graded exams
    	List<ExamApplicationModel> gradedExams = examApplicationRepo.findByStudentAndGradeIsNotNullOrderByExamDateDateDesc(studentData);
    	
    	
    	/*************************SETTING THE MODELS***************************/
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
       	
       	
       	/*************************SETTING THE MODELS***************************/
    	model.addAttribute("studentData",studentData);
    	model.addAttribute("futureStudentExams",futureStudentExams);
		return "student/exams";
	}
	


/********************************************MANAGE EXAMS PAGE (STUDENT)***********************************************************/
	@RequestMapping(value="/manageExam", method=RequestMethod.GET)
	public String manageExam(@RequestParam String action, @RequestParam int id)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String mailOfUser = auth.getName();
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
			int delete = examApplicationRepo.removeByStudentIdAndExamDateId(studentData.getId(), id);
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
	@RequestMapping(value = "/addExam", method = RequestMethod.GET)
	public String addExam(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String mailOfUser = auth.getName();
    	//find all the data of the specific Student who logged in.
    	ProfessorModel profData = professorRepo.findByMail(mailOfUser);
       	
       	List<ExamModel> exams = examRepo.findByCourseProfessor(profData.getId());
       	
       	/*************************SETTING THE MODELS***************************/
    	model.addAttribute("professorExams",exams);
    	model.addAttribute("professorData",profData);

		return "professor/addExam";
	}
	
	
/********************************************ADDING EXAM***********************************************************/	
	@RequestMapping(value="/addExamModel", method=RequestMethod.GET)
	public String modelAdd(Model model,@RequestParam String courseSelected, @RequestParam String typeSelected)
	{
		CourseModel course = courseRepo.findByAcronym(courseSelected);
		ExamModel existsExam = examRepo.findByDescriptionAndTypeAndCourse(courseSelected,typeSelected, course);
		
		if(existsExam == null)
		{
			ExamModel exam = new ExamModel(courseSelected,typeSelected, course);		
			examRepo.save(exam);
			model.addAttribute("alreadyExists",false);
		}
		else
		{
			model.addAttribute("alreadyExists",true);
		}
		
		return "forward:/addExam";
	}
	
	
	

}
