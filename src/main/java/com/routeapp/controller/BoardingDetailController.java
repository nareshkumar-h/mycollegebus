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

import com.routeapp.model.BoardingDetail;
import com.routeapp.model.RouteStats;
import com.routeapp.service.BoardingDetailService;


@CrossOrigin
@RestController
@RequestMapping("/boardingdetails")
public class BoardingDetailController {

	@Autowired
	private BoardingDetailService boardingDetailService;

	@GetMapping
	public List<BoardingDetail> list() {
		return boardingDetailService.list();
	}
	
	@GetMapping("/routes/{route_no}")
	public List<BoardingDetail> findByRouteNo(@PathVariable("routeNo") Long routeNo) {
		return boardingDetailService.findByRouteNo(routeNo);
	}

	@GetMapping("/{id}")
	public BoardingDetail findById(@PathVariable("id") Long id) {
		return boardingDetailService.findById(id);
	}
	
	@GetMapping("/routestats")
	public List<RouteStats> findRouteStats(){
		return boardingDetailService.findRouteStats();
	}

	@PostMapping
	public void save(@RequestBody BoardingDetail BoardingDetail) {
		boardingDetailService.save(BoardingDetail);
	}

	@PutMapping
	public void update(@RequestBody BoardingDetail BoardingDetail) {
		boardingDetailService.update(BoardingDetail);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		boardingDetailService.delete(id);
	}
}
