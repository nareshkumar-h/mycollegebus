package com.routeapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routeapp.model.Role;
import com.routeapp.model.User;
import com.routeapp.service.UserService;
import com.routeapp.vo.UserVO;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public UserVO login(@RequestBody com.routeapp.model.User user, ModelMap modelMap) {
		System.out.println("UserController->login");

		com.routeapp.model.User userObj = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
		com.routeapp.vo.UserVO userVo = null;
		if (userObj != null) {
			userVo = UserVO.convertTo(userObj);

		}
		return userVo;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) throws Exception {

		System.out.println(user);
		Role r = new Role();
		r.setId(3L); // Default Student
		user.setRole(r);

		HttpStatus status = null; 
		try {
			userService.register(user);
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);

	}
}
