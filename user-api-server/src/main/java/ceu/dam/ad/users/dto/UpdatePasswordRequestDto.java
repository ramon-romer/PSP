package ceu.dam.ad.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordRequestDto {
	@NotBlank(message = "La password antigua no puede ser vacía")
	private String oldPassword;
	@Size(min = 8, message = "La password nueva tiene que tener minimo 8 caracteres")
	private String newPassword;
}
