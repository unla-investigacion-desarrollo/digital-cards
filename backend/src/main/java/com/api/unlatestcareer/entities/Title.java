package com.api.unlatestcareer.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.api.unlatestcareer.models.TitleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "title")
public class Title implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "createdAt")
	private LocalDate createdAt;

	@Column(name = "updateAt")
	private LocalDate updateAt;

	public Title(String name, LocalDate createdAt, LocalDate updateAt) {
		super();
		this.name = name;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public Title(TitleModel titleModel) {
		this.id = titleModel.getId();
		this.name = titleModel.getName();
		this.createdAt = titleModel.getCreatedAt();
		this.updateAt = titleModel.getUpdateAt();
	}
}
