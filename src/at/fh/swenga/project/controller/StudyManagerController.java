package at.fh.swenga.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.fh.swenga.project.dao.DegreeProgramRepository;
import at.fh.swenga.project.dao.ProfessorRepository;
import at.fh.swenga.project.dao.StudentRepository;
import at.fh.swenga.project.dao.UserRepository;
import at.fh.swenga.project.dao.YearRepository;
import at.fh.swenga.project.model.ProfessorModel;
import at.fh.swenga.project.model.StudentModel;


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
        	StudentModel studentData = studentRepo.findByMail(mailOfUser);
        	model.addAttribute("studentData",studentData);
            targetUrl = "student/index";
        }
        else if(role.toLowerCase().contains("admin")){
        	targetUrl = "admin/index";
        }      
        
		//no forward because then it won't search in views folder!
		return targetUrl;
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}

}
