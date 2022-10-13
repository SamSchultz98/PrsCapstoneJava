package com.bootcamp.Capstone.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bootcamp.Capstone.Models.Requestline;

public interface RequestlineRepository extends CrudRepository<Requestline,Integer>{
	
List<Requestline> findByRequestId(int requestid);


}
