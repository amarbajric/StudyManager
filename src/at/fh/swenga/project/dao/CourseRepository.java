package at.fh.swenga.project.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.project.model.CourseModel;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Integer> {

}
