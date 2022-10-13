package com.bootcamp.Capstone.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bootcamp.Capstone.Models.Request;
import com.bootcamp.Capstone.Models.Requestline;
import com.bootcamp.Capstone.Repositories.RequestRepository;
import com.bootcamp.Capstone.Repositories.RequestlineRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/requests")
public class RequestsController {
	
	@Autowired
	private RequestRepository rqRepo;
	
	@Autowired
	private RequestlineRepository rqlRepo;
	
	
	@GetMapping
	public ResponseEntity<Iterable<Request>> getAllRequests(){
		return new ResponseEntity<Iterable<Request>>(rqRepo.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Request> getRequestsbyId(@PathVariable int id){
		var target = rqRepo.findById(id);
		
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		target.get().setRequestlines(rqlRepo.findByRequestId(id));
		return new ResponseEntity<Request>(target.get(), HttpStatus.FOUND);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Request> updateRequest(@PathVariable int id, @RequestBody Request request){
		var target = rqRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(target.get().getId() != request.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		target.get().setRequestlines(rqlRepo.findByRequestId(id));
		rqRepo.save(request);
		return new ResponseEntity<Request>(request, HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity<Request> createRequest(@RequestBody Request request){
		if(request.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		rqRepo.save(request);
		var newRequestId=request.getId();
		request.setRequestlines(rqlRepo.findByRequestId(newRequestId));
		return new ResponseEntity<Request>(request, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deleteRequest(@PathVariable int id) {
		var target = rqRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		rqRepo.delete(target.get());
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("review/{id}")
	public ResponseEntity<Request> reviewRequest(@PathVariable int id){
		var target = rqRepo.findById(id);
		var targetTotal = target.get().getTotal();
		if(targetTotal > 50) {
			target.get().setStatus("Review");
			rqRepo.save(target.get());
			return new ResponseEntity<Request>(target.get(), HttpStatus.PAYMENT_REQUIRED);
		}
		target.get().setStatus("Approved");
		rqRepo.save(target.get());
		return new ResponseEntity<Request>(target.get(), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("approve/{id}")
	public ResponseEntity<Request> approveRequest(@PathVariable int id){
		var target = rqRepo.findById(id);
		if (target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		target.get().setStatus("Approve");
		rqRepo.save(target.get());
		return new ResponseEntity<Request>(target.get(),HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("deny/{id}")
	public ResponseEntity<Request> denyRequest(@PathVariable int id) {
		var target = rqRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		target.get().setStatus("Denied");
		rqRepo.save(target.get());
		return new ResponseEntity<Request>(target.get(),HttpStatus.OK);
		
		
	}
	
	@GetMapping("getreviews")
	public ResponseEntity getReviews(){
		List<Request> targets = rqRepo.findAllByStatus("Review");
		return new ResponseEntity<>(targets, HttpStatus.ACCEPTED);
		}
		
}
	
	
	
	
	


