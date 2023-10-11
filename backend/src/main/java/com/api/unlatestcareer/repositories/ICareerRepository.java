package com.api.unlatestcareer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.unlatestcareer.entities.Career;
import com.api.unlatestcareer.models.CareerModel;

public interface ICareerRepository extends JpaRepository<Career, Integer> {

	public abstract CareerModel findByName(String name);
	
}
