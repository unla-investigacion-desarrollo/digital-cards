package com.api.unlatestcareer.configuration;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.api.unlatestcareer.entities.User;
import com.api.unlatestcareer.repositories.IUserRepository;
@Component
public class Carga implements CommandLineRunner {
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		if(userRepository.findByUsername("admin@gmail.com").orElse(null) == null) {
			User defaultUser = new User("admin@gmail.com", "ADMIN", encoder.encode("admin123"), true, LocalDate.now(),
					LocalDate.now(),null);
			userRepository.save(defaultUser);
		}
		
		if(userRepository.findByUsername("user@gmail.com").orElse(null) == null) {
			User testUser = new User("user@gmail.com", "USER", encoder.encode("user123"), true, LocalDate.now(),
					LocalDate.now(),null);
			userRepository.save(testUser);
		}
	}
}
