package com.api.unlatestcareer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.unlatestcareer.entities.Title;
import com.api.unlatestcareer.models.TitleModel;

public interface ITitleRepository extends JpaRepository<Title, Integer> {

	public abstract TitleModel findByName(String name);
}
