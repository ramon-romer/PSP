package ceu.dam.ad.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {
	@Email(message = "El login no puede ser en blanco")
	private String login;
	@NotNull(message = "La contraseña no puede estar en blanco")
	private String password;
}
