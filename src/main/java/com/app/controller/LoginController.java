package com.app.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.RegisterdUser;


@Controller
public class LoginController {
	
	
	
	@RequestMapping("")
	public String Home() {
		
		return "index";
	}


	@GetMapping("/regester")
	public String showSignUpForm(Model model) {
	   RegisterdUser user=new RegisterdUser();
		model.addAttribute("user",user);
		List<String> ListQuestions= Arrays.asList("What is your Birthdate?","What is Your old Phone Number?","What is your Pet Name?");
	
		model.addAttribute("ListQuestions",ListQuestions);
		return "signup_form";
	}
	
	@PostMapping("/regester")
	public String submiForm(@ModelAttribute("user") RegisterdUser user) {
		System.out.println("Register user"+user);
		return "register-success";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping("/logout-success")
	public String logoutPage() {

		return "logout"; 
	}
	@RequestMapping("/homePage")
	public String homePage() {
		return "home"; 
	}
	@RequestMapping("/reg")
	public String dashboard() {
		return "register"; 
	}
	 
	
}
