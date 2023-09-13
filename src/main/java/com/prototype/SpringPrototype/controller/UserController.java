/*
package com.prototype.SpringPrototype.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prototype.SpringPrototype.model.UserData;
import com.prototype.SpringPrototype.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepo;

	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		String username = p.getName();
		UserData user= userRepo.findByUserName(username);

		m.addAttribute("user", user);
	}
	@GetMapping("/")
	public String home() {
		return "user/home";

	}
}
*/
