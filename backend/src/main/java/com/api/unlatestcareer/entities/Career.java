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
public class Career extends BaseEntityAudit{

	private String name;
	private String link;

	public Career(String name, String link, boolean enabled) {
		super();
		this.name = name;
		this.setEnabled(enabled);
		this.link = link;
	}
	public Career(String name, String link,  LocalDate updatedAt, boolean enabled) {
		super();
		this.name = name;
		this.setEnabled(enabled);
		this.link = link;
		this.setUpdatedAt(updatedAt);
	}
	
	public Career(CareerModel careerModel) {
		super();
		this.name = careerModel.getName();
		this.setEnabled(careerModel.isEnabled());
		this.link = careerModel.getLink();
	}
}
