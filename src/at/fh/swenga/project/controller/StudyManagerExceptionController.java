package at.fh.swenga.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class StudyManagerExceptionController {
	
	@RequestMapping(value = "/error_404")
	public String resourceNotFoundException(Exception e) {
		
		return "errorPages/error_404";
	}
	
	@RequestMapping(value = "/error_500")
	public String internalServerErrorException(Exception e) {
		
		return "errorPages/error_500";
	}
	
	
	 @ExceptionHandler(Exception.class)
     public String handleOtherExceptions(Exception ex) {

         return "errorPages/showError";

     }

}
