package com.api.unlatestcareer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.models.ProfileModel;

public interface IProfileRepository extends JpaRepository<Profile, Integer>{

	public abstract ProfileModel findByName(String name);
}
