package com.prototype.SpringPrototype.service;

import com.prototype.SpringPrototype.model.UserData;
import org.springframework.stereotype.Service;


public interface UserService {

	
	public UserData createUser(UserData user);
	
	public boolean checkUsername(String username);
}
