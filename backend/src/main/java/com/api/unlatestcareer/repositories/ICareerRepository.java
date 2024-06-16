package com.api.unlatestcareer.repositories;

import java.util.List;
import java.util.Optional;

import com.api.unlatestcareer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.unlatestcareer.entities.Career;

@Repository("careerRepository")
public interface ICareerRepository extends JpaRepository<Career, Integer> {

	public Optional<Career> findByName(String name);

	@Query("SELECT c FROM Career c WHERE c.enabled = true")
	List<Career> findByEnabledTrue();
}
