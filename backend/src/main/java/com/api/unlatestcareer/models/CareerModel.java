package com.api.unlatestcareer.models;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@ToString
public class CareerModel {
	
	private int id;
	
	private String name;
	private boolean enabled;
	private String link;
	
	private LocalDate createdAt;
	private LocalDate updateAt;
	
}
