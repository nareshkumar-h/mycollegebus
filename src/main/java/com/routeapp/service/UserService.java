package com.routeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routeapp.dao.UserDAO;
import com.routeapp.model.User;

@Service
public class UserService {
	
	
	@Autowired
	private UserDAO userDAO;

	public User findByEmailAndPassword(String code, String password) {

		return userDAO.findByEmailAndPassword(code, password);
	}

	public User findById(Long empId) {

		return userDAO.findById(empId);
	}

	public List<User> list() {

		return userDAO.list();
	}

	public void delete(Long empId) {

		userDAO.delete(empId);
	}

	public void update(User emp) {

		userDAO.update(emp);
	}

	public void register(User emp) {

		userDAO.save(emp);
}
	

}
