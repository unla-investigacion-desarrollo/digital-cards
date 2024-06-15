package com.api.unlatestcareer.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.models.ProfileModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProfileRepository extends JpaRepository<Profile, Integer>{

	public abstract ProfileModel findByName(String name);

	@Query("SELECT p FROM Profile p WHERE p.enabled = true")
	List<Profile> findByEnableTrue();
	
}
