package ceu.dam.ad.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewUserRequestDto {
	
	@NotEmpty(message = "El username es obligatorio")
	@Size(max = 50,  message = "Tamaño máximo 50 caracteres para username")
	private String username;
	
	@NotEmpty(message = "El email es obligatorio")
	@Size(max = 100,  message = "Tamaño máximo 100 caracteres para email")
	@Email(message = "El email debe tener un formato correcto")
	private String email;
	
	@NotEmpty(message = "El password es obligatorio")
	@Size(min = 8, message = "La password nueva tiene que tener más de 7 caracteres")
	private String password;
}
