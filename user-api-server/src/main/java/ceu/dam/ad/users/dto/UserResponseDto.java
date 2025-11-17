package ceu.dam.ad.users.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserResponseDto {
	private Long id;
	private String username;
	@Email(message = "Escribe el correo correctamente")
	private String email;
	private String password;
	private LocalDate createdDate;
	private LocalDate lastLoginDate;
}
