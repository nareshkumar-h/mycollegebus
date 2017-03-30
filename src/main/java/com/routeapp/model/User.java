package com.routeapp.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class User {

	private Long id;

	private String name;

	private String password;

	private Role role;

	private String email;

	private Long mobileNo;

	private boolean active;
	
	private LocalDate createdDate;

	private LocalDate modifiedDate;

}
