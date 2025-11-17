package ceu.dam.ad.users.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LoginResponseDto {
	private Long id;
	private String username;
	private String email;
	private LocalDate createdDate;
	private LocalDate lastLoginDate;
}
