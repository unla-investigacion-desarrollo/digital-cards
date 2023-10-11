package com.api.unlatestcareer.services;

import java.util.List;

import com.api.unlatestcareer.models.CareerModel;

public interface ICareerService {
	
	CareerModel findById(int id);

	CareerModel findByName(String name);

	List<CareerModel> getAll();

	boolean deleteById(int id);
	
	CareerModel save(CareerModel career);
	
	CareerModel update(CareerModel career, int careerId);
	
}
