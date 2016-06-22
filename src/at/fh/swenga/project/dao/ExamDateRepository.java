package at.fh.swenga.project.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.project.model.ExamDateModel;

@Repository
@Transactional
public interface ExamDateRepository extends JpaRepository<ExamDateModel, Integer> {
	
	public List<ExamDateModel> findAll();
	
	public ExamDateModel findById(Integer id);
	
	
	@Query(value = "SELECT count(*) "
			+ "FROM exam_dates ed "
			+ "join exam_applications ea on ed.id = ea.examDate_id "
			+ "join exams ex on ed.exam_id = ex.id "
			+ "where ed.exam_id = ?1 and ea.student_id = ?2 and ea.grade < 5 and ex.type = 'final'" , nativeQuery=true)
	public int checkIfStudentWasAlreadyPositive(int examId,int studentId);
	
	
	@Query(value = "SELECT count(*) "
			+ "FROM exam_dates ed "
			+ "join exam_applications ea on ed.id = ea.examDate_id "
			+ "join exams ex on ed.exam_id = ex.id "
			+ "join courses co on ex.course_id = co.id "
			+ "join courses_professors cp on co.id = cp.course_id "
			+ "where cp.professor_id = ?1 and ed.date < now()",nativeQuery=true)
	public Integer countByProfessorId(int professor_id);
	
	@Query(value = "SELECT COUNT(*) FROM exam_dates ed "
			+ "WHERE ed.exam_id = ?1 AND ed.date = ?2", nativeQuery=true)
	public Integer findByExamAndDate(int exam_id, Date date);
	
	
	@Query(value = "SELECT exd.id, exd.date, exd.description as datenumber, ex.description as course, ex.type, r.description as room, count(exa.id) as applicants "
			+ "FROM exam_dates exd "
			+ "JOIN exams ex ON exd.exam_id = ex.id "
			+ "JOIN courses c ON ex.course_id = c.id "
			+ "JOIN courses_professors cp ON c.id = cp.course_id "
			+ "JOIN professors p ON cp.professor_id = p.id "
			+ "JOIN rooms r ON exd.room_id = r.id "
			+ "LEFT JOIN exam_applications exa ON exa.examDate_id = exd.id "
			+ "where p.id = ?1 and exd.date > now() "
			+ "group by exd.id "
			+ "order by exd.date desc", nativeQuery=true)
	public List<Object[]> findUpcomingProfExams(int professor_id);
	
	@Query(value = "SELECT exd.id, exd.date, exd.description as datenumber, ex.description as course, ex.type, r.description as room, count(exa.id) as applicants "
			+ "FROM exam_dates exd "
			+ "JOIN exams ex ON exd.exam_id = ex.id "
			+ "JOIN courses c ON ex.course_id = c.id "
			+ "JOIN courses_professors cp ON c.id = cp.course_id "
			+ "JOIN professors p ON cp.professor_id = p.id "
			+ "JOIN rooms r ON exd.room_id = r.id "
			+ "RIGHT JOIN exam_applications exa ON exa.examDate_id = exd.id "
			+ "where p.id = ?1 AND exd.date < NOW() "
			+ "group by exd.id "
			+ "order by exd.date desc", nativeQuery=true)
	public List<Object[]> findProfExamsToGrade(int professor_id);
	
	@Query(value = "SELECT ed.id as id, co.acronym as course, ex.type as type, ea.attempt as attempt, ed.date as date, co.ectsValue as ects, ro.description as room "
			+ "FROM exam_dates ed "
			+ "JOIN exam_applications ea ON ed.id = ea.examDate_id "
			+ "JOIN exams ex ON ed.exam_id = ex.id "
			+ "JOIN courses co ON ex.course_id = co.id "
			+ "JOIN rooms ro ON ed.room_id = ro.id "
			+ "WHERE ea.student_id = ?1 and ed.date > NOW() "
			+ "ORDER BY ed.date asc "
			+ "LIMIT 5", nativeQuery=true)
	public List<Object[]>findUpcomingExams(int student_id);

	@Modifying
	@Query(value="UPDATE exam_dates ex "
			+ "SET ex.room_id = ?1 , ex.date = ?2, ex.description = ?3 "
			+ "WHERE ex.id = ?4", nativeQuery=true)
	public void updateExamDate(int roomId, Date date, String description, Integer examDateId);
	
}
