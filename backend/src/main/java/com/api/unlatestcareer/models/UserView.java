package com.api.unlatestcareer.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserView {
	
	private String username;
	private LocalDate createdAt;
	private LocalDate updateAt;
	private String token;
}
