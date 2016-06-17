package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.fh.swenga.project.model.ExamApplicationModel;
import at.fh.swenga.project.model.StudentModel;

public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	
	
	public List<StudentModel> findAll();
	
	public StudentModel findByMail(String mail);
}
