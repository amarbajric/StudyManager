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
	
	@Query(value = "select ea.* "
			+ "from exam_applications ea "
			+ "join exam_dates ed on ea.examDate_id = ed.id "
			+ "where student_id = ?1 and grade is not null "
			+ "order by ed.date desc", nativeQuery=true)
	public List<ExamApplicationModel> findByStudentAndGradeIsNotNullOrderByExamDateDateDesc(StudentModel student);
	
	@Query(value = "select ea.* "
			+ "from exam_applications ea "
			+ "join exam_dates ed on ea.examDate_id = ed.id "
			+ "where student_id = ?1 and grade is not null "
			+ "order by ed.date desc "
			+ "limit 5", nativeQuery=true)
	public List<ExamApplicationModel> findTop5ByStudentAndGradeIsNotNullOrderByExamDateDateDesc(StudentModel student);
	
	@Query(value= "SELECT s.id,AVG(e.grade) as average FROM students s join exam_applications e ON s.id = e.student_id GROUP BY s.id  ORDER BY average ASC ;  ", nativeQuery=true)
	public List<Object[]> findAverageGrades();
	
	
	@Query(value = "SELECT grade, COUNT(*) as anzahl FROM exam_applications where student_id = ?1 and grade is not null group by grade;", nativeQuery=true)
	public List<Object[]> findNumberOfGradesByGrade(int student_id);
	
	@Query(value = "SELECT ifnull(max(attempt),0) as attempt "
			+ "FROM exam_applications "
			+ "WHERE student_id = ?1 AND examDate_id = ?2", nativeQuery=true)
	public Integer attemptOfExam(int student_id, int examDate_id);

}

