package com.api.unlatestcareer.services;

import java.util.List;

import com.api.unlatestcareer.models.TitleModel;

public interface ITitleService {
	TitleModel findById(int id);

	TitleModel findByName(String name);

	List<TitleModel> getAll();

	boolean deleteById(int id);
	
	TitleModel save(TitleModel title);
	
	TitleModel update(TitleModel title, int titleId);
}
