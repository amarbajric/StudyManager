package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.fh.swenga.project.model.ExamApplicationModel;
import at.fh.swenga.project.model.Q_studentExam;
import at.fh.swenga.project.model.StudentModel;

public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	
	
	public List<StudentModel> findAll();
	
	public StudentModel findByMail(String mail);

	public List<StudentModel> findByYearYear(String year);//example: IMA2014
	
	@Query(value = "SELECT co.acronym as course, ex.type as type, ed.description as description, ed.date as date, co.ectsValue as ects "
			+ "FROM students st "
			+ "JOIN students_courses sc ON st.id = sc.student_id "
			+ "JOIN courses co ON sc.course_id = co.id "
			+ "JOIN exams ex ON co.id = ex.course_id "
			+ "JOIN exam_dates ed ON ex.id = ed.exam_id "
			+ "WHERE st.id = ?1 and ed.date > NOW()", nativeQuery=true)
	public List<Object[]> findFutureExams(int student_id);

}
