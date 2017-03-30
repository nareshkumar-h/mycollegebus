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

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDate createdDate;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDate modifiedDate;

}
