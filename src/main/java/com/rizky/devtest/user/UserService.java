package com.rizky.devtest.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rizky.devtest.exceptions.UserAlreadyExistException;
import com.rizky.devtest.exceptions.UserNotExistException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public User addUser(User newUser) {
		User foundUser = this.userRepository.findByName(newUser.getName());
		
		if(foundUser != null) {
			throw new UserAlreadyExistException();
		}
		
		return this.userRepository.save(newUser);
	}
	
	public List<User> findAll() {
		return this.userRepository.findAll();
	}
	
	public User findById(Long id) {
		return  this.userRepository.findById(id).orElseThrow(UserNotExistException::new);
	}
	
	public User updateUser(User newUser, Long id) {
		User foundUser = this.userRepository.findById(id).orElseThrow(UserNotExistException::new);
		String newName = newUser.getName();
		String newPhone = newUser.getPhone();
		
		if(newName != null) {
			foundUser.setName(newName);
		}
		
		if(newPhone != null) {
			foundUser.setPhone(newPhone);
		}
		
		return this.userRepository.save(foundUser);
	}
	
	public void deleteUser(Long id) {
		User foundUser = this.userRepository.findById(id).orElseThrow(UserNotExistException::new);
		
		this.userRepository.delete(foundUser);
	}
}
