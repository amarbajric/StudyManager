package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.fh.swenga.project.model.ExamApplicationModel;
import at.fh.swenga.project.model.StudentModel;
import at.fh.swenga.project.queryModels.Q_studentExam;

public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	
	
	public List<StudentModel> findAll();
	
	public StudentModel findById(int student_id);
	
	@Query(value = "SELECT COUNT(*) FROM students", nativeQuery=true)
	public int countAll();
	
	public int countByYearYear(String year);
	
	@Query(value="SELECT count(distinct st.id) as amount "
			+ "FROM professors pr "
			+ "join courses_professors cp on pr.id = cp.professor_id "
			+ "join courses co on cp.course_id = co.id "
			+ "join students_courses sc on co.id = sc.course_id "
			+ "join students st on sc.student_id = st.id "
			+ "where pr.id = ?1",nativeQuery=true)
	public int countByCoursesOfProfessor(int professor_id);
	
	public StudentModel findByMail(String mail);

	public List<StudentModel> findByYearYear(String year);//example: IMA2014
	
	@Query(value = "SELECT ed.id as id, co.acronym as course, ex.type as type, ed.description as description, ed.date as date, co.ectsValue as ects, ro.description as room, "
			+ "IF(st.id = (SELECT student_id FROM exam_applications where examDate_id = ed.id and student_id = st.id),1,0) as enrolled "
			+ "FROM students st "
			+ "JOIN students_courses sc ON st.id = sc.student_id "
			+ "JOIN courses co ON sc.course_id = co.id "
			+ "JOIN exams ex ON co.id = ex.course_id "
			+ "JOIN exam_dates ed ON ex.id = ed.exam_id "
			+ "JOIN rooms ro ON ed.room_id = ro.id "
			+ "WHERE st.id = ?1 and ed.date > NOW()", nativeQuery=true)
	public List<Object[]> findFutureExams(int student_id);

}
