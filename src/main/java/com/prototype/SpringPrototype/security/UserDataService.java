package com.prototype.SpringPrototype.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prototype.SpringPrototype.model.UserData;
import com.prototype.SpringPrototype.repository.UserRepository;

@Service
public class UserDataService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserData user = userRepo.findByUserName(username);

		if(user !=null) {
			return new CustomUserData(user);
		}

		throw new UsernameNotFoundException("user not avaialable");
	}

}
