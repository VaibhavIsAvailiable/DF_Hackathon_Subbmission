package com.hackathon.task.user;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackathon.task.globalexceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDTO createUser(UserDTO user) {
		UserEntity newUser = this.modelMapper.map(user, UserEntity.class);
		UserEntity savedUser = this.userRepo.save(newUser);

		return this.modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userId) {
		
		UserEntity getUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		getUser.setAbout(user.getAbout());
		getUser.setEmail(user.getEmail());
		getUser.setName(user.getName());
        getUser.setMobileNumber(user.getMobileNumber());
		UserEntity updatedUser = this.userRepo.save(getUser);
		return this.modelMapper.map(updatedUser, UserDTO.class);

	}

	@Override
	public UserDTO getUserById(Integer userId) {
		UserEntity user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.modelMapper.map(user, UserDTO.class);
		
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserEntity> allUsers = this.userRepo.findAll();
		List<UserDTO> result = allUsers.stream().map(user -> this.modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public void deleteUser(Integer userId) {
		UserEntity user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
	}

	@Override
	public UserDTO login(String email,String password ) {
		List<UserEntity> user = this.userRepo.findByEmail(email);
		System.out.println(user.toString());
		UserEntity getUser=  user.get(0);
		if(getUser.getPassword().equals(password))
			return this.modelMapper.map(getUser, UserDTO.class);
					
		return null;
	}



}