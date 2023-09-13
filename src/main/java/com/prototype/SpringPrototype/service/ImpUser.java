
package com.prototype.SpringPrototype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prototype.SpringPrototype.model.UserData;
import com.prototype.SpringPrototype.repository.UserRepository;

@Service
public class ImpUser implements UserService {

	@Autowired
	private UserRepository userRepo;
//	@Autowired
//	private BCryptPasswordEncoder passwordEncode;

	@Override
	public UserData createUser(UserData user) {
//		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");

		return userRepo.save(user);
	}
	@Override
	public boolean checkUsername(String username) {

		return userRepo.existsByUserName(username);
	}

}

