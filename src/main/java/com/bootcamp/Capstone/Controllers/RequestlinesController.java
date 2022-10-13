package com.bootcamp.Capstone.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bootcamp.Capstone.Models.Requestline;
import com.bootcamp.Capstone.Repositories.ProductRepository;
import com.bootcamp.Capstone.Repositories.RequestRepository;
import com.bootcamp.Capstone.Repositories.RequestlineRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/requestlines")
public class RequestlinesController {
	
	@Autowired
	private RequestlineRepository rlRepo;
	
	@Autowired
	private RequestRepository rq1Repo;
	
	@Autowired
	private ProductRepository proRepo;
	
	
	@GetMapping
	public ResponseEntity<Iterable<Requestline>> getAllRequestlines(){
		return new ResponseEntity<Iterable<Requestline>>(rlRepo.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Requestline> getRequestlinebyId(@PathVariable int id){
		var target = rlRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Requestline>(target.get(),HttpStatus.FOUND);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Requestline> updateRequestline(@PathVariable int id, @RequestBody Requestline requestline){
		var target = rlRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(target.get().getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var resetQuantity = reverse(target.get().getQuantity());
		target.get().setQuantity(resetQuantity);
		recalculate(id);
		
		rlRepo.save(requestline);
		recalculate(id);
		return new ResponseEntity<Requestline>(requestline,HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity<Requestline> createRequestline(@RequestBody Requestline requestline){
		if (requestline.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		var requestRequestid = requestline.getRequest().getId();		//Throws exception for when the Requestid in the body doesn't match one that exisits
		var attempt = rq1Repo.findById(requestRequestid);
		if(attempt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);	//424 error
		}
		var requestProductid = requestline.getProduct().getId();		//Throws exception for when the productid in the body doesn't match one that exisits
		var attempt1 = proRepo.findById(requestProductid);
		if(attempt1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);	//424 error
		}

		rlRepo.save(requestline);
		recalculate(requestline.getId());

		return new ResponseEntity<Requestline>(requestline, HttpStatus.CREATED);	
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteRequestline(@PathVariable int id) {
		var target = rlRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		var quantityForDelete = reverse(target.get().getQuantity());
		target.get().setQuantity(quantityForDelete);
		recalculate(id);
		rlRepo.delete(target.get());
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
		
	}
	
	private void recalculate(int requestlineid) {
		var request = rq1Repo.findById(rlRepo.findById(requestlineid).get().getRequest().getId());
		var product = proRepo.findById(rlRepo.findById(requestlineid).get().getProduct().getId()).get();
		var requestline = rlRepo.findById(requestlineid);
		var price = product.getPrice();
		var quantity = requestline.get().getQuantity();
		var total = request.get().getTotal();
		var newTotal = total + (quantity * price);
		
		
		if(newTotal<0) {
			newTotal = 0;
		}
		
		
		requestline.get().setRequest(request.get());
		
		request.get().setTotal(newTotal);	
		
		
		rq1Repo.save(request.get());
		rlRepo.save(requestline.get());
		
	}
	
	
	
	private int reverse(int value) {
		return value * -1;
	}

}
