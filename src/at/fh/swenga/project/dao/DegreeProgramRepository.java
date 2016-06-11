package at.fh.swenga.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import at.fh.swenga.project.model.DegreeProgramModel;

public interface DegreeProgramRepository extends JpaRepository<DegreeProgramModel, Integer> {

}
