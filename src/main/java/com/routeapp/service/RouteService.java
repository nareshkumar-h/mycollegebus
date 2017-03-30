package com.routeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routeapp.dao.RouteDAO;
import com.routeapp.model.Route;

@Service
public class RouteService {

	@Autowired
	private RouteDAO routeDAO;

	public Route findById(Long id) {
		return routeDAO.findById(id);
	}

	public List<Route> list() {
		return routeDAO.list();

	}

	public void save(Route Route) {

		routeDAO.save(Route);
	}

	public void delete(Long RouteId) {

		routeDAO.delete(RouteId);
	}

	public void update(Route Route) {

		routeDAO.update(Route);
	}

}
