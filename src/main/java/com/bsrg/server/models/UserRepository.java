package com.bsrg.server.models;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
	
	User save(User user);
	
	User findByUsername(String username);
}
