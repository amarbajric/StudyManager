package at.fh.swenga.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.project.model.YearModel;


@Repository
public interface YearRepository  extends JpaRepository<YearModel, Integer>{

}
