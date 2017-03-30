package com.routeapp.vo;

import com.routeapp.model.BoardingDetail;

import lombok.Data;

@Data
public class BoardingDetailVO {

	private Long id;
	private String name;
	private Long routeId;
	private String routeName;
	private String pickUpTime;
	
	
	public static BoardingDetailVO convert(BoardingDetail bd){
		BoardingDetailVO vo = new BoardingDetailVO();
		vo.setId(bd.getId());
		vo.setName(bd.getName());
		vo.setPickUpTime(bd.getPickUpTime().toString());
		
		vo.setRouteId(bd.getRoute().getId());
		vo.setRouteName(bd.getRoute().getName());
		
		return vo;
	}
	
	
}
