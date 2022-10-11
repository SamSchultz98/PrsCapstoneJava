package com.bootcamp.Capstone.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.bootcamp.Capstone.Models.Requestline;

public interface RequestlineRepository extends CrudRepository<Requestline,Integer>{
Iterable<Requestline> findByRequestId(int requestid);


}
