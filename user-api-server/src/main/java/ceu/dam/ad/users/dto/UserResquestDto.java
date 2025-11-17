package ceu.dam.ad.users.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class UserResquestDto {
	@NotNull(message = "El usuario no puede estar en blanco")
	private String username;
	@Email(message = "El login no puede ser en blanco")
	private String email;
	@NotNull(message = "La contraseña no puede estar en blanco")
	private String password;

}
