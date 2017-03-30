package com.routeapp.controller;

import java.util.List;
import java.util.Map;

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
import com.routeapp.vo.UserBoardingDetailVO;


@CrossOrigin
@RestController
@RequestMapping("/userboardingdetails")
public class UserBoardingDetailController {

	@Autowired
	private UserBoardingDetailService boardingDetailService;

	@GetMapping
	public List<UserBoardingDetailVO> list() {
		List<UserBoardingDetail> list = boardingDetailService.list();
		List<UserBoardingDetailVO> ubVOList = UserBoardingDetailVO.convert(list);
		return ubVOList;
	}
	
	@GetMapping("/routes/{routeNo}")
	public List<UserBoardingDetailVO> findByRouteNo(@PathVariable("routeNo") Long routeNo) {
		List<UserBoardingDetail> list = boardingDetailService.findByRouteNo(routeNo);
		List<UserBoardingDetailVO> userBdList = UserBoardingDetailVO.convert(list);
		return userBdList;
	}
	
	@GetMapping("/boardingstats")
	public Map<Long , Long > findBoardingPointStats() {
		return boardingDetailService.findBoardingPointStats();
	}
	
	@GetMapping("/routestats")
	public Map<Integer , Long > findByRouteStats() {
		return boardingDetailService.findByRouteStats();
	}
	
	@GetMapping("/users/{userId}")
	public List<UserBoardingDetailVO> findByUserId(@PathVariable("userId") Long userId) {
		List<UserBoardingDetail> bdObj = boardingDetailService.findByUserId(userId);
		List<UserBoardingDetailVO> userBdList = UserBoardingDetailVO.convert(bdObj);
		return userBdList;
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
