package ceu.dam.ad.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewUserRequestDto {
	@NotNull(message = "El usuario no puede estar en blanco")
	private String username;
	@Email(message = "El login no puede ser en blanco")
	private String email;
	@NotNull(message = "La contraseña no puede estar en blanco")
	private String password;
}
