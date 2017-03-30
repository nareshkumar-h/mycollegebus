package com.routeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routeapp.model.UserBoardingDetail;
import com.routeapp.service.UserBoardingDetailService;


@CrossOrigin
@RestController
@RequestMapping("/userboardingdetails")
public class UserBoardingDetailController {

	@Autowired
	private UserBoardingDetailService boardingDetailService;

	@GetMapping
	public List<UserBoardingDetail> list() {
		return boardingDetailService.list();
	}
	
	@GetMapping("/routes/{routeNo}")
	public List<UserBoardingDetail> findByRouteNo(@PathVariable("routeNo") Long routeNo) {
		return boardingDetailService.findByRouteNo(routeNo);
	}
	
	@GetMapping("/users/{userId}")
	public List<UserBoardingDetail> findByUserId(@PathVariable("userId") Long userId) {
		return boardingDetailService.findByUserId(userId);
	}

	@GetMapping("/{id}")
	public UserBoardingDetail findById(@PathVariable("id") Long id) {
		return boardingDetailService.findById(id);
	}

	@PostMapping
	public void save(@RequestBody UserBoardingDetail BoardingDetail) {
		boardingDetailService.save(BoardingDetail);
	}

	@PutMapping
	public void update(@RequestBody UserBoardingDetail BoardingDetail) {
		boardingDetailService.update(BoardingDetail);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		boardingDetailService.delete(id);
	}
}
