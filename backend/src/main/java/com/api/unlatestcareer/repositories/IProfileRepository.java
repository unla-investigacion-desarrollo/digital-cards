package com.api.unlatestcareer.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.models.ProfileModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProfileRepository extends JpaRepository<Profile, Integer>{

	public abstract ProfileModel findByName(String name);

	@Query("SELECT p FROM Profile p WHERE p.enabled = true")
	List<Profile> findByEnableTrue();

	@Query("SELECT p from Profile p WHERE p.id = :id AND p.enabled = true")
	Optional<Profile> findByIdAndEnableTrue(@Param("id") int id);

	@Query("SELECT p FROM User u JOIN u.profiles p WHERE u.id = :id AND p.enabled = true")
	List<Profile> findProfilesByUserId(@Param("id") int id);
}
