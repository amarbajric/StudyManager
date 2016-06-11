package at.fh.swenga.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import at.fh.swenga.project.model.YearModel;

public interface YearRepository  extends JpaRepository<YearModel, Integer>{

}
