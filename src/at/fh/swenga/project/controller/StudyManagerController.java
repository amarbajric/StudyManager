package at.fh.swenga.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.fh.swenga.project.dao.DegreeProgramRepository;
import at.fh.swenga.project.dao.StudentRepository;
import at.fh.swenga.project.dao.UserRepository;
import at.fh.swenga.project.dao.UserRoleRepository;
import at.fh.swenga.project.dao.YearRepository;
import at.fh.swenga.project.model.DegreeProgramModel;
import at.fh.swenga.project.model.StudentModel;
import at.fh.swenga.project.model.User;
import at.fh.swenga.project.model.UserRole;
import at.fh.swenga.project.model.YearModel;

@Controller
public class StudyManagerController {

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	YearRepository yearRepo;

	@Autowired
	DegreeProgramRepository degreeProgramRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserRoleRepository userRoleRepo;
	
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
        System.out.println(role);

        String targetUrl = "";
        if(role.toLowerCase().contains("professor")) {
            targetUrl = "/professor/index";
        } else if(role.toLowerCase().contains("student")) {
            targetUrl = "/student/index";
        }
        else if(role.toLowerCase().contains("admin")){
        	targetUrl = "admin/index";
        }      
        
        
        
        

		System.out.println(userRoleRepo.findAll().get(0).getRole());
        
        
        
		
		return "forward:" + targetUrl;
	}
	
	
	@RequestMapping(value = {"fill"})
	@Transactional
	public String fillData(Model model){
		
		StudentModel clagger = new StudentModel("Christian", "Lagger");

		YearModel IMA2014 = new YearModel("2014");

		DegreeProgramModel IMA = new DegreeProgramModel("IMA", "Werner Fritz", "Informationsmanagement", "Bachelor");

		IMA2014.setDegreeProgram(IMA);
		clagger.setYear(IMA2014);

		degreeProgramRepo.save(IMA);
		yearRepo.save(IMA2014);
		studentRepo.save(clagger);
		
		User claggerUser = new User(clagger.getMail(), "$2a$10$2BZh7qw/FSh23ZCbojA.OOoo7vzg7KaqHUp34l8/i9.ktxzcr3vJm", true);
		UserRole userRole = new UserRole(claggerUser, "ROLE_USER");
		userRepo.save(claggerUser);

		
		return "forward:list";
	}

	@RequestMapping(value = { "/getPage" })
	public String getPage(Pageable page, Model model) {

		StudentModel clagger = new StudentModel("Christian", "Lagger");

		YearModel IMA2014 = new YearModel("2014");

		DegreeProgramModel IMA = new DegreeProgramModel("IMA", "Werner Fritz", "Informationsmanagement", "Bachelor");

		IMA2014.setDegreeProgram(IMA);
		clagger.setYear(IMA2014);

		degreeProgramRepo.save(IMA);
		yearRepo.save(IMA2014);
		studentRepo.save(clagger);
		
		Page<StudentModel> students = studentRepo.findAll(page);
		model.addAttribute("carsPage", students);
		model.addAttribute("students", students.getContent());
		model.addAttribute("type", "findAll");
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}

}
