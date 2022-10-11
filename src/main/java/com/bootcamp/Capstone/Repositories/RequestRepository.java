package com.bootcamp.Capstone.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bootcamp.Capstone.Models.Request;

public interface RequestRepository extends CrudRepository<Request,Integer> {

	List<Request> findAllByStatus(String status);
	Request findByStatus(String status);
}
