package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.project.model.CourseModel;
import at.fh.swenga.project.model.DegreeProgramModel;
import at.fh.swenga.project.model.ExamModel;

@Repository
@Transactional
public interface ExamRepository extends JpaRepository<ExamModel, Integer> {
	
	
	public List<ExamModel> findAll();
	
	public ExamModel findByDescriptionAndTypeAndCourse(String description,String type,CourseModel course);
	
	public List<ExamModel> findByCourseDegreeProgram(DegreeProgramModel degreeProgram);
	
	@Query(value = "SELECT ex.* "
			+ "FROM exams ex "
			+ "JOIN courses co ON ex.course_id = co.id "
			+ "JOIN courses_professors cp ON co.id = cp.course_id "
			+ "WHERE cp.professor_id = ?1 "
			+ "ORDER BY ex.description asc, ex.type asc", nativeQuery=true)
	public List<ExamModel> findByCourseProfessor(int professor_id);
	
	@Modifying
	@Query(value = "INSERT INTO exams(description, type, course_id) "
			+ "VALUES((?1), (?2), CAST((SELECT id FROM courses where acronym = (?1);) AS INT)", nativeQuery=true)
	public void createNewExam(String description, String type);
	

}
