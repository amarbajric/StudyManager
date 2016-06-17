package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.project.model.ExamApplicationModel;
import at.fh.swenga.project.model.StudentModel;

@Repository
@Transactional
public interface ExamApplicationRepository extends JpaRepository<ExamApplicationModel, Integer> {
	
	
	public List<ExamApplicationModel> findAll();
	
	public List<ExamApplicationModel> findTop5ByStudentOrderByExamDateDateAsc(StudentModel student);

}

