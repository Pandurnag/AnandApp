package com.app.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.UserConstant;
import com.app.entity.AdminQuestions;
import com.app.entity.User;
import com.app.repository.UserRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	private String roles;
   
	@PostMapping("join")
	public String joinGroup(@RequestBody User user) {
	  user.setRole(UserConstant.DEFALUT_ROLE);
	  String encriptedPassword = passwordEncoder.encode(user.getPassword());
	  user.setPassword(encriptedPassword);
	  repository.save(user);
	  
	  return "Hi"+user.getUserName() +"Welcome to group";
   }

	@GetMapping("/access/{userId}/{userRole}")
	//@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
	public String giveAccessToUser(@PathVariable int userId,@PathVariable String userRole,Principal principal) {
		String newRole = "";
		int assingedRole;
		User user = repository.findById(userId).get();
		assingedRole = getUserRoleSize(user);
		List<String> activeRole  =  getRoleByLogedInUser(principal);
		List<String> assignRoles = Arrays.stream(user.getRoles().split(",")).collect(Collectors.toList());
		System.out.println("Role assinged:"+assingedRole);
		if(assignRoles.contains(userRole)) {
			return "User allready has "+userRole+" role so you can give to other";
		}
		if(activeRole.contains(userRole) && assingedRole<3) {
			newRole = user.getRoles() +","+ userRole;
			user.setRole(newRole);
			repository.save(user);	
			return "Hi "+ user.getUserName() +" New Role assing to you by "+principal.getName();
		}
		if(assingedRole >= 3) {
			return "User has allready lots of roles "+user.getRoles();
		}			
		return "You can not assing any role right now";
	}
	
	private List<String> getRoleByLogedInUser(Principal principal){
		String roles = getLogedInUser(principal).getRoles();
		List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if(assignRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
		}
		if(assignRoles.contains("ROLE_MODERATOR")) {
			return Arrays.stream(UserConstant.MODERATOR_ACCESS).collect(Collectors.toList());
		}
			return Collections.emptyList();		
	}
	
	private User getLogedInUser(Principal principal) {
		return repository.findByUserName(principal.getName()).get();
		
	}
	
	private int getUserRoleSize(User user) {
		String rolesAssigned  =  user.getRoles();
		List<String> roles = Arrays.stream(rolesAssigned.split(",")).collect(Collectors.toList());			
		return roles.size();   
	}
	
	@GetMapping("/admin")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> loadUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/student")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String studentUserAccess() {
		return "User can only access this !";
		
	}

	@GetMapping("/managequestions")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<AdminQuestions> manageQuestions(){
		return null;
	}
	
	 
	@GetMapping("/test1")
	public String testUser() {
		return "User can only access this !";
		
	}
	
}
