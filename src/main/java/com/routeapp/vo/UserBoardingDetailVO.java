package com.routeapp.vo;

import java.util.ArrayList;
import java.util.List;

import com.routeapp.model.BoardingDetail;
import com.routeapp.model.UserBoardingDetail;

import lombok.Data;

@Data
public class UserBoardingDetailVO {

	private Long id;
	private Long boardingId;
	private String boardingName;
	
	private Long userId;
	private String userName;
	
	private Long routeId;
	private String routeName;
	private String pickUpTime;
	
	
	public static UserBoardingDetailVO convert(UserBoardingDetail bd){
		UserBoardingDetailVO vo = new UserBoardingDetailVO();
		
		vo.setId(bd.getId());
		vo.setUserId(bd.getUser().getId());
		vo.setUserName(bd.getUser().getName());
		
		BoardingDetail boardingDetail = bd.getBoardingDetail();
		vo.setRouteId(boardingDetail.getRoute().getId());
		vo.setRouteName(boardingDetail.getRoute().getName());		
		
		vo.setPickUpTime(boardingDetail.getPickUpTime().toString());
		vo.setBoardingId(boardingDetail.getId());
		vo.setBoardingName(boardingDetail.getName());

		
		return vo;
	}
	
	public static List<UserBoardingDetailVO> convert(List<UserBoardingDetail> bd){
		
		List<UserBoardingDetailVO> ubList = new ArrayList<>();
		for (UserBoardingDetail ub : bd) {
			ubList.add(convert(ub));
		}
		return ubList;
		
	}
	
	
}
