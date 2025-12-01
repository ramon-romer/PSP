package ceu.dam.ad.users.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UserResquestDto {
	@NotEmpty(message = "El usuario no puede estar en blanco")
	@Size(max = 50, message = "Tamañi maximo 50 caracteres para username")
	private String username;
	
	@NotEmpty(message = "El email es obligatorio")
	@Size(max= 100, message = "Tamañi maximo 100 caracteres para email")
	@Email(message = "El login no puede ser en blanco")
	private String email;
	
	@NotEmpty(message = "El password es obligatorio")
	@Size(min= 8, message = "La password tiene que tener minimo 8 caracteres")
	private String password;

}
