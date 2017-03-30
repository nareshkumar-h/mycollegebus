package com.routeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routeapp.model.User;
import com.routeapp.service.UserService;
import com.routeapp.vo.UserVO;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserVO> list() {

		List<User> list = userService.list();
		List<UserVO> userList = UserVO.convertTo(list);
		return userList;
	}

	@GetMapping("/{id}")
	public UserVO findById(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		UserVO userVO = UserVO.convertTo(user);
		return userVO;
	}

	@PutMapping
	public void update(@RequestBody User user) {
		userService.update(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}

}
