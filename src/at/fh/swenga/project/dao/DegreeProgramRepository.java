package at.fh.swenga.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.project.model.DegreeProgramModel;

@Repository
public interface DegreeProgramRepository extends JpaRepository<DegreeProgramModel, Integer> {

}
