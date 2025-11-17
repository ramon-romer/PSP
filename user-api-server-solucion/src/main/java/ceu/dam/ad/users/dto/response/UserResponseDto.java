package ceu.dam.ad.users.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserResponseDto {
	private Long id;
	private String username;
	private String email;
	private String password;
	private LocalDate createdDate;
	private LocalDate lastLoginDate;
}
