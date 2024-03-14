package com.hackathon.task.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	List<UserEntity> findByEmail(String email);
}
