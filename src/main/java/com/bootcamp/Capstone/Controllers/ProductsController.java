package com.bootcamp.Capstone.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bootcamp.Capstone.Models.Product;
import com.bootcamp.Capstone.Repositories.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductsController {
	
	@Autowired
	private ProductRepository proRepo;
	
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> getAllProducts(){
		return new ResponseEntity<Iterable<Product>>(proRepo.findAll(),HttpStatus.FOUND);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id){
		var target = proRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(target.get(),HttpStatus.FOUND);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
		var target = proRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(target.get().getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		proRepo.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		if(product.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		proRepo.save(product);
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteProduct(@PathVariable int id) {
		var target=proRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		proRepo.delete(target.get());
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	

}
