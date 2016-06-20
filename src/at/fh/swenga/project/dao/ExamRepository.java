package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.project.model.DegreeProgramModel;
import at.fh.swenga.project.model.ExamModel;

@Repository
@Transactional
public interface ExamRepository extends JpaRepository<ExamModel, Integer> {
	
	
	public List<ExamModel> findAll();
	
	public List<ExamModel> findByCourseDegreeProgram(DegreeProgramModel degreeProgram);
	
	

}
