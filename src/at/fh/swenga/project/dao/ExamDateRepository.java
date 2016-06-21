package at.fh.swenga.project.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.project.model.ExamDateModel;

@Repository
@Transactional
public interface ExamDateRepository extends JpaRepository<ExamDateModel, Integer> {
	
	public List<ExamDateModel> findAll();
	
	public ExamDateModel findById(int id);
	
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
			+ "RIGHT JOIN exam_applications exa ON exa.examDate_id = exd.id "
			+ "where p.id = ?1 "
			+ "group by exd.id "
			+ "order by exd.date desc", nativeQuery=true)
	public List<Object[]> findProfExams(int professor_id);
	
	
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

	
}
