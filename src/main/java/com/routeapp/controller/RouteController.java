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

import com.routeapp.model.Route;
import com.routeapp.service.RouteService;

@CrossOrigin
@RestController
@RequestMapping("/routes")
public class RouteController {

	@Autowired
	private RouteService routeService;

	@GetMapping
	public List<Route> list() {
		return routeService.list();
	}

	@GetMapping("/{id}")
	public Route findById(@PathVariable("id") Long id) {
		return routeService.findById(id);
	}

	@PostMapping
	public void save(@RequestBody Route Route) {
		routeService.save(Route);
	}

	@PutMapping
	public void update(@RequestBody Route Route) {
		routeService.update(Route);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		routeService.delete(id);
	}
}
