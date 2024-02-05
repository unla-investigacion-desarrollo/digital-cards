package com.api.unlatestcareer.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.api.unlatestcareer.models.CareerModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="career")
public class Career implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String name;
	private boolean enabled;
	private String link;
	private LocalDate createdAt;
	private LocalDate updateAt;

	public Career(String name, String link, LocalDate createdAt, LocalDate updateAt, boolean enabled) {
		super();
		this.name = name;
		this.enabled = enabled;
		this.link = link;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
	public Career(String name, String link,  LocalDate updateAt, boolean enabled) {
		super();
		this.name = name;
		this.enabled = enabled;
		this.link = link;
		this.updateAt = updateAt;
	}
	
	public Career(CareerModel careerModel) {
		super();
		this.name = careerModel.getName();
		this.enabled = careerModel.isEnabled();
		this.link = careerModel.getLink();
	}
}
