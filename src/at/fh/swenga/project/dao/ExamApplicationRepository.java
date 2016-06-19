package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.project.model.ExamApplicationModel;
import at.fh.swenga.project.model.StudentModel;

@Repository
@Transactional
public interface ExamApplicationRepository extends JpaRepository<ExamApplicationModel, Integer> {
	
	
	public List<ExamApplicationModel> findAll();
	
	public List<ExamApplicationModel> findByStudentAndGradeIsNotNullOrderByExamDateDateDesc(StudentModel student);
	
	public List<ExamApplicationModel> findTop5ByStudentAndGradeIsNotNullOrderByExamDateDateDesc(StudentModel student);
	
	@Query(value = "SELECT grade, COUNT(*) as anzahl FROM IMA14_lagger_project1.exam_applications where student_id = ?1 group by grade;", nativeQuery=true)
	public List<Object[]> findNumberOfGradesByGrade(int student_id);

}

