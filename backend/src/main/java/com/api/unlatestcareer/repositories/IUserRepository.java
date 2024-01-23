package com.api.unlatestcareer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.unlatestcareer.entities.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUsername(String username);
	
	// @Query("SELECT p FROM User u JOIN u.profiles p WHERE u.id = :userId AND p.status = :status")
}
