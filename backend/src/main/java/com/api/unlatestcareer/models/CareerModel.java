package com.api.unlatestcareer.models;

import java.time.LocalDate;
import lombok.*;

@Data
public class CareerModel {
	private int id;
	private String name;
	private String link;
	private boolean enabled;
}
