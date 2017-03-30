package com.routeapp.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.routeapp.dao.UserBoardingDetailDAO;
import com.routeapp.model.UserBoardingDetail;


@Repository
public class UserBoardingDetailService {

	@Autowired
	private UserBoardingDetailDAO userBoardingDetailDAO;


	public List<UserBoardingDetail> list() {

		return userBoardingDetailDAO.list();
	}
	
	public List<UserBoardingDetail> findByUserId(Long userId) {
		return userBoardingDetailDAO.findByUserId(userId);
	}
	
	public List<UserBoardingDetail> findByRouteNo(Long routeNo) {
		return userBoardingDetailDAO.findByRouteNo(routeNo);
	}

	public UserBoardingDetail findById(Long id) {

		return userBoardingDetailDAO.findById(id);
	}

	public void update(UserBoardingDetail bd) {

		userBoardingDetailDAO.update(bd);
	}

	public void delete(Long id) {

		userBoardingDetailDAO.delete(id);
	}

	public void save(UserBoardingDetail bd) {

		userBoardingDetailDAO.save(bd);
	}

}
