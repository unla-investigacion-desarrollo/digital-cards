package com.api.unlatestcareer.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TitleModel {

	private int id;
	private String name;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	
	
}
