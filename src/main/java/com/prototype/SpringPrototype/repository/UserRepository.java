package com.prototype.SpringPrototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prototype.SpringPrototype.model.UserData;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<UserData, Integer> {

	public boolean existsByUserName(String username);

	public UserData findByUserName(String username);
}
