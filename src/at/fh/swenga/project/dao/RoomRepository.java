package at.fh.swenga.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.project.model.RoomModel;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Integer> {
	
	public List<RoomModel> findAll();
	
	public RoomModel findById(int room_id);

}
