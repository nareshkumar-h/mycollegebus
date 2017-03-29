package com.routeapp.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {

	private Long id;

	private String code;

	private String name;

	private String password;

	private String gender;

	private Role role;

	private String emailId;

	private Long mobileNo;

	private boolean active;

	private LocalDate createdDate;

	private LocalDate modifiedDate;

}
