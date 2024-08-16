package com.hongha.ver1.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
	private String username;
	private String password;
	private String email;
	private String fullName;
	private boolean gender;
	private Date dob;
	private String avatar;
	private String address1;
	private String address2;
	private String phone1;
	private String phone2;
	private Set<RoleDTO> roles = new HashSet<>();
}
