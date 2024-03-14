package com.hackathon.task.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.task.payload.ApiResponse;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("digitalflack/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {
		UserDTO createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/updateUserById/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable Integer userId) {
		UserDTO updatedUser = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDTO>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/deleteById/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);

	}

	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());

	}

	@GetMapping("/userById/{userId}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable Integer userId) {

		return ResponseEntity.ok(this.userService.getUserById(userId));

	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> loginUser(@RequestBody LoginDTO loginDTO) {
		System.out.println(loginDTO.getEmail());
		return new ResponseEntity<UserDTO>(this.userService.login(loginDTO.getEmail(), loginDTO.getPassword()),
				HttpStatus.OK);
	}

}