package com.rizky.devtest.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User newUser) {
		User savedUser = this.userService.addUser(newUser);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findAll() {
		List<User> users = this.userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User foundUser = this.userService.findById(id);
		
		return new ResponseEntity<>(foundUser, HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable Long id) {
		User updatedUser = this.userService.updateUser(newUser, id);
		
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		this.userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
