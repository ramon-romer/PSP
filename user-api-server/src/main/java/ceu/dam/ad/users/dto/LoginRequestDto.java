package ceu.dam.ad.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {
	@NotEmpty(message = "El login no es correcto")
	@Schema(description = "Username o email")
	private String login;
	
	@NotEmpty(message = "La contraseña no puede estar en blanco")
	private String password;
	
}
