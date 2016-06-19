package at.fh.swenga.project.controller;


import java.util.ArrayList;
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

import at.fh.swenga.project.dao.DegreeProgramRepository;
import at.fh.swenga.project.dao.ExamApplicationRepository;
import at.fh.swenga.project.dao.ExamDateRepository;
import at.fh.swenga.project.dao.ExamRepository;
import at.fh.swenga.project.dao.ProfessorRepository;
import at.fh.swenga.project.dao.StudentRepository;
import at.fh.swenga.project.dao.UserRepository;
import at.fh.swenga.project.dao.YearRepository;
import at.fh.swenga.project.model.ExamApplicationModel;
import at.fh.swenga.project.model.ExamModel;
import at.fh.swenga.project.model.ProfessorModel;
import at.fh.swenga.project.model.StudentModel;
import at.fh.swenga.project.util.MapSorter;


@Controller
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
	
	/*@RequestMapping("/fill")
	@Transactional
	public String fillData(Model model) {

		StudentModel clagger = new StudentModel("Christian", "Lagger");

		YearModel IMA2014 = new YearModel("2014");

		DegreeProgramModel IMA = new DegreeProgramModel("IMA", "Werner Fritz", "Informationsmanagement", "Bachelor");

		IMA2014.setDegreeProgram(IMA);
		clagger.setYear(IMA2014);

		degreeProgramRepo.save(IMA);
		yearRepo.save(IMA2014);
		studentRepo.save(clagger);

		return "index";
	}*/
	
	
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
        if(role.toLowerCase().contains("professor")) {
        	ProfessorModel profData = professorRepo.findByMail(mailOfUser);
        	model.addAttribute("professorData",profData);
            targetUrl = "professor/index";
        } else if(role.toLowerCase().contains("student")) {
        	//find all the data of the specific Student who logged in.
        	StudentModel studentData = studentRepo.findByMail(mailOfUser);
        	//get top 5 latest graded exams
        	List<ExamApplicationModel> examApplications = examApplicationRepo.findTop5ByStudentAndGradeIsNotNullOrderByExamDateDateDesc(studentData);
        	//get the total amount of students
        	List<StudentModel> allStudents = studentRepo.findAll();
        	//get all students who are studying with the specific student
        	List<StudentModel> studentColleagues = studentRepo.findByYearYear(studentData.getYear().getYear());
        	//find all exams of a degreeProgram
        	List<ExamModel> examsOfDegreeProgram = examRepo.findByCourseDegreeProgram(studentData.getYear().getDegreeProgram());
        	//set data in model object(grades)
        	List<Object[]> numberOfGradesByGrade = examApplicationRepo.findNumberOfGradesByGrade(studentData.getId());
        	//get grades into Map
        	Map<Integer,Integer> gradesMap = new HashMap<Integer,Integer>();
        	for (int i = 0; i < numberOfGradesByGrade.size(); i++) {
        		Object[] arr = numberOfGradesByGrade.get(i);
        		gradesMap.put(Integer.parseInt(arr[0].toString()), Integer.parseInt(arr[1].toString()));
        		}
        	//set grades which don't exist to zero
        	for (int i = 1; i < 6; i++) {
        		if (!gradesMap.containsKey(i)){
        			gradesMap.put(i,0);
        		}
        	}
        	//setting the number of every grade to a list
        	List<Integer> grades = new ArrayList(gradesMap.values());
        	
        	
        	//get the average grade from all students
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
        	
        	System.out.println(average);
        	
        	//setting models
        	model.addAttribute("average",average);
        	model.addAttribute("rank",rank);
        	model.addAttribute("examsOfDegreeProgram",examsOfDegreeProgram);
        	model.addAttribute("studentColleagues",studentColleagues);
        	model.addAttribute("allStudents",allStudents);
        	model.addAttribute("studentData",studentData);
        	model.addAttribute("examApplications",examApplications);
        	model.addAttribute("grades",grades);
            
        	targetUrl = "student/index";
        }
        else if(role.toLowerCase().contains("admin")){
        	targetUrl = "admin/index";
        }      
        
		//no forward because then it won't search in views folder!
		return targetUrl;
	}
	
	@RequestMapping(value = "/grades", method = RequestMethod.GET)
	public String showGrades(Model model) {
    	//find all the data of the specific Student who logged in.
    	StudentModel studentData = studentRepo.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
		
    	//get all graded exams
    	List<ExamApplicationModel> gradedExams = examApplicationRepo.findByStudentAndGradeIsNotNullOrderByExamDateDateDesc(studentData);
    	model.addAttribute("studentData",studentData);
    	model.addAttribute("gradedExams",gradedExams);
		return "student/grades";
	}
	
	
	@RequestMapping(value = "/exams", method = RequestMethod.GET)
	public String showExams(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String mailOfUser = auth.getName();
    	//find all the data of the specific Student who logged in.
    	StudentModel studentData = studentRepo.findByMail(mailOfUser);
    	//Implementation of Exam Application view

    	model.addAttribute("studentData",studentData);
		return "student/exams";
	}
	

	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}
	
	
	

}
