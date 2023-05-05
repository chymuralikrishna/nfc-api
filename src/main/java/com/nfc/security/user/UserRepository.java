package com.nfc.security.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u from User u where u.username =:name or u.email=:name")   
    Optional<User> findByUsernameOrEmail(@Param("name") String name);
  

}
