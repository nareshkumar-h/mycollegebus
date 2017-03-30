package com.routeapp.model;

import lombok.Data;

@Data
public class UserBoardingStats {

	private Long id;
	private User user;
	private BoardingDetail boardingDetail;
	private boolean active;
	
}
