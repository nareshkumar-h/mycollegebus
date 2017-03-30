package com.routeapp.vo;

import lombok.Data;

@Data
public class UserVO {

	private Long id;
	private String name;
	private String email;
	private Integer roleId;
	private String roleName;

	public static UserVO convertTo(com.routeapp.model.User user) {

		UserVO u = new UserVO();
		u.setId(user.getId());
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setRoleName(user.getRole().getName());
		return u;
	}
}
