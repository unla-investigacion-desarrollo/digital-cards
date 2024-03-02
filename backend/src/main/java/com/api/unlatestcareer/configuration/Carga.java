package com.api.unlatestcareer.configuration;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.api.unlatestcareer.entities.Career;
import com.api.unlatestcareer.entities.User;
import com.api.unlatestcareer.repositories.ICareerRepository;
import com.api.unlatestcareer.repositories.IUserRepository;

@Component
public class Carga implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

	@Autowired
	@Qualifier("careerRepository")
	private ICareerRepository careerRepository;

	@Override
	public void run(String... args) throws Exception {

		if (userRepository.findByUsername("admin@gmail.com").orElse(null) == null) {
			User defaultUser = new User("admin@gmail.com", "ADMIN", encoder.encode("admin123"), true, LocalDate.now(),
					LocalDate.now(), null);
			userRepository.save(defaultUser);
		}

		if (userRepository.findByUsername("user@gmail.com").orElse(null) == null) {
			User testUser = new User("user@gmail.com", "USER", encoder.encode("user123"), true, LocalDate.now(),
					LocalDate.now(), null);
			userRepository.save(testUser);
		}

		if (careerRepository.findByName("Licenciatura en Audiovisión").orElse(null) == null) {
			Career testCareer1 = new Career("Licenciatura en Audiovisión",
					"https://www.unla.edu.ar/carreras/grado/licenciaturas/audiovision", null, null, true);
			careerRepository.save(testCareer1);
		}

		if (careerRepository.findByName("Licenciatura en Ciencia Política y Gobierno").orElse(null) == null) {
			Career testCareer2 = new Career("Licenciatura en Ciencia Política y Gobierno",
					"https://www.unla.edu.ar/carreras/grado/licenciaturas/ciencia-politica-y-gobierno", null, null,
					true);
			careerRepository.save(testCareer2);
		}

		if (careerRepository.findByName("Licenciatura en Ciencia y Tecnología de los Alimentos").orElse(null) == null) {
			Career testCareer3 = new Career("Licenciatura en Ciencia y Tecnología de los Alimentos",
					"https://www.unla.edu.ar/carreras/grado/licenciaturas/ciencia-y-tecnologia-de-los-alimentos", null,
					null, true);
			careerRepository.save(testCareer3);
		}

		if (careerRepository.findByName("Licenciatura en Diseño Industrial").orElse(null) == null) {
			Career testCareer4 = new Career("Licenciatura en Diseño Industrial",
					"https://www.unla.edu.ar/carreras/grado/licenciaturas/diseno-industrial", null, null, true);
			careerRepository.save(testCareer4);
		}

		if (careerRepository.findByName("Licenciatura en Diseño y Comunicación Visual").orElse(null) == null) {
			Career testCareer5 = new Career("Licenciatura en Diseño y Comunicación Visual",
					"https://www.unla.edu.ar/carreras/grado/licenciaturas/diseno-y-comunicacion-visuall", null, null,
					true);
			careerRepository.save(testCareer5);
		}
	}
}
