package com.bootcamp.Capstone.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.bootcamp.Capstone.Models.User;

public interface UserRepository extends CrudRepository<User,Integer>{

	
	User findByUsername(String username);
}
