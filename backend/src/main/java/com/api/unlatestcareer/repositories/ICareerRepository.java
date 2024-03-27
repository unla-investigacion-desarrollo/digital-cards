package com.api.unlatestcareer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.unlatestcareer.entities.Career;

@Repository("careerRepository")
public interface ICareerRepository extends JpaRepository<Career, Integer> {

	public Optional<Career> findByName(String name);	
}
