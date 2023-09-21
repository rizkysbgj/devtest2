package com.rizky.devtest.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rizky.devtest.exceptions.UserAlreadyExistException;
import com.rizky.devtest.exceptions.UserNotExistException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	    
	@Test
	void addUser_shouldReturnUser_whenInvokedWihtUserAndUserNotExist() {
		String name = "John";
		User user = User.builder().name(name).phone("08123456789").build();
		Mockito.when(userRepository.findByName(name)).thenReturn(null);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		User result = this.userService.addUser(user);
		
		Assertions.assertEquals(user, result);
	}
	
	@Test
	void addUser_shouldThrowUserAlreadyExistException_whenInvokedWihtUserAndUsertExist() {
		String name = "John";
		User user = User.builder().name(name).phone("08123456789").build();
		Mockito.when(userRepository.findByName(name)).thenReturn(user);
		
		Assertions.assertThrows(UserAlreadyExistException.class, () -> this.userService.addUser(user));
	}
	
	@Test
	void findAll_shouldReturnListOfUser_whenInvoked() {
		User user= User.builder().name("John").phone("08123456789").build();
		List<User> users = Collections.singletonList(user);
		Mockito.when(userRepository.findAll()).thenReturn(users);
		
		List<User> result = this.userService.findAll();
		
		Assertions.assertEquals(users, result);
	}
	
	@Test
	void findById_shouldReturnUser_whenInvokedAndUserExist() {
		Long id = (long) 1;
		User user= User.builder().id(id).name("John").phone("08123456789").build();
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
		
		User result = this.userService.findById(id);
		
		Assertions.assertEquals(user, result);
	}
	
	@Test
	void findById_shouldThrowUserNotExistException_whenInvokedAndUserNotExist() {
		Long id = (long) 1;
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
		
		Assertions.assertThrows(UserNotExistException.class, () -> this.userService.findById(id));
	}
	
	@Test
	void update_shouldReturnUpdatedUser_whenInvokedAndUserExist() {
		Long id = (long) 1;
		User user= User.builder().id(id).name("John").phone("08123456789").build();
		User updatedUser = User.builder().id(id).name("Alice").phone("02101021089210").build();
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
		Mockito.when(userRepository.save(updatedUser)).thenReturn(updatedUser);
		
		User result = this.userService.updateUser(updatedUser, id);
		
		Assertions.assertEquals(updatedUser, result);
	}
	
	@Test
	void updateUser_shouldThrowUserNotExistException_whenInvokedAndUserNotExist() {
		Long id = (long) 1;
		User updatedUser = User.builder().id(id).name("Alice").phone("02101021089210").build();
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
		
		Assertions.assertThrows(UserNotExistException.class, () -> this.userService.updateUser(updatedUser, id));
	}
	
	@Test
	void deleteUser_shouldThrowUserNotExistException_whenInvokedAndUserNotExist() {
		Long id = (long) 1;
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
		
		Assertions.assertThrows(UserNotExistException.class, () -> this.userService.deleteUser(id));
	}
}
