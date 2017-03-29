package com.routeapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routeapp.model.User;
import com.routeapp.model.Role;
import com.routeapp.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<User> list() {

		List<User> list = userService.list();
		return list;

	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam("code") String code, @RequestParam("password") String password,
			ModelMap modelMap, HttpSession session) {
		System.out.println("UserController->login");

		User employee=userService.findByEmailAndPassword(code, password);
		if (employee != null) {
			session.setAttribute("LOGGED_IN_USER",employee);

			return "success";
		} else {
			modelMap.addAttribute("ERROR_MESSAGE", "Invalid Email/Password");
			return "index";
		}
}
	
	@PostMapping("/RegisterEmployee")
	public void register(@RequestParam("code") String code, @RequestParam("name") String name,
			@RequestParam("gender") String gender, @RequestParam("role") Long role,
			@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			@RequestParam("mobileNo") Long mobileNo, ModelMap modelMap, HttpSession session) throws Exception {

	
			User emp = new User();
			emp.setCode(code);
			emp.setName(name);
			emp.setGender(gender);
			emp.setEmailId(emailId);
			emp.setPassword(password);
			emp.setMobileNo(mobileNo);

			Role r = new Role();
			r.setId(role); 
			emp.setRole(r);
			

			userService.register(emp);

			

	}


	@GetMapping("/{id}")
	public User findById(@PathVariable("id") Long id) {
		return userService.findById(id);
	}

	@PutMapping
	public void update(@RequestBody User employee) {
		userService.update(employee);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}

}
