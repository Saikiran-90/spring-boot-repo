package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, String>{
   
	 @Query("select user from UserEntity user where user.email=?1 and user.password=?2")
	 public UserEntity validate(String name, String password);
	 
}
