package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.project.model.ExamDateModel;

@Repository
@Transactional
public interface ExamDateRepository extends JpaRepository<ExamDateModel, Integer> {
	
	public List<ExamDateModel> findAll();
	
	@Query(value= "SELECT exd.id, exd.date, exd.description as datenumber, ex.description as course, ex.type, r.description as room, count(exa.id) as applicants "
			+ "FROM exam_dates exd "
			+ "JOIN exams ex ON exd.exam_id = ex.id "
			+ "JOIN courses c ON ex.course_id = c.id "
			+ "JOIN courses_professors cp ON c.id = cp.course_id "
			+ "JOIN professors p ON cp.professor_id = p.id "
			+ "JOIN rooms r ON exd.room_id = r.id "
			+ "RIGHT JOIN exam_applications exa ON exa.examDate_id = exd.id "
			+ "where p.id = ?1 "
			+ "group by exd.id", nativeQuery=true)
	public List<Object[]> findProfExams(int id);

	
}
