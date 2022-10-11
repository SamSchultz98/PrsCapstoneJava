package com.bootcamp.Capstone.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bootcamp.Capstone.Models.User;
import com.bootcamp.Capstone.Repositories.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	@Autowired
	private UserRepository usRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<User>> getAllUsers(){
		return new ResponseEntity<Iterable<User>>(usRepo.findAll(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserbyId(@PathVariable int id){
		var target = usRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(target.get(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<User> createNewUser(@RequestBody User user){
		if(user.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		usRepo.save(user);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
		var target = usRepo.findById(id);
		var targetId = target.get().getId();
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (user.getId() != targetId) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var updatedUser = user;
		usRepo.save(user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteUser(@PathVariable int id) {
		var target = usRepo.findById(id);
		if(target.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		usRepo.delete(target.get());
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("login/{username}/{password}")
	public ResponseEntity<User> login(@PathVariable String username, @PathVariable String password) {
		var target = usRepo.findByUsername(username);
		if(target == null) {
			return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
		}
		var targetPass= target.getPassword();
		if(!targetPass.equals(password)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(target,HttpStatus.ACCEPTED);
			
	}

}
