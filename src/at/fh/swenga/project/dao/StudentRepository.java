package at.fh.swenga.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import at.fh.swenga.project.model.StudentModel;

public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	
}
