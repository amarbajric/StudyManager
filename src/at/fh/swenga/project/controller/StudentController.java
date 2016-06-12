package at.fh.swenga.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.project.dao.DegreeProgramRepository;
import at.fh.swenga.project.dao.StudentRepository;
import at.fh.swenga.project.dao.YearRepository;
import at.fh.swenga.project.model.DegreeProgramModel;
import at.fh.swenga.project.model.StudentModel;
import at.fh.swenga.project.model.YearModel;

@Controller
public class StudentController {

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	YearRepository yearRepo;

	@Autowired
	DegreeProgramRepository degreeProgramRepo;

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
	
	@RequestMapping(value = {"/", "list"})
	public String showIndex(Model model) {
		List <StudentModel> students = studentRepo.findAll();
		model.addAttribute("students",students);
		model.addAttribute("type","Find All");
		return "index";
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

}
