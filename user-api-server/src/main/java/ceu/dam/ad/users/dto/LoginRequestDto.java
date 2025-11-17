package ceu.dam.ad.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {
	@NotNull(message = "La contraseña no puede estar en blanco")
	private String password;
	@Email(message = "El login no es correcto")
	private String login;
	
}
