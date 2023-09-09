package com.api.unlatestcareer.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.api.unlatestcareer.models.CareerModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="career")
public class Career implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="link")
	private String link;
	
	@Column(name="createdAt")
	private LocalDate createdAt;
	
	@Column(name="updateAt")
	private LocalDate updateAt;

	public Career(String name, String link, LocalDate createdAt, LocalDate updateAt, boolean enabled) {
		super();
		this.name = name;
		this.enabled = enabled;
		this.link = link;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
	
	public Career(CareerModel careerModel) {
		super();
		this.name = careerModel.getName();
		this.enabled = careerModel.isEnabled();
		this.link = careerModel.getLink();
		this.createdAt = careerModel.getCreatedAt();
		this.updateAt = careerModel.getUpdateAt();
	}
	
}
