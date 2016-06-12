package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.fh.swenga.project.model.StudentModel;

public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	
	
	public List<StudentModel> findAll();
	
	
}
