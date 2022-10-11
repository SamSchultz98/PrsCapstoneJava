package com.bootcamp.Capstone.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bootcamp.Capstone.Models.Vendor;
import com.bootcamp.Capstone.Repositories.VendorRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/vendors")
public class VendorsController {
	
	@Autowired
	private VendorRepository venRepo;

	@GetMapping
	public ResponseEntity<Iterable<Vendor>> getAllVendors(){
	return new ResponseEntity<Iterable<Vendor>>(venRepo.findAll(),HttpStatus.FOUND);	
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable int id){
		var target = venRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(target.get(), HttpStatus.FOUND);
		}
	
	@PutMapping("{id}")
	public ResponseEntity<Vendor> updateVendor(@PathVariable int id, @RequestBody Vendor vendor){
		var target = venRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(target.get().getId() != vendor.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		venRepo.save(vendor);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity<Vendor> createNewVendor(@RequestBody Vendor vendor){
		if(vendor.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		venRepo.save(vendor);
		return new ResponseEntity<Vendor>(vendor,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deleteVendor(@PathVariable int id){
		var target = venRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		venRepo.delete(target.get());
		return new ResponseEntity<>(HttpStatus.ACCEPTED);	
	}
	
	
	
	
	
	
	
	
	
}

	
