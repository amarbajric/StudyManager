package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import at.fh.swenga.project.model.ProfessorModel;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Integer> {

	public List<ProfessorModel> findAll();
	
	public ProfessorModel findByMail(String mail);
	
	@Query(value = "SELECT COUNT(*) FROM professors", nativeQuery=true)
	public int countAll();
	
	@Query(value = "SELECT ifnull(avg(ea.grade),0) as average "
			+ "FROM exam_applications ea "
			+ "join exam_dates ed on ea.examDate_id = ed.id "
			+ "join exams ex on ed.exam_id = ex.id "
			+ "join courses co on ex.course_id = co.id "
			+ "join courses_professors cp on co.id = cp.course_id "
			+ "where cp.professor_id = ?1 and ea.grade is not null", nativeQuery=true)
	public Double findAverageGradeOfAllExams(int professor_id);
	
	
	@Query(value="SELECT ea.grade, COUNT(*) as amount "
			+ "FROM exam_applications ea "
			+ "join exam_dates	ed on ea.examDate_id = ed.id "
			+ "join exams ex on ed.exam_id = ex.id "
			+ "join courses co on ex.course_id = co.id "
			+ "join courses_professors cp on co.id = cp.course_id "
			+ "where cp.professor_id = ?1 "
			+ "GROUP BY ea.grade;",nativeQuery=true)
	public List<Object[]> findAmountOfGradesByProfessor(int professor_id);

}
