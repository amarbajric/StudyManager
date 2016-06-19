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
	

	
}
