package com.routeapp.vo;

import java.util.ArrayList;
import java.util.List;

import com.routeapp.model.User;

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

	public static List<UserVO> convertTo(List<User> userList) {

		List<UserVO> list = new ArrayList<>();
		for (User u : userList) {

			list.add(convertTo(u));
		}
		return list;
	}
}
